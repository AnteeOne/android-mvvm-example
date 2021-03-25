package com.anteeone.iconapp.data.repositories

import android.util.Log
import com.anteeone.iconapp.data.models.network.IconDetailModel
import com.anteeone.iconapp.data.models.network.IconListModel
import com.anteeone.iconapp.data.network.ApiFactory
import com.anteeone.iconapp.domain.entities.IconEntity
import com.anteeone.iconapp.domain.entities.IconListEntity
import com.anteeone.iconapp.domain.repositories.ApiRepository
import java.lang.Exception
import java.lang.IllegalStateException

class ApiRepositoryImpl: ApiRepository {

    private val api = ApiFactory.iconApi

    override suspend fun getIconList(count: Int): IconListEntity {
        try{
            Log.println(Log.DEBUG,"anteetag","getting icon list...")
            return api.getIconList(count).mapToIconListEntity()
        }
        catch (e: Exception){
            throw IllegalStateException("Error with getting icon list",e)
        }

    }

    override suspend fun getIconDetail(id: Int): IconEntity {
        try{
            Log.println(Log.DEBUG,"anteetag","getting icon detail...")
            return api.getIconDetail(id).mapToIconEntity()
        }
        catch (e: Exception){
            throw IllegalStateException("Error with getting icon detail",e)
        }
    }
}