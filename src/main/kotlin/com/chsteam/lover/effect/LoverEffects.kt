package com.chsteam.lover.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object LoverEffects {

    val OESTRUS = register(OestrusEffect(), "oestrus")
    val HORMONE = register(HormoneEffect(), "hormone")

    fun register() {

    }

    private fun register(effect: StatusEffect, id: String) : StatusEffect {
        return Registry.register(Registries.STATUS_EFFECT, Identifier("lover", id), effect)
    }

}