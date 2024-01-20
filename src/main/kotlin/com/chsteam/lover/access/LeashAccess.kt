package com.chsteam.lover.access

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

interface LeashAccess {
    fun leashInteract(player: PlayerEntity?, hand: Hand?): ActionResult?
}
