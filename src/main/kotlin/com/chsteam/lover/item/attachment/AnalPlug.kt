package com.chsteam.lover.item.attachment

import com.chsteam.lover.attribute.LoverAttributes
import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import java.util.*

class AnalPlug : TrinketItem(FabricItemSettings().maxCount(1)) {

    override fun getModifiers(
        stack: ItemStack?,
        slot: SlotReference?,
        entity: LivingEntity?,
        uuid: UUID?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val modifiers =  super.getModifiers(stack, slot, entity, uuid)
        modifiers.put(LoverAttributes.SHAME, EntityAttributeModifier(uuid, "lover.anal_plug", 1.0, EntityAttributeModifier.Operation.ADDITION))
        return modifiers
    }
}