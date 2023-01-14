package ru.spliterash.itemsAdderHelper.command.configuration

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import org.springframework.stereotype.Component

@Component
class ACFCommandManagerHolder(plugin: JavaPlugin) {
    init {
        set(plugin)
    }

    val instance
        get() = Companion.instance

    companion object {
        private lateinit var instance: PaperCommandManager

        fun set(plugin: JavaPlugin) {
            if (!Companion::instance.isInitialized)
                instance = PaperCommandManager(plugin)
        }
    }
}