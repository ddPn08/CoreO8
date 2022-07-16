package run.dn5.sasa.coreo8

import org.bukkit.plugin.java.JavaPlugin
import run.dn5.sasa.coreo8.commands.coreo8.CoreO8
import run.dn5.sasa.coreo8.commands.coreo8ctl.CoreO8Ctl
import run.dn5.sasa.coreo8.listeners.PlayerQuitListener
import java.io.File

class PaperPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: PaperPlugin
    }

    val manager = Manager(this)

    override fun onEnable() {
        instance = this
        this.checkResources()
        this.registerCommands()
        this.registerListeners()
    }

    private fun checkResources() {
        if (File("${this.dataFolder}/config.yml").exists()) return
        this.saveDefaultConfig()
    }

    private fun registerCommands() {
        listOf(
            CoreO8(),
            CoreO8Ctl()
        ).forEach {
            this.getCommand(it.name)?.setExecutor(it)
        }
    }

    private fun registerListeners() {
        listOf(
            PlayerQuitListener(this),
        ).forEach { this.server.pluginManager.registerEvents(it, this) }
    }
}