package com.chsteam.lover.attribute

import net.minecraft.entity.attribute.ClampedEntityAttribute
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object LoverAttributes {

    val LIBDIO = register("generic.libdio", ClampedEntityAttribute("attribute.name.generic.libdio", 0.0, 0.0, 100.0).setTracked(true)) // 欲望增长速率
    val INTERACT = register("generic.interact", ClampedEntityAttribute("attribute.name.generic.interact", 0.0, 0.0, 1000.0).setTracked(true)) // 刺激度
    val SHAME = register("generic.shame", ClampedEntityAttribute("attribute.name.generic.shame", 0.0, 0.0, 10.0).setTracked(true)) // 羞耻度
    val SENSITIVITY = register("generic.sensitivity", ClampedEntityAttribute("attribute.name.generic.sensitivity", 5.0, 1.0, 1000.0).setTracked(true)) // 敏感度

    fun register() {
        // 仅仅是为了注册这些属性
    }

    private fun register(id: String, attribute: EntityAttribute): EntityAttribute {
        return Registry.register(Registries.ATTRIBUTE, Identifier("lover", id), attribute) as EntityAttribute
    }
}