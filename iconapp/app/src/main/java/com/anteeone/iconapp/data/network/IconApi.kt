package com.anteeone.iconapp.data.network

import com.anteeone.iconapp.data.models.network.IconDetailModel
import com.anteeone.iconapp.data.models.network.IconListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IconApi {

    @GET("iconsets/1/icons")
    suspend fun getIconList(
        @Query("count") count: Int
    ): IconListModel

    @GET("icons/{id}")
    suspend fun getIconDetail(
        @Path("id") id: Int
    ): IconDetailModel

}

