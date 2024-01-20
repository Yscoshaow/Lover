package com.chsteam.lover.mixin;

import com.chsteam.lover.item.functional.DriftingBottle;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @ModifyVariable(method = "insertStack(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), ordinal = 0)
    public ItemStack lover$modifyDriftingBottle(ItemStack stack) {
        return DriftingBottle.Companion.pickDriftingBottle(stack);
    }
}
