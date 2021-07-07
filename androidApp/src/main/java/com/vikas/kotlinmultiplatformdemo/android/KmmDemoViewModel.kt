package com.vikas.kotlinmultiplatformdemo.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.kotlinmultiplatformdemo.models.BaseViewState
import com.vikas.kotlinmultiplatformdemo.models.DoggoResponseModel
import com.vikas.kotlinmultiplatformdemo.repository.DoggoListRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * viewmodel class for managing the data app level
 */
class KmmDemoViewModel(private val doggoListRepository: DoggoListRepository = DoggoListRepository.getInstance()) :
    ViewModel() {

    private val _doggoList = MutableLiveData<List<DoggoResponseModel>>()
    val doggoList: LiveData<List<DoggoResponseModel>> = _doggoList

    private val _apiStatus = MutableLiveData<BaseViewState>()
    val baseViewState: LiveData<BaseViewState> = _apiStatus

    init {
        getDoggoList()
    }

    private fun getDoggoList() {
        viewModelScope.launch {
            doggoListRepository.getDoggoList(15).collect {
                when (it.status) {
                    is BaseViewState.ERROR -> {
                        //do something here on error
                        _apiStatus.value = it.status
                    }
                    is BaseViewState.LOADING -> {
                        //do something here on loading
                        _apiStatus.value = it.status
                    }
                    is BaseViewState.SUCCESS -> {
                        //process data received
                        _doggoList.value = it.data
                        _apiStatus.value = it.status
                    }
                }
            }
        }
    }

}