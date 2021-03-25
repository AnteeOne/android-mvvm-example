package com.anteeone.iconapp.domain.usecases.interfaces

import com.anteeone.iconapp.domain.entities.IconEntity

interface GetIconDetailUsecase {

    suspend operator fun invoke(id: Int): IconEntity

}