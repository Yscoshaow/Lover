package com.chsteam.lover.effect

import com.chsteam.lover.attribute.LoverAttributes
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.AttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import java.util.*

class OestrusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0xF08080) {

    private val uuid: UUID = UUID.randomUUID()

    override fun onApplied(entity: LivingEntity?, amplifier: Int) {
        if(entity is PlayerEntity) {
            entity.attributes.getCustomInstance(LoverAttributes.LIBDIO)?.modifiers?.add(
                EntityAttributeModifier(uuid, "lover.oestrus_effect", amplifier * 0.5, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            )
        }
    }

    override fun onRemoved(attributeContainer: AttributeContainer?) {
        attributeContainer?.getCustomInstance(LoverAttributes.LIBDIO)?.removeModifier(uuid)
    }
}