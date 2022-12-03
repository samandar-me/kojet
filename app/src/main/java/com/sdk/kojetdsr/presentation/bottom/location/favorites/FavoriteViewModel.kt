package com.sdk.kojetdsr.presentation.bottom.location.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.use_cases.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<List<FavoriteLocationName>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<FavoriteLocationName>> get() = _state

    init {
        getAllFavoriteNames()
    }

    private fun getAllFavoriteNames() {
        viewModelScope.launch {
            useCases.getFavoriteNamesUseCase("").collect {
                _state.value = it
            }
        }
    }
}