package com.chsteam.lover.worlddata

import com.chsteam.lover.Lover
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.world.PersistentState
import net.minecraft.world.World


class StationeryText(private val stationeryText : MutableList<String>) : PersistentState() {

    companion object {
        private val type = Type(
            { StationeryText(mutableListOf()) },
            StationeryText::createFromNbt,
            null
        )

        fun getServerState(server: MinecraftServer): StationeryText {
            val persistentStateManager = server.getWorld(World.OVERWORLD)!!.persistentStateManager

            val state = persistentStateManager.getOrCreate(type, Lover.MOD_ID)

            return state
        }

        private fun createFromNbt(tag: NbtCompound): StationeryText {
            val texts = decodeStringToList(tag.getByteArray("stationery_text")).toMutableList()
            val state = StationeryText(texts)
            return state
        }

        private fun encodeListToString(list: List<String>): ByteArray {
            val combinedString = list.joinToString("\t")
            return combinedString.toByteArray(Charsets.UTF_8)
        }

        private fun decodeStringToList(byteArray: ByteArray): List<String> {
            val string = String(byteArray, Charsets.UTF_8)
            return string.split("\t")
        }
    }

    override fun writeNbt(nbt: NbtCompound): NbtCompound {
        val byteArray = encodeListToString(stationeryText)

        nbt.putByteArray("stationery_text", byteArray)
        return nbt
    }

    fun write(text: String) {
        this.stationeryText.add(text.replace("\t", ""))
        this.markDirty()
    }

    fun readRandom() : String? {
        if(this.stationeryText.isNotEmpty()) {
            this.markDirty()
            val randomIndex = this.stationeryText.indices.random()
            return this.stationeryText.removeAt(randomIndex)
        }
        return null
    }
}