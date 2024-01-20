package com.chsteam.lover.mixin;

import com.chsteam.lover.access.LeashAccess;
import com.chsteam.lover.entity.LeashProxyEntity;
import com.chsteam.lover.item.LoverItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements LeashAccess {
    private final ServerPlayerEntity self = (ServerPlayerEntity) (Object) this;
    private LeashProxyEntity lover$leashProxy;
    private Entity lover$holder;

    private int lover$lastage;
    private void lover$leashUpdate() {
        if (
                lover$holder != null && (
                        !lover$holder.isAlive()
                                || !self.isAlive()
                                || self.isDisconnected()
                                || self.hasVehicle()
                )
        ) {
            lover$leashPlayerDeath();
            lover$leashDrop();
        }

        if (lover$leashProxy != null) {
            if (lover$leashProxy.proxyIsRemoved()) {
                lover$leashProxy = null;
            }
            else {
                Entity holderActual = lover$holder;
                Entity holderTarget = lover$leashProxy.getHoldingEntity();

                if (holderTarget == null && holderActual != null) {
                    lover$leashPlayerDeath();
                    lover$leashDrop();
                }
                else if (holderTarget != holderActual) {
                    lover$leashAttach(holderTarget);
                }
            }
        }

        lover$leashApply();
    }

    private void lover$leashApply() {
        ServerPlayerEntity player = self;
        Entity holder = lover$holder;
        if (holder == null) return;
        if (holder.getWorld() != player.getWorld()) return;

        float distance = player.distanceTo(holder);
        if(distance < 4) {
            return;
        }
        if (distance > 32) {
            lover$leashPlayerDeath();
            lover$leashDrop();
            return;
        }

        double dx = (holder.getX() - player.getX()) / (double) distance;
        double dy = (holder.getY() - player.getY()) / (double) distance;
        double dz = (holder.getZ() - player.getZ()) / (double) distance;

        player.addVelocity(
                Math.copySign(dx * dx * 0.4D, dx),
                Math.copySign(dy * dy * 0.4D, dy),
                Math.copySign(dz * dz * 0.4D, dz)
        );

        player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(player));
        player.velocityDirty = false;
    }

    private void lover$leashAttach(Entity entity) {
        lover$holder = entity;

        if (lover$leashProxy == null) {
            lover$leashProxy = new LeashProxyEntity(self);
            self.getWorld().spawnEntity(lover$leashProxy);
            lover$leashProxy.refreshPositionAndAngles(self.getX(), self.getY(), self.getZ(), 0.0F, 0.0F);
        }
        lover$leashProxy.attachLeash(lover$holder, true);

        if (self.hasVehicle()) {
            self.stopRiding();
        }

        lover$lastage = self.age;
    }

    private void lover$leashPlayerDeath() {
        lover$holder = null;

        if (lover$leashProxy != null) {
            if (lover$leashProxy.isAlive() || !lover$leashProxy.proxyIsRemoved()) {
                lover$leashProxy.proxyRemove();
            }
            lover$leashProxy = null;
        }
    }

    private void lover$leashDrop() {
        self.dropItem(LoverItems.INSTANCE.getLEATHER_LEAD());
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void lover$tick(CallbackInfo info) {
        lover$leashUpdate();
    }

    @Override
    public ActionResult leashInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == LoverItems.INSTANCE.getLEATHER_LEAD() && lover$holder == null) {
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            lover$leashAttach(player);
            return ActionResult.SUCCESS;
        }

        if (lover$holder == player && lover$lastage + 20 < self.age) {
            if (!player.isCreative()) {
                lover$leashDrop();
            }
            lover$leashPlayerDeath();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
