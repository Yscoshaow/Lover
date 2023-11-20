package com.chsteam.lover.item

import com.chsteam.lover.item.attachment.JumpingEgg
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object LoverItems {
    val JUMPING_EGG = register(JumpingEgg(FabricItemSettings()), "jumping_egg")

    fun register() {

    }

    private fun register(item: Item, id: String) : Item {
        return Registry.register(Registries.ITEM, Identifier("lover", id), item)
    }
}