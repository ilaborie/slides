package org.ilaborie.logger

import org.ilaborie.logger.Level.*
import java.time.LocalDateTime


enum class Level {
    TRACE, DEBUG, INFO, WARN, ERROR;

    private val ESCAPE = '\u001B'
    private val RESET = "$ESCAPE[0m"
    private val GREY = "$ESCAPE[37m"
    private val GREEN = "$ESCAPE[32m"
    private val BLUE = "$ESCAPE[34m"
    private val ORANGE = "$ESCAPE[33m"
    private val RED = "$ESCAPE[31m"

    fun highlight(): String = when (this) {
        Level.TRACE -> GREY + this + RESET
        Level.DEBUG -> GREEN + this + RESET
        Level.INFO  -> BLUE + this + ' ' + RESET
        Level.WARN  -> ORANGE + this + ' ' + RESET
        Level.ERROR -> RED + this + RESET
    }
}

class Logger(val name: String, var level: Level = Level.INFO, var appender: Appender = ConsoleAppender) {

    fun trace(messageBuilder: () -> String) {
        log(TRACE, null, messageBuilder)
    }

    fun debug(messageBuilder: () -> String) {
        log(DEBUG, null, messageBuilder)
    }

    fun info(messageBuilder: () -> String) {
        log(INFO, null, messageBuilder)
    }

    fun warn(messageBuilder: () -> String) {
        log(WARN, null, messageBuilder)
    }

    fun warn(cause: Throwable, messageBuilder: () -> String) {
        log(WARN, cause, messageBuilder)
    }

    fun error(messageBuilder: () -> String) {
        log(ERROR, null, messageBuilder)
    }

    fun error(cause: Throwable, messageBuilder: () -> String) {
        log(ERROR, cause, messageBuilder)
    }

    private fun log(logLevel: Level, cause: Throwable?, messageBuilder: () -> String) {
        if (logLevel >= level
        ) {
            appender.write(logLevel, LogInfo(logLevel, LocalDateTime.now(), messageBuilder()))
            if (cause != null) appender.write(logLevel, cause)
        }
    }
}