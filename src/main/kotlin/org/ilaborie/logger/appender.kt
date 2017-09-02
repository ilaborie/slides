package org.ilaborie.logger

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class LogInfo(private val level: Level, private val now: LocalDateTime, private val message: String) {
    private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSS")

    fun format() = "${timeFormatter.format(now)} ${level.highlight()} $message"
}

sealed class Appender {
    abstract fun write(level: Level, cause: Throwable)
    internal abstract fun write(level: Level, logInfo: LogInfo)
}

object ConsoleAppender : Appender() {
    override fun write(level: Level, cause: Throwable) {
        val out = if (level >= Level.ERROR) System.err else System.out
        cause.printStackTrace(out)
    }

    override fun write(level: Level, logInfo: LogInfo) {
        val out = if (level >= Level.ERROR) System.err else System.out
        out.println(logInfo.format())
    }
}