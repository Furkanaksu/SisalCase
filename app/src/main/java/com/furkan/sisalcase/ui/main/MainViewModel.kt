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
    val _getData: MutableLiveData<Resource<ListModel>> = MutableLiveData()
    val getData: LiveData<Resource<ListModel>?>
        get() = _getData
    var searchNewsResponse: ListModel? = null

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error



    fun getData(searchQuery: String, context :Context) = viewModelScope.launch {
        safeSearchNewCall(searchQuery, context)
    }

    private suspend fun safeSearchNewCall(searchQuery: String, context: Context){
        _getData.postValue(Resource.Loading())
        try{
            if(hasInternetConnection(context)){
                val response = dataRepository.getData(searchQuery)
                _getData.postValue(handleSearchNewsResponse(response))
            }
            else
                _getData.postValue(Resource.Error("No Internet Connection"))
        }
        catch (ex: Exception){
            when(ex){
                is IOException -> _getData.postValue(Resource.Error("Network Failure"))
                else -> _getData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    fun handleSearchNewsResponse(response: Response<ListModel>): Resource<ListModel> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}