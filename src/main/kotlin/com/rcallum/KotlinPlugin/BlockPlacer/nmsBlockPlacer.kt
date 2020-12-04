package com.rcallum.KotlinPlugin.BlockPlacer

import net.minecraft.server.v1_15_R1.BlockPosition
import net.minecraft.server.v1_15_R1.ChunkSection
import net.minecraft.server.v1_15_R1.IBlockData
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld


class nmsBlockPlacer {

    // Loops through all the locations and starts the placement
    public fun startPlacing(loc: Location, length: Int, width: Int, height: Int){
        val location = loc.add(1.0, 0.0, 0.0)
        for (a in 0..length){
            for (b in 0..width){
                for (c in 0..height){
                    val loc2 = location.clone().add(a.toDouble(), c.toDouble(), b.toDouble())
                    loc2.world?.let { placeBlockNMSFaster(loc2.blockX, loc2.blockY, loc2.blockZ, 1, it) }
                }
            }

        }
    }


    // 130k blocks but doesnt show up unless relog
    /*
    public fun placeBlockNMS(x: Int, y: Int, z: Int, mat: Int, world: World) {
        // Get the world as a NMS varible and the chunk that the location is in
        var nmsWorld = (world as CraftWorld).handle
        var nmsChunk = nmsWorld.getChunkAt(x shr 4, z shr 4)
        val bp = BlockPosition(x, y, z)
        val ibd: IBlockData = net.minecraft.server.v1_15_R1.Block.getByCombinedId(mat)
        //nmsWorld.setTypeAndData(bp, ibd, 2)
        //nmsChunk.a(bp, ibd)
        nmsChunk.setType(bp, ibd, false);
    }
*/

    // Using 1.15.2 I got 4mil bps
    // This is likely the fastest way
    public fun placeBlockNMSFaster(x: Int, y: Int, z: Int, mat: Int, world: World) {
        // Get the world as a NMS varible and the chunk that the location is in
        var nmsWorld = (world as CraftWorld).handle
        var nmsChunk = nmsWorld.getChunkAt(x shr 4, z shr 4)
        val ibd: IBlockData = net.minecraft.server.v1_15_R1.Block.getByCombinedId(mat)
        var cs = nmsChunk.getSections()[y shr 4]
        if (cs == nmsChunk){
            cs = ChunkSection(y shr 4 shl 4)
            nmsChunk.getSections()[y shr 4] = cs
        }
        cs.setType(x and 15, y and 15, z and 15, ibd)

    }


}