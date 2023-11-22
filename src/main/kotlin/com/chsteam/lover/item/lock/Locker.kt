package com.chsteam.lover.item.lock

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.ClickType
import net.minecraft.world.World
import java.util.*


class Locker : Item(FabricItemSettings()) {

    override fun onClicked(
        stack: ItemStack?,
        otherStack: ItemStack?,
        slot: Slot?,
        clickType: ClickType?,
        player: PlayerEntity?,
        cursorStackReference: StackReference?
    ): Boolean {
        if(otherStack?.item is Key && clickType == ClickType.RIGHT) {
            val nbt = stack?.orCreateNbt
            val uuid = UUID.randomUUID()
            nbt?.putUuid("lock_id", uuid)

            val otherNbt = otherStack.orCreateNbt
            otherNbt.putUuid("lock_id", uuid)

            otherStack.nbt = otherNbt
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference)
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        val uuid = stack?.nbt?.getUuid("lock_id") ?: "未绑定"
        tooltip?.add(Text.translatable("item.lover.key.tooltip").append(" $uuid"))
        super.appendTooltip(stack, world, tooltip, context)
    }
}