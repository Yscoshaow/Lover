package com.chsteam.lover.item.attachment

import com.chsteam.lover.attribute.LoverAttributes
import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import java.util.*

class EggVibrator(setting : Settings) : TrinketItem(setting) {

    override fun getModifiers(
        stack: ItemStack?,
        slot: SlotReference?,
        entity: LivingEntity?,
        uuid: UUID?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val modifiers =  super.getModifiers(stack, slot, entity, uuid)
        modifiers.put(LoverAttributes.PLEASURABLE_SENSATION, EntityAttributeModifier(uuid, "lover.pleasurable_sensation_speed", 1.0, EntityAttributeModifier.Operation.ADDITION))
        return modifiers
    }
}