package org.ilaborie.slides


fun <T> safe(default: T, dangerous: () -> T): T =
        try {
            dangerous()
        } catch (e: Throwable) {
            // e.printStackTrace()
            default
        }