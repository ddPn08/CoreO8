package run.dn5.sasa.coreo8.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import run.dn5.sasa.coreo8.PaperPlugin

class PlayerQuitListener(
    private val plugin: PaperPlugin
) : Listener {
    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        plugin.manager.disable(e.player)
    }
}