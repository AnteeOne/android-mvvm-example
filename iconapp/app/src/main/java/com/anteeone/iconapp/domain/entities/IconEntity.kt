package com.anteeone.iconapp.domain.entities

data class IconEntity(
    val iconId: Int,
    val isPremium: Boolean,
    val previewUrl: String,
    val authorName: String
)