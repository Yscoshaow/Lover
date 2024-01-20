package com.chsteam.lover.item.functional

import com.chsteam.lover.item.LoverItems
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.ClickType
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class Stationery : Item(FabricItemSettings().maxCount(1)) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        itemStack.orCreateNbt.putString("stationery_text", "秘密的消息是！")
        return TypedActionResult.success(itemStack, false)
    }

    override fun onClicked(
        stack: ItemStack?,
        otherStack: ItemStack?,
        slot: Slot?,
        clickType: ClickType?,
        player: PlayerEntity?,
        cursorStackReference: StackReference?
    ): Boolean {
        if(otherStack?.item == Items.GLASS_BOTTLE && clickType == ClickType.RIGHT && stack?.orCreateNbt?.contains("stationery_text") == true && otherStack?.count == 1) {
            val text = stack.orCreateNbt.getString("stationery_text")
            stack.count = 0
            otherStack.count = 0
            val driftingBottle = ItemStack(LoverItems.DRIFTING_BOTTLE, 1)
            driftingBottle.orCreateNbt.putString("stationery_text", text)
            cursorStackReference?.set(driftingBottle)
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference)
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