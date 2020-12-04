package com.rcallum.KotlinPlugin.Commands

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
    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean
    {
        // Checking to make sure nothing is null
        if (command != null) {
            if (sender != null) {
                // Checks if the command is "start"
                if (command.name.equals("start")){
                    if (args != null) {
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
                                if (loc == null){
                                    loc = Bukkit.getWorld("world").getBlockAt(0,0,0).getLocation()
                                }
                                val starter = setBlock()
                                starter.startPlacing(loc, length, width, height)

                                // After blocks have placed time and doing the math
                                val end = Date()
                                val timer = Timer()
                                val blocks = (length*width*height)
                                var timetaken = timer.timeDiff(start, end)
                                if (timetaken.equals(0)){
                                    timetaken+0.1
                                }
                                // Sending the timings message to the commandSender
                                sender.sendMessage(c("&7Total Blocks: &e" + blocks))
                                sender.sendMessage(c("&7Time taken: &e" + timetaken + "ms"))
                                sender.sendMessage(c("&7Blocks Per Second: &e" + (((blocks)/(timetaken))*1000)))
                                return false
                            }

                        }

                    }
                sender.sendMessage("You must have 3 integer arguments. [Length, Widith, Height]")
                }
            }
        }
        return false
    }

    fun c(i:String) : String{
        return ChatColor.translateAlternateColorCodes('&', i)
    }
}