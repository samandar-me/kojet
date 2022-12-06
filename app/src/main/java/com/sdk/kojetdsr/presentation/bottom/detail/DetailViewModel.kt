package com.sdk.kojetdsr.presentation.bottom.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_cases.base.AllUseCases
import com.sdk.domain.util.Response
import com.sdk.kojetdsr.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: AllUseCases,
    private val networkUtils: NetworkUtils
) : ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> get() = _state

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnScreenLaunched -> {
                viewModelScope.launch {
                    useCases.getCurrentWeatherUseCase(event.name)
                        .collectLatest { response ->
                            when (response) {
                                is Response.Loading -> {
                                    _state.update {
                                        DetailState(isLoading = true)
                                    }
                                    delay(700L)
                                }
                                is Response.Error -> {
                                    _state.update {
                                        DetailState(
                                            isLoading = false,
                                            error = if (networkUtils.getNetworkStatus().value == true) response.error else "Check internet connection"
                                        )
                                    }
                                }
                                is Response.Success -> {
                                    _state.update {
                                        DetailState(isLoading = false, success = response.data)
                                    }
                                }
                            }
                        }
                }
            }
            is DetailEvent.OnDeleteClicked -> {
                viewModelScope.launch {
                    useCases.deleteFavNameUseCase(event.name.name)
                    useCases.deleteLocationNameUseCase(event.name)
                }
            }
            is DetailEvent.OnRefreshSwiped -> {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("@@@@ViewModelCleared")
    }
}