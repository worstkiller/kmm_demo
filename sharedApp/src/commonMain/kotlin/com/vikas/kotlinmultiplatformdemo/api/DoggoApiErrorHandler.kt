package com.vikas.kotlinmultiplatformdemo.api

import com.vikas.kotlinmultiplatformdemo.models.BaseResponse
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel
import io.ktor.client.features.*

class DoggoApiErrorHandler {

    companion object {

        fun processError(throwable: Throwable): BaseResponse<List<DoggoResponseModel>> {
            return when (throwable) {
                is HttpRequestTimeoutException -> {
                    BaseResponse.error(msg = "could not connect to server", errorCode = 101)
                }
//                is java.net.UnknownHostException -> {
//                    BaseResponse.error(
//                        msg = "Please check your internet connectivity",
//                        errorCode = 103
//                    )
//                }
                else -> {
                    BaseResponse.error(msg = "Something went wrong", errorCode = 104)
                }
            }
        }

    }

}