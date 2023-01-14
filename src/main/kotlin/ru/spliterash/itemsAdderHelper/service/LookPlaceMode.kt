package ru.spliterash.itemsAdderHelper.service

import org.bukkit.Location
import org.bukkit.event.player.PlayerInteractEvent

object LookPlaceMode : FurniturePlaceMode {
    override fun location(e: PlayerInteractEvent): Location? {
        return e.player.rayTraceBlocks(5.0)
            ?.hitPosition
            ?.toLocation(e.player.world)
            ?.apply {
                yaw = e.player.location.yaw
            }
    }


    override fun name(): String = "LOOK"
}