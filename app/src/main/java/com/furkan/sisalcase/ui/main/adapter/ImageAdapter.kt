package com.furkan.sisalcase.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.furkan.sisalcase.R
import com.furkan.sisalcase.data.model.ChildrenDetailModel
import com.furkan.sisalcase.data.model.ChildrenModel
import com.furkan.sisalcase.utils.loadImage
import java.lang.Exception

class ImageAdapter(
    private val mDataSet: ArrayList<ChildrenModel>? = arrayListOf(),
    private val itemClickDetail: ((ChildrenDetailModel) -> Unit)?
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mDataSet != null) {
            val data = mDataSet[position].data

            if (!data?.thumbnail.isNullOrEmpty())
            {
                try {
                    data?.thumbnail?.let { holder.image.loadImage(it) }
                }
                catch (e:Exception)
                {
                    e.message?.let { Log.e("Error", it) }
                }

            }
            else if (!data?.url_overridden_by_dest.isNullOrEmpty())
            {
                try {
                    data?.url_overridden_by_dest?.let { holder.image.loadImage(it) }
                }
                catch (e:Exception)
                {
                    e.message?.let { Log.e("Error", it) }
                }
            }

            holder.image.setOnClickListener {
                itemClickDetail?.let { it1 ->
                    if (data != null) {
                        it1(data)
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return mDataSet?.size ?: 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}