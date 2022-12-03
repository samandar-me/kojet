package com.sdk.domain.model

data class UpdateFavLocationName(
    val id: Int,
    val name: String,
    val isSaved: Boolean
)