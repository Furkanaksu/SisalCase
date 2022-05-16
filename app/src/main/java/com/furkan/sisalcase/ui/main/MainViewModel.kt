package com.furkan.sisalcase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.sisalcase.data.dto.ApiServices
import com.furkan.sisalcase.data.model.ListModel
import com.furkan.sisalcase.data.repository.GalleryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: ApiServices) : ViewModel() {
    private val _getData = MutableLiveData<ListModel>()
    val getData: LiveData<ListModel>
        get() = _getData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getData(key : String) =
        viewModelScope.launch {
            GalleryRepo(dataRepository).getData(key,{
                _getData.postValue(it)
            },{
                _error.postValue(it)
            }
            )
        }

    fun getLastData() : ListModel?{
        return _getData.value
    }
}