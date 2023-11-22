package com.chsteam.lover.item.attachment

import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotAttributes
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack
import java.util.*

class Blinkers : TrinketItem(FabricItemSettings().maxCount(1)) {

    override fun tick(stack: ItemStack?, slot: SlotReference?, entity: LivingEntity?) {
        entity?.setStatusEffect(StatusEffectInstance(StatusEffects.BLINDNESS, 20, 50, false, false, false), entity)
    }

    override fun getModifiers(
        stack: ItemStack?,
        slot: SlotReference?,
        entity: LivingEntity?,
        uuid: UUID?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val modifiers =  super.getModifiers(stack, slot, entity, uuid)
        SlotAttributes.addSlotModifier(modifiers, "head/hat", uuid, 1.0, EntityAttributeModifier.Operation.ADDITION)
        return modifiers
    }

}