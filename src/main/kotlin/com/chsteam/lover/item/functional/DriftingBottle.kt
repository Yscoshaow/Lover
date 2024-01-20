package com.chsteam.lover.item.functional

import com.chsteam.lover.Lover.stationeryText
import com.chsteam.lover.item.LoverItems.DRIFTING_BOTTLE
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.text.Text
import net.minecraft.world.World

class DriftingBottle : Item(FabricItemSettings().maxCount(1)) {

    companion object {
        fun pickDriftingBottle(stack: ItemStack) : ItemStack {
            if (stack.item === DRIFTING_BOTTLE) {
                if (!stack.getOrCreateNbt().contains("stationery_text")) {
                    val text = stationeryText.readRandom()
                    if (text != "" && text != null) {
                        stack.getOrCreateNbt().putString("stationery_text", text)
                        stack.count = 1
                    } else {
                        return ItemStack(Items.GLASS_BOTTLE, 1)
                    }
                }
            }
            return stack
        }
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        val text = stack?.nbt?.getString("stationery_text") ?: "未写下"
        tooltip?.add(Text.of(text))
        super.appendTooltip(stack, world, tooltip, context)
    }
}