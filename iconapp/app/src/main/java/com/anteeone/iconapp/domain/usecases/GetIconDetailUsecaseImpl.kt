package com.anteeone.iconapp.domain.usecases

import com.anteeone.iconapp.domain.entities.IconEntity
import com.anteeone.iconapp.domain.repositories.ApiRepository
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconDetailUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase

class GetIconDetailUsecaseImpl(val apiRepository: ApiRepository): Usecase,GetIconDetailUsecase {

    override suspend operator fun invoke(id: Int): IconEntity{
        return apiRepository.getIconDetail(id)
    }

}