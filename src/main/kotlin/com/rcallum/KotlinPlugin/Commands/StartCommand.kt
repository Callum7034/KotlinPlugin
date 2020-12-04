package com.rcallum.KotlinPlugin.Commands

import com.rcallum.KotlinPlugin.BlockPlacer.nmsBlockPlacer
import com.rcallum.KotlinPlugin.BlockPlacer.setBlock
import com.rcallum.KotlinPlugin.Timer.Timer
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.*

class StartCommand : CommandExecutor {


    // Recieves Command
    override fun onCommand(sender: CommandSender, command: Command, p2: String, args: Array<out String>): Boolean
    {
        // Checking to make sure nothing is null
        if (command.name.equals("start")){
            if (args.size == 3){
                // Converting the args to integers
                val length = args[0].toIntOrNull()
                val width = args[1].toIntOrNull()
                val height = args[2].toIntOrNull()
                if ((length != null) && (width != null) && (height != null)) {

                    val start = Date() // This gets the inital date value for the timing

                    //Sending debug messages to the commandSender
                    sender.sendMessage("Length: " + length)
                    sender.sendMessage("Width: " + width)
                    sender.sendMessage("Height: " + height)

                    //DO FUNCTION

                    var player = (sender as Player)
                    var loc = player.getLocation()
                    /* Below is basic and gets 80,000 bps
                    val starter = setBlock()
                    starter.startPlacing(loc, length, width, height)
                    */

                    // Below is with nmsChunk placing and gets 100,000 - 130,000 bps
                    val nmsStarter = nmsBlockPlacer()
                    nmsStarter.startPlacing(loc, length, width, height)




                    // After blocks have placed time and doing the math
                    val end = Date()
                    val timer = Timer()
                    val blocks = (length*width*height)
                    var timetaken = timer.timeDiff(start, end)
                    if (timetaken.equals(0)){
                        timetaken+0.1
                    }
                    // Sending the timings message to the commandSender
                    Bukkit.broadcastMessage(c("&7Total Blocks: &e" + blocks))
                    Bukkit.broadcastMessage(c("&7Time taken: &e" + timetaken + "ms"))
                    Bukkit.broadcastMessage(c("&7Blocks Per Second: &e" + (((blocks)/(timetaken))*1000)))


                    // Chunk Reloading (Need this for the blocks to show up on the client)
                    val chunkx = player.location.chunk.x
                    val chunkz = player.location.chunk.z
                    for (i in 0..16){
                        for (a in 0..16) {
                            player.location.world?.refreshChunk(chunkx + i, chunkz + a)
                        }

                    }
                    return false
                }

            }
        sender.sendMessage("You must have 3 integer arguments. [Length, Widith, Height]")
        }
        return false
    }

    fun c(i:String) : String{
        return ChatColor.translateAlternateColorCodes('&', i)
    }
}