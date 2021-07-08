package com.vikas.kotlinmultiplatformdemo.utils

import platform.Foundation.NSLog

/**
 * ios implementation for the @see [KmmDemoLogger] log function
 */
actual class KmmDemoLogger actual constructor() {
    actual fun log(message: String) {
        if (Platform.isDebugBinary) NSLog(message, "")
    }
}