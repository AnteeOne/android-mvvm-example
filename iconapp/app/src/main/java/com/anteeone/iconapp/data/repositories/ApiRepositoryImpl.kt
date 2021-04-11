package com.anteeone.iconapp.data.repositories

import android.util.Log
import com.anteeone.iconapp.data.network.ApiConstants
import com.anteeone.iconapp.data.network.IconApi
import com.anteeone.iconapp.domain.entities.IconEntity
import com.anteeone.iconapp.domain.entities.IconListEntity
import com.anteeone.iconapp.domain.repositories.ApiRepository
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.lang.IllegalStateException
import javax.inject.Inject

class ApiRepositoryImpl(var api: IconApi): ApiRepository {



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