package com.sdk.kojetdsr.presentation.bottom.settings

sealed class SettingsEvent {
    data class ChangeTheme(val index: Int): SettingsEvent()
    data class ChangeLanguage(val lan: String): SettingsEvent()
    object GetState: SettingsEvent()
}