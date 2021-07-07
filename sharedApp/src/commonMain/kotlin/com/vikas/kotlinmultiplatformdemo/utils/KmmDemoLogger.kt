package com.vikas.kotlinmultiplatformdemo.utils

/**
 * function definition for the logging in ios and android
 * platform to be implemented to provide the implementation
 */
expect class KmmDemoLogger() {
    fun log(message: String)
}