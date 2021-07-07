package com.vikas.kotlinmultiplatformdemo.utils

import android.util.Log
import com.vikas.kotlinmultiplatformdemo.BuildConfig

/**
 * implementation for the @see[KmmDemoLogger] log function
 */
actual class KmmDemoLogger actual constructor() {
    actual fun log(message: String) {
        if (BuildConfig.DEBUG) Log.d(SharedAppConstants.SHARED_APP_NAME, message)
    }
}