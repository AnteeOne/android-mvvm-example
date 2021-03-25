package com.anteeone.iconapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anteeone.iconapp.domain.entities.IconListEntity
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconListUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase
import kotlinx.coroutines.launch

class IconListViewModel(
    private val usecase: Usecase
) : ViewModel() {

    init {
        Log.d("anteetag", "IconListViewModel have initialised")
    }

    val mIconList : MutableLiveData<IconListEntity> =
        MutableLiveData<IconListEntity>().also {
            viewModelScope.launch {
                it.value = (usecase as GetIconListUsecase).invoke(50)
            }
        }

}