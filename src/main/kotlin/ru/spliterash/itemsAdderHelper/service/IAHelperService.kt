package ru.spliterash.itemsAdderHelper.service

import dev.lone.itemsadder.api.CustomFurniture
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class IAHelperService {
    private val playerPlaceMode = hashMapOf<UUID, FurniturePlaceMode>()

    fun setMode(player: Player, mode: FurniturePlaceMode?) {
        if (mode == null) {
            playerPlaceMode.remove(player.uniqueId)
        } else {
            playerPlaceMode[player.uniqueId] = mode
        }
    }

    fun playerMode(uniqueId: UUID): FurniturePlaceMode? {
        return playerPlaceMode[uniqueId]
    }

    fun onPlace(e: PlayerInteractEvent) {
        val player = e.player

        e.clickedBlock ?: return
        val item = e.item ?: return
        val customStack = CustomFurniture.byItemStack(item) ?: return
        val mode = playerMode(player.uniqueId) ?: return
        e.isCancelled = true

        val location = mode.location(e) ?: return

        CustomFurniture.spawnPreciseNonSolid(customStack.id, location)
        player.sendMessage("Фурнитура установлена в соответствии с режимом ${mode.name()}")
    }
}