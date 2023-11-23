package com.chsteam.lover.mixin;

import com.chsteam.lover.access.LoverManagerAccess;
import com.chsteam.lover.attribute.LoverAttributes;
import com.chsteam.lover.lover.LoverManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements LoverManagerAccess {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private final LoverManager loverManager = new LoverManager();

    @NotNull
    @Override
    public LoverManager getLoverManager() {
        return this.loverManager;
    }

    @Inject(at = @At("RETURN") , method = "tick")
    public void lover$updateLover(CallbackInfo info) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        this.loverManager.update(player);
    }

    @Inject(at = @At("RETURN") , method = "createPlayerAttributes", cancellable = true)
    private static void lover$createLoverAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue().add(LoverAttributes.INSTANCE.getINTERACT(),0).add(LoverAttributes.INSTANCE.getSHAME(),0).add(LoverAttributes.INSTANCE.getLIBDIO(),1).add(LoverAttributes.INSTANCE.getSENSITIVITY(), 5));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "TAIL"))
    private void lover$readCustomDataFromTag(NbtCompound nbt, CallbackInfo info) {
        this.loverManager.readNbt(nbt);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "TAIL"))
    private void lover$writeCustomDataToTag(NbtCompound nbt, CallbackInfo info) {
        this.loverManager.writeNbt(nbt);
    }

}
