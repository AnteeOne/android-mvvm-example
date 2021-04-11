package com.anteeone.iconapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase
import kotlin.properties.Delegates

class ViewModelFactory(
    private val usecase: Usecase):
ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(IconListViewModel::class.java) -> {
                IconListViewModel(usecase) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(ItemDetailViewModel::class.java) -> {
                ItemDetailViewModel(usecase) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown viewmodel class")
            }
        }
    }
}