package com.rcallum.KotlinPlugin.Timer

import java.util.*

class Timer {

    // Gets the amount of milliseconds between 2 dates
    public fun timeDiff(from: Date, to:Date): Long {
        val fromtime = from.time
        val toTime = to.time
        return (toTime - fromtime)
    }
}