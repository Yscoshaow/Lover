package com.chsteam.lover.client.hud

import com.chsteam.lover.Lover
import com.chsteam.lover.access.LoverManagerAccess
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.DrawContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.client.gui.hud.InGameHud.HeartType
import kotlin.math.min

@Environment(EnvType.CLIENT)
object LoverHud {
    private val LOVE_FULL = Identifier(Lover.MOD_ID, "hud/love_full")
    private val LOVE_HALF = Identifier(Lover.MOD_ID, "hud/love_half")

    fun renderLoveHud(drawContext: DrawContext, type: HeartType, playerEntity: PlayerEntity, x: Int, y: Int, blinking: Boolean, half: Boolean) : Boolean {
        if(type == HeartType.NORMAL) {
            val statusBarY = drawContext.scaledWindowHeight - 39
            val scaleY = statusBarY - y
            if(scaleY == 0 || scaleY == -1) { // 针对只处理第一行的爱心 并且 针对抖动进行修复
                val scaleX = drawContext.scaledWindowWidth / 2 - 91 - x
                val loverManager = (playerEntity as LoverManagerAccess).getLoverManager()
                val libdio = loverManager.libdio / 100
                val values = listOf(0, -8, -16, -24, -32, -40, -48, -56, -64, -72)
                val maxIndex = min(libdio, values.size - 1)
                for(i in 0..maxIndex) {
                    if (values[i] == scaleX) {
                        val identifier = if(half) LOVE_HALF else LOVE_FULL
                        drawContext.drawGuiTexture(identifier, x,y,9,9)
                        return true
                    }
                }
            }
        }
        return false
    }
}