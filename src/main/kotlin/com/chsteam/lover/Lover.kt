package com.chsteam.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.effect.LoverEffects
import com.chsteam.lover.item.LoverItems
import com.chsteam.lover.schedule.DelayedTaskManager
import com.chsteam.lover.statistics.LoverStatistics
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.LoggerFactory
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.word
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.*


object Lover : ModInitializer {

	const val MOD_ID = "lover"

    private val logger = LoggerFactory.getLogger("lover")

	private lateinit var server: MinecraftServer

	override fun onInitialize() {
		LoverItems.register()
		LoverAttributes.register()
		LoverEffects.register()
		LoverStatistics.register()

		CommandRegistrationCallback.EVENT.register { dispatcher: CommandDispatcher<ServerCommandSource>, registryAccess: CommandRegistryAccess, environment: CommandManager.RegistrationEnvironment ->

		}

		ServerLifecycleEvents.SERVER_STARTED.register { server: MinecraftServer ->
			this.server = server
		}
		ServerTickEvents.END_SERVER_TICK.register { server ->
			DelayedTaskManager.tick(server)
		}
	}

	fun getMinecraftServer() : MinecraftServer {
		return this.server
	}
}