package com.cts.galaxy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cts.galaxy.api.Resource
import com.cts.galaxy.api.models.GalaxyDTO
import com.cts.galaxy.api.models.GalaxyRequestDTO
import com.cts.galaxy.api.usecase.GalaxyUseCase
import kotlinx.coroutines.launch

/**
 * @author Praveen Kumar Sugumaran
 */
class GalaxyCollectionViewModel(private val galaxyUseCase: GalaxyUseCase) : ViewModel() {

    private val _state = MutableLiveData<Resource<GalaxyDTO>>()

    val currentState: LiveData<Resource<GalaxyDTO>> = _state

    fun fetchGalaxyCollectionInformation() {
        viewModelScope.launch {
            _state.value = Resource.loading(null)
            galaxyUseCase.invoke(
                this, GalaxyRequestDTO(
                    searchKey,
                    mediaType,
                    yearStart,
                    yearEnd
                )
            ) {
                _state.value = it
            }
        }
    }

    companion object {
        private const val searchKey = "milky way"
        private const val mediaType = "image"
        private const val yearStart = "2017"
        private const val yearEnd = "2022"
    }
}