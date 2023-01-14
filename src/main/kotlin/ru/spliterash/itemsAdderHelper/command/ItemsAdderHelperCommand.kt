package ru.spliterash.itemsAdderHelper.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Optional
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.Player
import org.springframework.stereotype.Component
import ru.spliterash.itemsAdderHelper.service.IAHelperService
import ru.spliterash.itemsAdderHelper.service.LookPlaceMode
import ru.spliterash.itemsAdderHelper.service.OffsetPlaceMode


@Component
@CommandAlias("iahelper")
class ItemsAdderHelperCommand(
    private val helper: IAHelperService
) : BaseCommand() {

    @Subcommand("place_mode look")
    fun mode(player: Player) {
        helper.setMode(player, LookPlaceMode)
    }

    @Subcommand("place_mode offset")
    fun offset(player: Player, x: Double, y: Double, z: Double, @Optional freeRotation: Boolean?) {
        helper.setMode(player, OffsetPlaceMode(x, y, z, freeRotation ?: false))
    }

    @Subcommand("place_mode reset")
    fun reset(player: Player) {
        helper.setMode(player, null)
    }
}