package com.anteeone.iconapp.domain.repositories

import com.anteeone.iconapp.data.models.network.IconDetailModel
import com.anteeone.iconapp.data.models.network.IconListModel
import com.anteeone.iconapp.domain.entities.IconEntity
import com.anteeone.iconapp.domain.entities.IconListEntity

interface ApiRepository {

    suspend fun getIconList(count: Int): IconListEntity

    suspend fun getIconDetail(id: Int): IconEntity

}