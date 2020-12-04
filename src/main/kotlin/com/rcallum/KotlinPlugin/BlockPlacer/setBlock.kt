package com.rcallum.KotlinPlugin.BlockPlacer

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material

class setBlock {

    // Loops through all the locations and starts the placement
    public fun startPlacing(loc: Location, length:Int, width:Int, height:Int){
        val location = loc.add(1.0, 0.0, 0.0)
        for (a in 0..length){
            for (b in 0..width){
                for (c in 0..height){
                    setBlock(location.clone().add(a.toDouble(), c.toDouble(), b.toDouble()), Material.STONE)
                }
            }

        }
    }

    // This method does roughly 80,000 blocks per second
    fun setBlock(block:Location, type:Material) {
        val loc = block.block
        loc.setType(type)
    }
}