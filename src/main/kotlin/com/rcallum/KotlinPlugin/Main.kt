package com.rcallum.KotlinPlugin

import com.rcallum.KotlinPlugin.Commands.StartCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable()
    {
        Bukkit.getConsoleSender().sendMessage("Enabling Kotlin Plugin")
        Bukkit.getConsoleSender().sendMessage("${this.description.name} v${this.description.version} was loaded successfully.")
        Bukkit.getServer().getPluginCommand("start")?.setExecutor(StartCommand())
    }

    override fun onDisable()
    {
        Bukkit.getConsoleSender().sendMessage("Disabling Kotlin Plugin")
    }
}