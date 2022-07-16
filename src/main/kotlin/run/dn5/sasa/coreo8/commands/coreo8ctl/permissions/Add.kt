package run.dn5.sasa.coreo8.commands.coreo8ctl.permissions

import net.luckperms.api.LuckPermsProvider
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import run.dn5.sasa.coreo8.PaperPlugin
import run.dn5.sasa.coreo8.Prompt
import run.dn5.sasa.coreo8.commands.BaseCommand

class Add : BaseCommand("add") {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val plugin = PaperPlugin.instance
        val config = plugin.config

        if (args.isEmpty()) return Prompt.err(sender, "Usage: /coreo8ctl add <permission>")

        val permission = args[0]
        val permissions = config.getStringList("permissions")
        if (permissions.contains(permission)) return Prompt.err(sender, "Permission already exists")
        permissions.add(permission)
        config.set("permissions", permissions)
        config.save("${plugin.dataFolder}/config.yml")

        return Prompt.info(sender, "Permission added")
    }

    override fun tabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        val plugin = PaperPlugin.instance
        val pre = if (args.isEmpty()) "" else args[0]
        return plugin.server.pluginManager.permissions.map { it.name }.filter { it.startsWith(pre) }
    }
}