package com.anteeone.iconapp.data.models.network


import com.anteeone.iconapp.domain.entities.IconListEntity
import com.google.gson.annotations.SerializedName

private val DEFAULT_PREVIEW_LOGO_URL = "https://cdn1.iconfinder.com/data/icons/bnw/16x16/apps/1_kmenu.png"

data class IconListModel(
    @SerializedName("icons")
    val icons: List<Icon>,
    @SerializedName("total_count")
    val totalCount: Int
) {


    data class Icon(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("containers")
        val containers: List<Container>,
        @SerializedName("icon_id")
        val iconId: Int,
        @SerializedName("is_icon_glyph")
        val isIconGlyph: Boolean,
        @SerializedName("is_premium")
        val isPremium: Boolean,
        @SerializedName("published_at")
        val publishedAt: String,
        @SerializedName("raster_sizes")
        val rasterSizes: List<RasterSize>,
        @SerializedName("styles")
        val styles: List<Style>,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("type")
        val type: String
    ) {
        data class Category(
            @SerializedName("identifier")
            val identifier: String,
            @SerializedName("name")
            val name: String
        )

        data class Container(
            @SerializedName("download_url")
            val downloadUrl: String,
            @SerializedName("format")
            val format: String
        )

        data class RasterSize(
            @SerializedName("formats")
            val formats: List<Format>,
            @SerializedName("size")
            val size: Int,
            @SerializedName("size_height")
            val sizeHeight: Int,
            @SerializedName("size_width")
            val sizeWidth: Int
        ) {
            data class Format(
                @SerializedName("download_url")
                val downloadUrl: String,
                @SerializedName("format")
                val format: String,
                @SerializedName("preview_url")
                val previewUrl: String
            )
        }

        data class Style(
            @SerializedName("identifier")
            val identifier: String,
            @SerializedName("name")
            val name: String
        )
    }

    fun mapToIconListEntity(): IconListEntity =
        mutableListOf<IconListEntity.IconListMember>().let {
            for(icon in icons){
                it.add(
                    IconListEntity.IconListMember(
                        icon.iconId,
                        icon.isPremium,
                        if (icon.rasterSizes.isNotEmpty())
                            icon.rasterSizes[0].formats[0].previewUrl
                        else DEFAULT_PREVIEW_LOGO_URL)
                )
            }
            IconListEntity(it)
        }

}