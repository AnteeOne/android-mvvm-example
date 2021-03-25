package com.anteeone.iconapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anteeone.iconapp.domain.entities.IconEntity
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconDetailUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconListUsecase
import com.anteeone.iconapp.domain.usecases.interfaces.Usecase
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    private val usecase: Usecase,
    private val id: Int
) : ViewModel() {

    init {
        Log.d("anteetag", "IconDetailViewModel have initialised")
    }

    val mIconDetail : MutableLiveData<IconEntity> = MutableLiveData<IconEntity>().also {
        viewModelScope.launch {
            it.value = (usecase as GetIconDetailUsecase).invoke(id)
        }
    }

}