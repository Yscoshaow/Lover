package com.chsteam.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.effect.LoverEffects
import com.chsteam.lover.entity.LoverEntities
import com.chsteam.lover.item.LoverItems
import com.chsteam.lover.schedule.DelayedTaskManager
import com.chsteam.lover.statistics.LoverStatistics
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.MinecraftServer
import org.slf4j.LoggerFactory

object Lover : ModInitializer {

    private val logger = LoggerFactory.getLogger("lover")

	private lateinit var server: MinecraftServer

	override fun onInitialize() {
		LoverItems.register()
		LoverEntities.register()
		LoverAttributes.register()
		LoverEffects.register()
		LoverStatistics.register()

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