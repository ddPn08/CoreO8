package run.dn5.sasa.coreo8

import de.myzelyam.api.vanish.VanishAPI
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionAttachment
import java.util.*

class Manager(
    private val plugin: PaperPlugin
) {
    private val database = mutableMapOf<UUID, PlayerData>()

    fun enable(player: Player) {
        database[player.uniqueId] = PlayerData(
            player.location,
            player.gameMode,
        )

        VanishAPI.hidePlayer(player)

        player.gameMode = GameMode.SPECTATOR
        player.inventory.clear()

        val config = this.plugin.config
        config.getStringList("permissions").forEach {
            player.addAttachment(plugin, it, true)
        }
    }

    fun disable(player: Player) {
        if (!this.isEnabled(player)) return
        val data = database[player.uniqueId]!!

        VanishAPI.showPlayer(player)

        player.teleport(data.location)
        player.gameMode = data.gameMode

        database.remove(player.uniqueId)

        val config = this.plugin.config
        config.getStringList("permissions").forEach {
            val attachment = player.addAttachment(plugin, it, false)
            player.removeAttachment(attachment)
        }
    }

    fun isEnabled(player: Player) = database.containsKey(player.uniqueId)

    data class PlayerData(
        val location: Location,
        val gameMode: GameMode,
    )
}