package com.vikas.kotlinmultiplatformdemo.client

import com.vikas.kotlinmultiplatformdemo.utils.KmmDemoLogger
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


/**
 * network client for the api calls, provided with a base host, if neeeded to provide another host
 * pass different host and should work for the new host.
 * make sure to change in the service level implementation
 */
class KmmDemoClient(private val kmmHost: String = NetworkConfig.ENDPOINT) {

    private val kmmDemoLogger: KmmDemoLogger by lazy {
        KmmDemoLogger()
    }

    private val httpClient: HttpClient by lazy {
        createHttpClient(createJson())
    }

    fun getClient() = httpClient

    private fun createHttpClient(json: Json) = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    kmmDemoLogger.log(message)
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 20_000
            requestTimeoutMillis = 20_000
            socketTimeoutMillis = 20_000
        }
        expectSuccess = false

        install(DefaultRequest) {
            url {
                host = kmmHost
                protocol = URLProtocol.HTTPS
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(NetworkConfig.HEADER_API_KEY, NetworkConfig.API_KEY)
        }
    }

    private fun createJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

}