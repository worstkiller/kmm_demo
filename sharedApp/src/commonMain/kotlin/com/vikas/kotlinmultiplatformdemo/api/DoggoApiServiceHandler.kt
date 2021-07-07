package com.vikas.kotlinmultiplatformdemo.api

import com.vikas.kotlinmultiplatformdemo.client.KmmDemoClient
import com.vikas.kotlinmultiplatformdemo.client.NetworkConfig
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel
import io.ktor.client.request.*

/**
 * an api implementation for the Doggo service with basic setup forming the data
 */
class DoggoApiServiceHandler(private val kmmDemoDoggoClient: KmmDemoClient) : DoggoApiService {

    companion object {

        fun getDoggoApiServiceHandler(): DoggoApiServiceHandler {
            return DoggoApiServiceHandler(KmmDemoClient(NetworkConfig.DOGGO_ENDPOINT))
        }

    }

    override suspend fun getDoggoList(apiPath: String, count: Int): List<DoggoResponseModel> {
        return kmmDemoDoggoClient.getClient().get {
            url(apiPath)
            parameter("limit", count)
            //add other custom params here like header and query param etc if needed
        }
    }
}