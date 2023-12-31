package com.chsteam.lover.item.attachment

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.item.lock.LockableAttachment
import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import java.util.*

class Gag : LockableAttachment(FabricItemSettings().maxCount(1))  {

    companion object {
        val gagPlayer = hashMapOf<UUID, Boolean>()
    }

    override fun getModifiers(
        stack: ItemStack?,
        slot: SlotReference?,
        entity: LivingEntity?,
        uuid: UUID?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val modifiers =  super.getModifiers(stack, slot, entity, uuid)
        modifiers.put(LoverAttributes.SHAME, EntityAttributeModifier(uuid, "lover.pleasurable_shame_speed", 1.0, EntityAttributeModifier.Operation.ADDITION))
        return modifiers
    }

    override fun onEquip(stack: ItemStack?, slot: SlotReference?, entity: LivingEntity?) {
        if (entity is PlayerEntity) {
            gagPlayer[entity.uuid] = true
        }
    }

    override fun onUnequip(stack: ItemStack?, slot: SlotReference?, entity: LivingEntity?) {
        if (entity is PlayerEntity) {
            gagPlayer[entity.uuid] = false
        }
    }
}