package run.dn5.sasa.coreo8

import de.myzelyam.api.vanish.VanishAPI
import org.bukkit.Bukkit
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

        Bukkit.getOnlinePlayers().forEach {
            if (it.hasPermission("coreo8.command.coreo8")) Prompt.info(it, "${player.name} entered coreo8")
        }

        val config = plugin.config
        config.getStringList("permissions").forEach {
            player.addAttachment(plugin, it, true)
        }
    }

    fun disable(player: Player) {
        if (!isEnabled(player)) return
        val data = database[player.uniqueId]!!

        VanishAPI.showPlayer(player)

        player.teleport(data.location)
        player.gameMode = data.gameMode

        database.remove(player.uniqueId)

        val config = plugin.config
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