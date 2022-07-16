package run.dn5.sasa.coreo8

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender

object Prompt {
    fun info(sender: CommandSender, message: String): Boolean {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>[CoreO8] <aqua>$message"))
        return true
    }

    fun err(sender: CommandSender, message: String): Boolean {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>[CoreO8] <red>$message"))
        return false
    }
}