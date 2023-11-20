package com.chsteam.lover.entity

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World

object LoverEntities {

    val PHANTOM = Registry.register(
        Registries.ENTITY_TYPE, Identifier("lover", "phantom"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.CREATURE
        ) { type: EntityType<HostileEntity>, world: World ->
            Phantom(type, world)
        }.dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    )

    fun register() {

    }
}