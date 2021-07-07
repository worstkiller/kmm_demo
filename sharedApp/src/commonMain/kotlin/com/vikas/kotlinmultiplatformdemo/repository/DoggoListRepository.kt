package com.vikas.kotlinmultiplatformdemo.repository

import com.vikas.kotlinmultiplatformdemo.utils.KmmDemoLogger
import com.vikas.kotlinmultiplatformdemo.api.DoggoApiErrorHandler
import com.vikas.kotlinmultiplatformdemo.api.DoggoApiServiceHandler
import com.vikas.kotlinmultiplatformdemo.models.BaseViewState
import com.vikas.kotlinmultiplatformdemo.models.BaseResponse
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * repository class for managing the data for doggo api and performing other common data operations
 */
class DoggoListRepository(private val doggoApiServiceHandler: DoggoApiServiceHandler) {

    companion object {

        fun getInstance(): DoggoListRepository {
            return DoggoListRepository(DoggoApiServiceHandler.getDoggoApiServiceHandler())
        }

    }

    fun getDoggoList(count: Int): Flow<BaseResponse<List<DoggoResponseModel>>> {
        return flow<BaseResponse<List<DoggoResponseModel>>> {
            //do repository level operation before consuming the data in the viewmodel or ui
            val doggoList = doggoApiServiceHandler.getDoggoList(count = count)
            if (doggoList.isNullOrEmpty()) {
                emit(BaseResponse.error(data = listOf(), msg = "No data found"))
            } else {
                emit(BaseResponse.success(data = doggoList))
            }
        }.catch { ex ->
            emit(DoggoApiErrorHandler.processError(ex))
            KmmDemoLogger().log(ex.message ?: "")
        }.onStart { emit(BaseResponse(BaseViewState.LOADING)) }
    }


}