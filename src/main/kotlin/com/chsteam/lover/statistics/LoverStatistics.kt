package com.chsteam.lover.statistics

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.stat.StatFormatter
import net.minecraft.stat.Stats
import net.minecraft.util.Identifier


object LoverStatistics {

    val ORGASMS: Identifier = register("orgasms")

    init {
        Stats.CUSTOM.getOrCreateStat(ORGASMS, StatFormatter.DEFAULT);
    }

    fun register() {

    }

    private fun register(id: String) : Identifier {
        return Registry.register(Registries.CUSTOM_STAT, id, Identifier("lover", id))
    }
}