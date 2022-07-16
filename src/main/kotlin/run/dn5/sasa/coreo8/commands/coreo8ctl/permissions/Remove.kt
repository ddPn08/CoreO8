package run.dn5.sasa.coreo8.commands.coreo8ctl.permissions

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import run.dn5.sasa.coreo8.PaperPlugin
import run.dn5.sasa.coreo8.Prompt
import run.dn5.sasa.coreo8.commands.BaseCommand

class Remove : BaseCommand("remove") {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val plugin = PaperPlugin.instance
        if (args.isEmpty()) return Prompt.err(sender, "Usage: /coreo8ctl permissions remove <permission>")

        val config = plugin.config
        val permissions = config.getStringList("permissions")
        if (!permissions.contains(args[0])) return Prompt.err(sender, "Permission not found")
        permissions.remove(args[0])
        config.set("permissions", permissions)
        config.save("${plugin.dataFolder}/config.yml")

        return Prompt.info(sender, "Permission removed")
    }

    override fun tabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        val plugin = PaperPlugin.instance
        return plugin.config.getStringList("permissions")
    }
}