package com.vikas.kotlinmultiplatformdemo.api

/**
 * api interface for doggo REST api
 */
interface DoggoApiService {

    companion object {
        private const val GET_BREEDS = "/v1/breeds"
    }

    suspend fun getDoggoList(apiPath: String = GET_BREEDS, count: Int): List<Any>

}