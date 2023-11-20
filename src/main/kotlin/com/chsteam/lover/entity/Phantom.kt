package com.chsteam.lover.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager

class Phantom(entityType: EntityType<HostileEntity>, world: World) : HostileEntity(entityType, world), GeoEntity {

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar?) {
        TODO("Not yet implemented")
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        TODO("Not yet implemented")
    }
}