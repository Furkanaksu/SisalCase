package com.furkan.sisalcase.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furkan.sisalcase.data.dto.Resource
import com.furkan.sisalcase.data.model.ListModel
import com.furkan.sisalcase.data.repository.GalleryRepo
import com.furkan.sisalcase.utils.NetworkUtil.Companion.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: GalleryRepo) : ViewModel() {
    private val _getData: MutableLiveData<Resource<ListModel>> = MutableLiveData()
    val getData: LiveData<Resource<ListModel>?>
        get() = _getData

    fun getData(searchQuery: String) = viewModelScope.launch {
        val response = dataRepository.getData(searchQuery)
        _getData.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<ListModel>): Resource<ListModel> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(getData.value?.data ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}