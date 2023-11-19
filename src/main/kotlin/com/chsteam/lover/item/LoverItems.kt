package com.chsteam.lover.item

import com.chsteam.lover.item.attachment.JumpingEgg
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object LoverItems {
    val JUMPING_EGG = Registry.register(Registries.ITEM, Identifier("lover", "jumping_egg"), JumpingEgg(FabricItemSettings()))

    fun register() {

    }
}