package com.sdk.data.local.manager

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocalizationManager {
    fun setLanguage(context: Context, language: String) {
        context.resources.apply {
            val locale = Locale(language)
            val config = Configuration(configuration)

            context.createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            context.resources.updateConfiguration(config, displayMetrics)
        }
    }
}