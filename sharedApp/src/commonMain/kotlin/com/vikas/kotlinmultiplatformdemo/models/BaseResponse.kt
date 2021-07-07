package com.vikas.kotlinmultiplatformdemo.models

/**
 * base model for the api response
 */
class BaseResponse<out T>(
    val status: BaseViewState,
    val data: T? = null
) {

    companion object {
        const val ERROR_CODE_404 = 404 //default error code

        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(BaseViewState.SUCCESS, data)
        }

        fun <T> error(
            msg: String? = null,
            data: T? = null,
            errorCode: Int? = null
        ): BaseResponse<T> {
            return BaseResponse(BaseViewState.ERROR(msg, errorCode), data)
        }

        fun <T> loading(data: T? = null): BaseResponse<T> {
            return BaseResponse(BaseViewState.LOADING, data)
        }
    }

}