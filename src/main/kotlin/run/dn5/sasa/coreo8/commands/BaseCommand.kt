package run.dn5.sasa.coreo8.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import run.dn5.sasa.coreo8.Prompt

open class BaseCommand(
    val name: String
) : TabExecutor {
    open val subCommands: List<BaseCommand> = emptyList()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return execute(sender, command, label, args)
        val subCommand =
            subCommands.find { it.name == args[0] } ?: return execute(sender, command, label, args)
        return subCommand.onCommand(sender, command, args[0], args.copyOfRange(1, args.size))
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        if (args.isEmpty() || args.size == 1) return subCommands.map { it.name } + tabComplete(
            sender,
            command,
            label,
            args
        )

        val subCommand =
            subCommands.find { it.name == args[0] } ?: return subCommands.map { it.name } + tabComplete(
                sender,
                command,
                label,
                args
            )
        return subCommand.onTabComplete(sender, command, args[0], args.copyOfRange(1, args.size))
    }

    open fun execute(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return Prompt.err(sender, "Unknown command: $label ${args.joinToString(" ")}")
    }

    open fun tabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        return emptyList()
    }
}