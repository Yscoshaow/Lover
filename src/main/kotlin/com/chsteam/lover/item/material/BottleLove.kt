package com.chsteam.lover.item.material

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World

class BottleLove : Item(FabricItemSettings().maxCount(16)) {
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        stack?.nbt?.getString("owner")?.let { name ->
            tooltip?.add(Text.translatable("item.lover.bottle_of_lover.who").append(" $name"))
        } ?: {
            tooltip?.add(Text.translatable("item.lover.bottle_of_lover.unknown"))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }
}