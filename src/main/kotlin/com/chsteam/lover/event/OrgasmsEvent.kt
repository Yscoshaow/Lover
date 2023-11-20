package com.chsteam.lover.event

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult


fun interface OrgasmsEvent {

    fun call(player: PlayerEntity) : ActionResult

    companion object {
        val EVENT: Event<OrgasmsEvent> = EventFactory.createArrayBacked(OrgasmsEvent::class.java) { listeners ->
            OrgasmsEvent { player ->
                for (listener in listeners) {
                    val result = listener.call(player)
                    if (result != ActionResult.PASS) {
                        return@OrgasmsEvent result
                    }
                }
                ActionResult.PASS
            }
        }
    }

}