package ru.spliterash.itemsAdderHelper.service

import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector

class OffsetPlaceMode(
    private val x: Double,
    private val y: Double,
    private val z: Double,
    private val freeRotation: Boolean = false
) : FurniturePlaceMode {
    override fun location(e: PlayerInteractEvent): Location {
        val face = face(e.player)

        val location = e.clickedBlock!!.getRelative(e.blockFace).location.add(0.5, 0.0, 0.5)

        val toAdd = when (face) {
            BlockFace.NORTH -> Vector(x, y, z)
            BlockFace.EAST -> Vector(-z, y, x)
            BlockFace.SOUTH -> Vector(-x, y, -z)
            BlockFace.WEST -> Vector(z, y, -x)
            else -> Vector()
        }

        location.add(toAdd)
        val yaw:Float = if (freeRotation) e.player.location.yaw else when (face) {
            BlockFace.SOUTH -> 0.0F
            BlockFace.WEST -> 90.0F
            BlockFace.NORTH -> 180.0F
            BlockFace.EAST -> 270.0F
            else -> 0F
        }
        location.yaw = yaw

        return location
    }

    private fun face(player: Player): BlockFace {
        val yaw = (player.location.yaw + 360) % 360
        return when {
            yaw >= 45 && yaw < 135 -> BlockFace.WEST
            yaw >= 135 && yaw < 225 -> BlockFace.NORTH
            yaw >= 225 && yaw < 315 -> BlockFace.EAST
            else -> BlockFace.SOUTH
        }
    }

    override fun name(): String = "OFFSET($x,$y,$z)"
}