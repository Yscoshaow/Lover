package com.chsteam.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.effect.LoverEffects
import com.chsteam.lover.item.LoverItems
import com.chsteam.lover.schedule.DelayedTaskManager
import com.chsteam.lover.settings.Settings
import com.chsteam.lover.statistics.LoverStatistics
import com.chsteam.lover.worlddata.StationeryText
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule
import org.slf4j.LoggerFactory


object Lover : ModInitializer  {

	const val MOD_ID = "lover"

    private val logger = LoggerFactory.getLogger("lover")

	lateinit var server: MinecraftServer
		private set
	lateinit var stationeryText: StationeryText
		private set


	override fun onInitialize() {
		LoverItems.register()
		LoverAttributes.register()
		LoverEffects.register()
		LoverStatistics.register()

		CommandRegistrationCallback.EVENT.register { dispatcher: CommandDispatcher<ServerCommandSource>, registryAccess: CommandRegistryAccess, environment: CommandManager.RegistrationEnvironment ->

		}

		ServerLifecycleEvents.SERVER_STARTED.register { server: MinecraftServer ->
			this.server = server
			this.stationeryText = StationeryText.getServerState(server)
		}
		ServerTickEvents.END_SERVER_TICK.register { server ->
			DelayedTaskManager.tick(server)
		}
	}

	fun getMinecraftServer() : MinecraftServer {
		return this.server
	}
}