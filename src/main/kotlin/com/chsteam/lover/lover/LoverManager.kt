package com.chsteam.lover.lover

import com.chsteam.lover.attribute.LoverAttributes
import com.chsteam.lover.event.OrgasmsEvent
import com.chsteam.lover.schedule.DelayedTaskManager
import com.chsteam.lover.statistics.LoverStatistics
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ActionResult
import java.util.UUID

class LoverManager {

    companion object {
        const val MAX_LIBDIO = 1000
        const val MAX_PLEASURABLE = 1000
        val LOVER_TIMING = listOf(250, 500, 750, 1000)
    }

    private var libdio = 0
    private var tick = 0
    private var pleasurable = 0

    fun update(player: PlayerEntity) {
        tick++
        if(tick % 20 != 0) return

        if(libdio < MAX_LIBDIO) {
            libdio += player.attributes.getValue(LoverAttributes.LIBDIO).toInt()
        }
        else if(libdio == MAX_LIBDIO) {
            player.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 2400, 1,  false,  false , false))
            player.addStatusEffect(StatusEffectInstance(StatusEffects.MINING_FATIGUE, 2400, 1, false, false , false))
            // 300秒的敏感度翻倍
            val uuid = UUID.randomUUID()
            player.getAttributeInstance(LoverAttributes.SENSITIVITY)?.addTemporaryModifier(EntityAttributeModifier(uuid, "lover.libdio_max", 2.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
            DelayedTaskManager.addTask(DelayedTaskManager.DelayedTask(6000) {
                player.getAttributeInstance(LoverAttributes.SENSITIVITY)?.removeModifier(uuid)
            })
        }
        if(libdio in LOVER_TIMING) {
            // TODO 分泌爱液
        }

        val pleasurableIncrease = player.attributes.getValue(LoverAttributes.SENSITIVITY) * player.attributes.getValue(LoverAttributes.INTERACT)
        if(pleasurableIncrease != 0.0) {
            pleasurable += pleasurableIncrease.toInt()
        } else {
            pleasurable -= 5
        }

        if(pleasurable == MAX_PLEASURABLE) {
            val event = OrgasmsEvent.EVENT.invoker().call(player)
            if(event != ActionResult.FAIL) {
                pleasurable = 700
                libdio = 0

                player.increaseStat(LoverStatistics.ORGASMS, 1)
                player.hungerManager.foodLevel -= 6
                player.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, 900, 2, false, false, false))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 900, 2, false, false, false))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.MINING_FATIGUE, 900, 2, false, false, false))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.REGENERATION, 200, 1, false, false, false))

                // 300 秒
                val uuid = UUID.randomUUID()
                player.getAttributeInstance(LoverAttributes.SENSITIVITY)?.addTemporaryModifier(EntityAttributeModifier(uuid, "lover.orgasm", 5.0, EntityAttributeModifier.Operation.ADDITION))
                DelayedTaskManager.addTask(DelayedTaskManager.DelayedTask(6000) {
                    player.getAttributeInstance(LoverAttributes.SENSITIVITY)?.removeModifier(uuid)
                })
            }
        }
    }

    fun readNbt(nbt: NbtCompound) {
        val loverNbt = nbt.getCompound("lover")
        this.libdio = loverNbt.getInt("libdio")
        this.pleasurable = loverNbt.getInt("pleasurable")
    }

    fun writeNbt(nbt: NbtCompound) {
        val loverNbt = NbtCompound()
        loverNbt.putInt("libdio", this.libdio)
        loverNbt.putInt("pleasurable", this.pleasurable)
        nbt.put("lover", loverNbt)
    }
}