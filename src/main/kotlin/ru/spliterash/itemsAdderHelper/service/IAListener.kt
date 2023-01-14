package ru.spliterash.itemsAdderHelper.service

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import ru.spliterash.springspigot.listener.SpigotListener

@SpigotListener
class IAListener(
    private val iaHelperService: IAHelperService
) : Listener {
    @EventHandler
    fun onPlace(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK || e.hand == EquipmentSlot.OFF_HAND) return
        iaHelperService.onPlace(e)
    }
}