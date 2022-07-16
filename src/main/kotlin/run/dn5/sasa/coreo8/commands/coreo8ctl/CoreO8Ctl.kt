package run.dn5.sasa.coreo8.commands.coreo8ctl

import run.dn5.sasa.coreo8.commands.BaseCommand
import run.dn5.sasa.coreo8.commands.coreo8ctl.permissions.Permissions

class CoreO8Ctl : BaseCommand("coreo8ctl") {
    override val subCommands: List<BaseCommand> = listOf(
        Permissions()
    )
}