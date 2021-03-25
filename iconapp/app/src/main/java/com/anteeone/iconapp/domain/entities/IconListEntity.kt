package com.anteeone.iconapp.domain.entities

data class IconListEntity(
    val iconList: List<IconListMember>
){

    data class IconListMember(
        val iconId: Int,
        val isPremium: Boolean,
        val previewUrl: String
    )
}
