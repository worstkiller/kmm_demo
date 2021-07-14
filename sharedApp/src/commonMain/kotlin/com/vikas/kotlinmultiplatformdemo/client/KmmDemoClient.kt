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
import kotlin.native.concurrent.ThreadLocal


/**
 * network client for the api calls, provided with a base host, if neeeded to provide another host
 * pass different host and should work for the new host.
 * make sure to change in the service level implementation
 */
@ThreadLocal
object KmmDemoClient {

    private const val kmmHost: String = NetworkConfig.DOGGO_ENDPOINT

    private val kmmDemoLogger: KmmDemoLogger by lazy {
        KmmDemoLogger()
    }

    private val httpClient: HttpClient by lazy {
        createHttpClient()
    }

    fun getClient() = httpClient

    private fun createHttpClient() = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(jsonInstance)
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

    private val jsonInstance = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

}