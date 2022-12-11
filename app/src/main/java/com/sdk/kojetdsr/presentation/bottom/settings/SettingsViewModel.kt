package com.sdk.kojetdsr.presentation.bottom.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.domain.use_cases.base.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: AllUseCases
): ViewModel() {
    private val _theme: MutableStateFlow<Int> = MutableStateFlow(0)
    val theme: StateFlow<Int> get() = _theme
    private val _language: MutableStateFlow<String> = MutableStateFlow("en")
    val language: MutableStateFlow<String> get() = _language

    init {
        viewModelScope.launch {
            useCases.getLanguageUseCase.invoke("").collect {
                _language.value = it
            }
        }
        viewModelScope.launch {
            useCases.getThemeUseCase.invoke("").collect {
                _theme.value = it
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.ChangeLanguage -> {
                viewModelScope.launch {
                    useCases.saveLanguageUseCase.invoke(event.lan)
                }
            }
            is SettingsEvent.ChangeTheme -> {
                viewModelScope.launch {
                    useCases.saveThemeUseCase.invoke(event.index)
                }
            }
        }
    }
}