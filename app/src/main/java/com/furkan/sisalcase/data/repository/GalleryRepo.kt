package com.furkan.sisalcase.data.repository


import android.util.Log
import com.furkan.sisalcase.data.dto.ApiServices
import com.furkan.sisalcase.data.model.ListModel
import com.furkan.sisalcase.ui.base.CallbackObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepo @Inject constructor(private val apiService: ApiServices) {

    suspend fun getData(
        keyword: String,
        success: CallbackObject<ListModel>,
        failure: CallbackObject<String>
    ) {

        try {
            val call = apiService.getData(keyword)

            Log.d("dataaaa", call.toString())
            val filteredData = call.data?.children?.filter{!it.data?.url_overridden_by_dest.isNullOrBlank() || !it.data?.thumbnail.isNullOrBlank()}
            when {
                call.data?.children?.size?:0 < 1 -> {
                    failure.invoke(call.message.toString())
                }
                filteredData?.size?:0 < 1 ->
                {
                    failure.invoke(call.message.toString())
                }
                call.error != null -> {
                    failure.invoke(call.message.toString())
                }
                else -> {
                    success.invoke(call)
                }
            }
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
            failure.invoke(e.message.toString())
        }
    }

}