/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

package kotlinx.cli

internal actual fun exitProcess(status: Int): Nothing {
    kotlin.system.exitProcess(status)
}

internal actual fun eprintln(message: String) {
    val lineBreak = when(Platform.osFamily) {
        OsFamily.LINUX, OsFamily.ANDROID, OsFamily.WASM, OsFamily.UNKNOWN -> "\n"
        OsFamily.IOS, OsFamily.MACOSX, OsFamily.TVOS, OsFamily.WATCHOS -> "\r"
        OsFamily.WINDOWS -> "\r\n"
    }
    val stderr = platform.posix.fdopen(2, "w")
    platform.posix.fprintf(stderr, "%s$lineBreak", message)
    platform.posix.fflush(stderr)
}