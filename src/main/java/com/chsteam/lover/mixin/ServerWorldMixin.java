package com.chsteam.lover.mixin;

import com.chsteam.lover.entity.LeashProxyEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "shouldCancelSpawn(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true, require = 0)
    private void lover$onShouldCancelSpawn(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity instanceof LeashProxyEntity) {
            info.setReturnValue(false);
            info.cancel();
        }
    }
}