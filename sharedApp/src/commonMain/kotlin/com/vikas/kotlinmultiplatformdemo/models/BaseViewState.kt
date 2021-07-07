package com.vikas.kotlinmultiplatformdemo.models

/**
 * api status
 */
sealed class BaseViewState {
    object SUCCESS : BaseViewState()

    data class ERROR(
        val message: String? = "",
        val errorCode: Int? = BaseResponse.ERROR_CODE_404
    ) : BaseViewState()

    object LOADING : BaseViewState()
}