package com.chsteam.lover.attribute

import net.minecraft.entity.attribute.ClampedEntityAttribute
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object LoverAttributes {

    val LIBDIO = register("generic.libdio", ClampedEntityAttribute("attribute.name.generic.libdio", 0.0, 0.0, 100.0).setTracked(true))
    val PLEASURABLE_SENSATION = register("generic.pleasurable_sensation", ClampedEntityAttribute("attribute.name.generic.pleasurable_sensation", 0.0, 0.0, 100.0).setTracked(true))
    val SHAME = register("generic.shame", ClampedEntityAttribute("attribute.name.generic.shame", 0.0, 0.0, 100.0).setTracked(true))
    val SENSITIVITY = register("generic.sensitivity", ClampedEntityAttribute("attribute.name.generic.sensitivity", 1.0, 1.0, 100.0).setTracked(true))

    fun register() {
        // 仅仅是为了注册这些属性
    }

    private fun register(id: String, attribute: EntityAttribute): EntityAttribute {
        return Registry.register(Registries.ATTRIBUTE, Identifier("lover", id), attribute) as EntityAttribute
    }
}