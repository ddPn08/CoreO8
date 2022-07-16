package run.dn5.sasa.coreo8.commands.coreo8ctl.permissions

import run.dn5.sasa.coreo8.commands.BaseCommand

class Permissions : BaseCommand("permissions") {
    override val subCommands: List<BaseCommand> = listOf(Add(), Remove())
}