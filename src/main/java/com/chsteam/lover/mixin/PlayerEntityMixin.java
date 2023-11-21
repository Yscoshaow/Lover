package com.chsteam.lover.mixin;

import com.chsteam.lover.attribute.LoverAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(at = @At("RETURN") , method = "createPlayerAttributes", cancellable = true)
    private static void attribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue().add(LoverAttributes.INSTANCE.getPLEASURABLE_SENSATION(),0).add(LoverAttributes.INSTANCE.getSHAME(),0).add(LoverAttributes.INSTANCE.getLIBDIO(),1).add(LoverAttributes.INSTANCE.getSENSITIVITY(), 1));
    }
}
