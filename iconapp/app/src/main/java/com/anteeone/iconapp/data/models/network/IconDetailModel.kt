package com.anteeone.iconapp.data.models.network


import com.anteeone.iconapp.domain.entities.IconEntity
import com.google.gson.annotations.SerializedName

data class IconDetailModel(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("containers")
    val containers: List<Container>,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("iconset")
    val iconset: Iconset,
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

    data class Iconset(
        @SerializedName("are_all_icons_glyph")
        val areAllIconsGlyph: Boolean,
        @SerializedName("author")
        val author: Author,
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("icons_count")
        val iconsCount: Int,
        @SerializedName("iconset_id")
        val iconsetId: Int,
        @SerializedName("identifier")
        val identifier: String,
        @SerializedName("is_premium")
        val isPremium: Boolean,
        @SerializedName("license")
        val license: License,
        @SerializedName("name")
        val name: String,
        @SerializedName("published_at")
        val publishedAt: String,
        @SerializedName("styles")
        val styles: List<Style>,
        @SerializedName("type")
        val type: String,
        @SerializedName("website_url")
        val websiteUrl: String
    ) {
        data class Author(
            @SerializedName("author_id")
            val authorId: Int,
            @SerializedName("iconsets_count")
            val iconsetsCount: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("website_url")
            val websiteUrl: String
        )

        data class Category(
            @SerializedName("identifier")
            val identifier: String,
            @SerializedName("name")
            val name: String
        )

        data class License(
            @SerializedName("license_id")
            val licenseId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("scope")
            val scope: String,
            @SerializedName("url")
            val url: String
        )

        data class Style(
            @SerializedName("identifier")
            val identifier: String,
            @SerializedName("name")
            val name: String
        )
    }

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

    fun mapToIconEntity(): IconEntity =
        IconEntity(
            iconId,
            isPremium,
            if (rasterSizes.isNotEmpty())
                rasterSizes[0].formats[0].previewUrl
            else DEFAULT_PREVIEW_LOGO_URL,
            iconset.author.name)

    private val DEFAULT_PREVIEW_LOGO_URL = "https://cdn1.iconfinder.com/data/icons/bnw/16x16/apps/1_kmenu.png"
}