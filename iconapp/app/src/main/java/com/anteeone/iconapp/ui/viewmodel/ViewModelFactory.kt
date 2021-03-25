package com.anteeone.iconapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase
import kotlin.properties.Delegates

class ViewModelFactory(
    private val usecase: Usecase):
ViewModelProvider.Factory {

    private var iconId: Int? = null

    constructor(usecase: Usecase, id:Int): this(usecase){
        iconId = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(IconListViewModel::class.java) -> {
                return IconListViewModel(usecase) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(ItemDetailViewModel::class.java) -> {
                return ItemDetailViewModel(usecase,iconId?:1) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown viewmodel class")
            }
        }
    }
}