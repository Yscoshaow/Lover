package com.chsteam.lover.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.TurtleEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.scoreboard.AbstractTeam
import java.util.*


class LeashProxyEntity(private val target: LivingEntity) : TurtleEntity(EntityType.TURTLE, target.world) {

    private fun proxyUpdate(): Boolean {
        if (proxyIsRemoved()) return false

        if (target.world !== world || !target.isAlive) return true

        val posActual = pos
        val posTarget = target.pos.add(0.0, 1.3, -0.15)

        if (!Objects.equals(posActual, posTarget)) {
            setRotation(0.0f, 0.0f)
            setPos(posTarget.getX(), posTarget.getY(), posTarget.getZ())
            boundingBox = getDimensions(EntityPose.DYING).getBoxAt(posTarget)
        }

        updateLeash()

        return false
    }

    override fun tick() {
        if (world.isClient) return
        if (proxyUpdate() && !proxyIsRemoved()) {
            proxyRemove()
        }
    }

    fun proxyIsRemoved(): Boolean {
        return this.isRemoved
    }

    fun proxyRemove() {
        super.remove(RemovalReason.DISCARDED)
    }

    override fun remove(reason: RemovalReason) {
    }

    init {
        health = 1.0f
        isInvulnerable = true

        isBaby = true
        isInvisible = true
        noClip = true

        val server = server
        if (server != null) {
            val scoreboard = server.scoreboard

            var team = scoreboard.getTeam(TEAM_NAME)
            if (team == null) {
                team = scoreboard.addTeam(TEAM_NAME)
            }
            if (team!!.collisionRule != AbstractTeam.CollisionRule.NEVER) {
                team.collisionRule = AbstractTeam.CollisionRule.NEVER
            }

            scoreboard.addScoreHolderToTeam(this.name.string,team)
        }
    }

    override fun getHealth(): Float {
        return 1.0f
    }

    override fun detachLeash(sendPacket: Boolean, dropItem: Boolean) {
    }

    override fun canBeLeashedBy(player: PlayerEntity): Boolean {
        return false
    }

    override fun initGoals() {
    }

    override fun pushAway(entity: Entity) {
    }

    override fun pushAwayFrom(entity: Entity) {
    }

    override fun onPlayerCollision(player: PlayerEntity) {
    }

    companion object {
        const val TEAM_NAME: String = "leashplayersimpl"
    }
}