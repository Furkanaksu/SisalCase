package com.furkan.sisalcase.data.model
import java.io.Serializable

data class ListModel (
    val kind : String? = null,
    val data : DataModel? = null,
    val error: Int? = null,
    val message: String? = null,
        )


data class DataModel(
    val children : ArrayList<ChildrenModel>? = null,
    val after : String? = null,
)


data class ChildrenModel(
    val kind : String? = null,
    val data : ChildrenDetailModel? = null
)


data class ChildrenDetailModel(
    val subreddit : String? = null,
    val title : String? = null,
    val author_fullname: String? = null,
    val thumbnail : String? = null,
    val url_overridden_by_dest : String? = null,
    val url : String? = null,
    val preview : PreviewModel? = null
):Serializable

data class PreviewModel(
    val images : ArrayList<Images>? = null,
)

data class Images(
    val source : Source? = null,
)

data class Source(
    val url : String? = null,
)