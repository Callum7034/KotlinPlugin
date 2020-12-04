# KotlinPlugin
Kotlin Spigot Plugin - Challenge

"So the task is:
You have to find out the fastest way to set tons of blocks without causing lag to the server.
Version doesn't matter.
Code must be in Kotlin.

You have two days to complete this task. Whenever finished create a benchmark with 10mil 
blocks and then create a git repository and lemme know!

Have fun,
Hopefully you'll learn a lot about minecraft itself in these two days."

*Highest performace Tests were done with 6gb of RAM (on server) and on a Ryzen 5 2600

Method I used was setting chunk sections to blocks, through NMS
I managed to get around 8 million blocks per second at my peak, which took just over 2 seconds to place all 10 million blocks
This is signifigantly faster than Bukkit's block.setType() method which does 50-80k blocks per second taking over 2 minutes to place all 10 million.

At first I played around on minecraft/spigot version 1.12.2 and tried to place blocks without physics and without updating the lighting engine/renderer.
This did not work too well with only 80k-130k blocks per second. While playing around with NMS and looking at very little documentation that there is 
I attempted to make my method more efficient but failed due to many of the methods in Chunks and World being not unobfuscated well, so I decided to update to MC/Spigot 1.15.2
This greatly increased the speed of the orginal NMS test to 200 - 300k Blocks per second ~40 seconds to place 10 mil blocks.
I then decided to find a new way to place blocks that was hopefully faster. This is where I started into the placing with chunk sections in NMS.
By spiltting a chunk into vertical segments and checking to see if the block needing to be placed was inside this segment I could much more efficently
place all of the blocks by doing this I got an average of 8 million blocks per second on my peak performace test taking just over 1 second to place all 10 million blocks
By decreasing/increasing the amount of RAM allocated to the server I was directly able to increase the speed at 2GB of RAM allocated I was getting around 4 million BPS (Blocks Per Second)
By increasing the RAM to 6GB I was consistainly getting 8 million BPS - Fasted time recorded to do 10 million blocks was 1173ms with a BPS of 8,525,000


Here is a link with the test running (Please note that OBS made it run slower than it should have with a large client lag spike): https://youtu.be/iUmX9L4sElo
