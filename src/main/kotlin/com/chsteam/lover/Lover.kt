package com.chsteam.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.item.LoverItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Lover : ModInitializer {

    private val logger = LoggerFactory.getLogger("lover")

	override fun onInitialize() {
		LoverItems.register()
		LoverAttributes.register()
	}
}