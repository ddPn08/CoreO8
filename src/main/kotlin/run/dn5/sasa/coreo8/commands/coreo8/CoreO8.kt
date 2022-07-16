package run.dn5.sasa.coreo8.commands.coreo8

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import run.dn5.sasa.coreo8.PaperPlugin
import run.dn5.sasa.coreo8.Prompt
import run.dn5.sasa.coreo8.commands.BaseCommand

class CoreO8 : BaseCommand("coreo8") {
    override val subCommands: List<BaseCommand> = listOf()

    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return Prompt.err(sender, "This command can only be used in-game.")

        val plugin = PaperPlugin.instance
        return if (plugin.manager.isEnabled(sender)) {
            plugin.manager.disable(sender)
            Prompt.info(sender, "Leaving CoreO8...")
        } else {
            plugin.manager.enable(sender)
            Prompt.info(sender, "Entering CoreO8...")
        }
    }
}