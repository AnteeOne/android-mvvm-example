package com.anteeone.iconapp.domain.usecases.interfaces

import com.anteeone.iconapp.domain.entities.IconEntity

interface GetIconDetailUsecase: Usecase {

    suspend operator fun invoke(id: Int): IconEntity

}