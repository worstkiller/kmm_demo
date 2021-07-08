package com.vikas.kotlinmultiplatformdemo.repository

import com.vikas.kotlinmultiplatformdemo.models.BaseResponse
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel
import com.vikas.kotlinmultiplatformdemo.scope.ScopeProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.native.concurrent.freeze

/**
 * providing a @see [DoggoListRepository] level implementation since flow is not directly available in ios,
 * instead we will convert our flow based api to callback based so that we can consume the functions
 * without worrying about data and error handling in an lambda function, while calling from ios viewmodel
 */
class DoggoListRepositoryImpl {

    private val doggoRepository: DoggoListRepository by lazy {
        DoggoListRepository.getInstance()
    }

    fun getDoggoList(
        count: Int,
        completionHandler: (BaseResponse<List<DoggoResponseModel>>) -> Unit
    ) {
        ScopeProvider.iosScope.launch {
            doggoRepository.getDoggoList(count).collect {
                completionHandler(it)
            }
        }
    }

}