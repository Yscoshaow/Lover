package com.chsteam.lover.item

import com.chsteam.lover.item.attachment.EggVibrator
import com.chsteam.lover.item.attachment.Gag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier


object LoverItems {
    val EGG_VIBRATOR = register(EggVibrator(), "egg_vibrator.json")
    val GAG = register(Gag(), "gag")

    val ITEM_GROUP = FabricItemGroup.builder()
        .icon { ItemStack(EGG_VIBRATOR) }
        .displayName(Text.translatable("itemGroup.lover.main_group"))
        .entries { displayContext: ItemGroup.DisplayContext, entries: ItemGroup.Entries ->
            entries.add(EGG_VIBRATOR)
            entries.add(GAG)
        }
        .build()

    fun register() {
        Registry.register(Registries.ITEM_GROUP, Identifier("lover", "main_group"), ITEM_GROUP)
    }

    private fun register(item: Item, id: String) : Item {
        return Registry.register(Registries.ITEM, Identifier("lover", id), item)
    }
}