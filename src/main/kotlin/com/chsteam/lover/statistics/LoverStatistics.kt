package com.chsteam.lover.statistics

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.stat.StatFormatter
import net.minecraft.stat.Stats
import net.minecraft.util.Identifier


object LoverStatistics {

    val ORGASMS: Identifier = Registry.register(Registries.CUSTOM_STAT, "orgasms",Identifier("lover", "orgasms"))

    init {
        Stats.CUSTOM.getOrCreateStat(ORGASMS, StatFormatter.DEFAULT);
    }

    fun register() {

    }
}