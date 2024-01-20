package com.chsteam.lover.settings

interface Settings {
    fun leashIsEnabled(): Boolean
    fun getLeashDistanceMin(): Double
    fun getLeashDistanceMax(): Double
}