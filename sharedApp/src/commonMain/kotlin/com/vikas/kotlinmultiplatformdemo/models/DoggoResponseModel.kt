package com.vikas.kotlinmultiplatformdemo.models

import kotlinx.serialization.Serializable

@Serializable
data class DoggoResponseModel(
    val breed_group: String? = null,
    val image: DoggoImage,
    val life_span: String,
    val name: String,
    val origin: String? = null,
    val temperament: String,
    val country_code: String? = null
)

@Serializable
data class DoggoImage(val url: String)
