package com.anteeone.iconapp.domain.usecases.interfaces

import com.anteeone.iconapp.domain.entities.IconListEntity

interface GetIconListUsecase {

    suspend operator fun invoke(count: Int): IconListEntity

}