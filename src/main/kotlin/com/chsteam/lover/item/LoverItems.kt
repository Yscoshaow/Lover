package com.chsteam.lover.item

import com.chsteam.lover.item.attachment.*
import com.chsteam.lover.item.lock.Key
import com.chsteam.lover.item.lock.Locker
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object LoverItems {

    val EGG_VIBRATOR = register(EggVibrator(), "egg_vibrator")
    val GAG = register(Gag(), "gag")
    val BLINKERS = register(Blinkers(), "blinkers")
    val STATIC_TAPE = register(StaticTape(), "static_tape")
    val ANAL_PLUG = register(AnalPlug(), "anal_plug")
    val LOCKER = register(Locker(), "locker")
    val KEY = register(Key(), "key")

    val ITEM_GROUP = FabricItemGroup.builder()
        .icon { ItemStack(EGG_VIBRATOR) }
        .displayName(Text.translatable("itemGroup.lover.main_group"))
        .entries { _: ItemGroup.DisplayContext, entries: ItemGroup.Entries ->
            entries.add(EGG_VIBRATOR)
            entries.add(GAG)
            entries.add(BLINKERS)
            entries.add(STATIC_TAPE)
            entries.add(ANAL_PLUG)
            entries.add(LOCKER)
            entries.add(KEY)
        }
        .build()

    fun register() {
        Registry.register(Registries.ITEM_GROUP, Identifier("lover", "main_group"), ITEM_GROUP)
    }

    private fun register(item: Item, id: String) : Item {
        return Registry.register(Registries.ITEM, Identifier("lover", id), item)
    }
}