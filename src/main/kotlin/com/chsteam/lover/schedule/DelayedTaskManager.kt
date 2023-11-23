package com.chsteam.lover.schedule

import com.chsteam.lover.Lover
import net.minecraft.server.MinecraftServer
import java.util.*

object DelayedTaskManager {
    private val tasks = PriorityQueue<DelayedTask>()

    fun addTask(task: DelayedTask) {
        tasks.offer(task)
    }

    fun tick(server: MinecraftServer) {
        val currentTime = server.ticks.toLong()
        while (tasks.isNotEmpty() && tasks.peek().executionTime <= currentTime) {
            val task = tasks.poll()
            task.run()
        }
    }

    class DelayedTask(delayInTicks: Long, private val action: Runnable) : Comparable<DelayedTask> {
        val executionTime: Long = Lover.getMinecraftServer().ticks + delayInTicks

        override fun compareTo(other: DelayedTask): Int {
            return executionTime.compareTo(other.executionTime)
        }

        fun run() {
            action.run()
        }
    }
}