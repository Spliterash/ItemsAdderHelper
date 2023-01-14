package ru.spliterash.itemsAdderHelper.service

import org.bukkit.Location
import org.bukkit.event.player.PlayerInteractEvent

interface FurniturePlaceMode {
    fun location(e: PlayerInteractEvent): Location?
    fun name(): String
}