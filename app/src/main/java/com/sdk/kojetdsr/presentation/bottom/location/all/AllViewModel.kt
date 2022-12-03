package com.sdk.kojetdsr.presentation.bottom.location.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.model.FavoriteLocationName
import com.sdk.domain.use_cases.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<AllState> = MutableStateFlow(AllState())
    val state: StateFlow<AllState> get() = _state

    init {
        getAllLocationNames()
    }

    private fun getAllLocationNames() {
        viewModelScope.launch {
            useCases.getLocationNamesUseCase("")
                .onStart {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                    delay(600L)
                }
                .catch { t ->
                    _state.update { it.copy(isLoading = false, error = t.message.toString()) }
                }
                .collect { list ->
                    _state.update { it.copy(isLoading = false, success = list) }
                }
        }
    }

    fun onEvent(event: AllEvent) {
        when (event) {
            is AllEvent.OnFavoriteClick -> {
                viewModelScope.launch {
                    useCases.updateFavLocationName(event.name)
                    if (event.name.isSaved) {
                        useCases.saveFavoriteNameUseCase(FavoriteLocationName(name = event.name.name))
                    } else {
                        useCases.deleteFavoriteNameUseCase(FavoriteLocationName(name = event.name.name))
                    }
                }
            }
        }
    }
}