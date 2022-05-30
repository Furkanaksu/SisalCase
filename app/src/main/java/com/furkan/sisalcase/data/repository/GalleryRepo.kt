package com.furkan.sisalcase.data.repository


import com.furkan.sisalcase.data.dto.ApiServices
import com.furkan.sisalcase.data.model.ListModel
import retrofit2.Response
import javax.inject.Inject

class GalleryRepo @Inject constructor(private val apiService: ApiServices) {

    suspend fun getData(keyword: String): Response<ListModel> {
        return apiService.getData(keyword)
    }
}