package io.typst.bukkit.kotlin.serialization.location

import kotlinx.serialization.Serializable
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity

@Serializable
data class WorldLocation(
    val worldName: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float = 0f,
    val pitch: Float = 0f,
) {
    val world: World? get() = Bukkit.getWorld(worldName)

    fun toBukkitLocation(entity: Entity? = null): Location {
        val yaw = entity?.location?.yaw ?: this.yaw
        val pitch = entity?.location?.pitch ?: this.pitch
        return Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch)
    }

    companion object {
        @JvmStatic
        fun fromBukkit(loc: Location): WorldLocation =
            WorldLocation(
                loc.world?.name ?: "",
                loc.x, loc.y, loc.z,
                loc.yaw, loc.pitch
            )
    }
}
