package com.furkan.sisalcase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.sisalcase.data.dto.Resource
import com.furkan.sisalcase.data.model.ListModel
import com.furkan.sisalcase.data.repository.GalleryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: GalleryRepo) : ViewModel() {
    private val _getData: MutableLiveData<Resource<ListModel>> = MutableLiveData()
    val getData: LiveData<Resource<ListModel>?>
        get() = _getData

    fun getData(searchQuery: String) = viewModelScope.launch {
        val response = dataRepository.getData(searchQuery)
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                _getData.postValue(Resource.Success(resultResponse))
            }
        }
    }
}