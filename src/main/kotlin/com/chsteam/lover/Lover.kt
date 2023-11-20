package com.chsteam.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.effect.LoverEffects
import com.chsteam.lover.entity.LoverEntities
import com.chsteam.lover.item.LoverItems
import com.chsteam.lover.statistics.LoverStatistics
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Lover : ModInitializer {

    private val logger = LoggerFactory.getLogger("lover")

	override fun onInitialize() {
		LoverItems.register()
		LoverEntities.register()
		LoverAttributes.register()
		LoverEffects.register()
		LoverStatistics.register()
	}
}