package com.chsteam.lover.item.lock

import com.chsteam.lover.item.LoverItems
import dev.emi.trinkets.api.TrinketInventory
import dev.emi.trinkets.api.TrinketItem
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.ClickType
import net.minecraft.world.World
import java.util.UUID

abstract class LockableAttachment(settings: Settings) : TrinketItem(settings) {

    override fun onClicked(
        stack: ItemStack?,
        otherStack: ItemStack?,
        slot: Slot?,
        clickType: ClickType?,
        player: PlayerEntity?,
        cursorStackReference: StackReference?
    ): Boolean {

        if(slot?.inventory is TrinketInventory && stack?.nbt != null && (otherStack == null || otherStack.isEmpty)) {
            val currentUUID = stack.orCreateNbt?.getUuid("lock_id")
            if(currentUUID != UUID.fromString("00000000-0000-0000-0000-000000000000")) {
                return true
            }
        }

        if(clickType == ClickType.RIGHT) {
            if(otherStack?.item is Locker && otherStack.nbt != null) {
                val nbt = stack?.orCreateNbt
                val uuid = otherStack.orCreateNbt.getUuid("lock_id")
                nbt?.putUuid("lock_id", uuid)

                otherStack.count -= 1
            }
            else if(otherStack?.item is Key && stack?.nbt != null && otherStack.nbt != null) {
                val keyUUID = otherStack.orCreateNbt.getUuid("lock_id")
                val currentUUID = stack.orCreateNbt.getUuid("lock_id")
                if(currentUUID == keyUUID) {

                    val locker = LoverItems.LOCKER.defaultStack
                    locker.orCreateNbt.putUuid("lock_id", keyUUID)
                    player?.giveItemStack(locker)

                    val nbt = stack.orCreateNbt
                    nbt.putUuid("lock_id", UUID.fromString("00000000-0000-0000-0000-000000000000"))
                    stack.nbt = nbt
                }
            }
            else if(otherStack?.item is KeyMaster) {
                val nbt = stack?.orCreateNbt
                nbt?.putUuid("lock_id", UUID.fromString("00000000-0000-0000-0000-000000000000"))
                stack?.nbt = nbt
            }
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference)
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        val uuid = stack?.nbt?.getUuid("lock_id")
        if(uuid != null && uuid != UUID.fromString("00000000-0000-0000-0000-000000000000")) {
            tooltip?.add(Text.translatable("item.lover.key.tooltip").append(" $uuid"))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }

}