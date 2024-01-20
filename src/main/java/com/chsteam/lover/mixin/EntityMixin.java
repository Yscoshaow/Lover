package com.chsteam.lover.mixin;

import com.chsteam.lover.Lover;
import com.chsteam.lover.item.LoverItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "isTouchingWater", at = @At("RETURN"), cancellable = true)
    private void lover$driftingBottle(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            Entity entity = (Entity) (Object) this;
            if(entity instanceof ItemEntity) {
                ItemEntity itemEntity = (ItemEntity) entity;
                ItemStack itemStack = itemEntity.getStack();
                if(itemStack.getItem() == LoverItems.INSTANCE.getDRIFTING_BOTTLE() && itemStack.getOrCreateNbt().contains("stationery_text")) {
                    String text = itemStack.getNbt().getString("stationery_text");
                    Lover.INSTANCE.getStationeryText().write(text);
                    itemEntity.discard();
                }
            }
        }
        cir.setReturnValue(cir.getReturnValue());
    }


}
