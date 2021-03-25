package com.anteeone.iconapp.domain.usecases

import com.anteeone.iconapp.domain.entities.IconListEntity
import com.anteeone.iconapp.domain.repositories.ApiRepository
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconListUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase

class GetIconListUsecaseImpl(val apiRepository: ApiRepository): Usecase, GetIconListUsecase {

    override suspend operator fun invoke(count: Int): IconListEntity {
        return apiRepository.getIconList(count)
    }

}