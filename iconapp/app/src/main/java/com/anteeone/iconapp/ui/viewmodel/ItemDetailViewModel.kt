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
    private val usecase: Usecase
) : ViewModel() {


    var itemId: Int? = null

    init {
        Log.d("anteetag", "IconDetailViewModel have initialised")
    }

    val mIconDetail : MutableLiveData<IconEntity> = MutableLiveData<IconEntity>().also {
        viewModelScope.launch {
            it.value = itemId?.let { it1 -> (usecase as GetIconDetailUsecase).invoke(it1) }
        }
    }

}