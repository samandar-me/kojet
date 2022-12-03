package com.sdk.kojetdsr.presentation.map.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_cases.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapDetailViewModel @Inject constructor(
    private val useCases: AllUseCases
): ViewModel() {
    fun saveLocationName(name: String) {
        viewModelScope.launch {
            useCases.saveLocationNameBaseUseCase.invoke(name)
        }
    }
}