package com.furkan.sisalcase.ui.main.adapter

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

class ImageAdapter(
    val mDataSet: ArrayList<ChildrenModel>? = arrayListOf(),
    val itemClickDetail: ((ChildrenDetailModel) -> Unit)?
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
            val data = mDataSet.get(position)?.data

            if (!data?.thumbnail.isNullOrEmpty())
            {
                Glide.with(holder.image.context)
                    .load(data?.thumbnail)
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(holder.image)
            }
            else if (!data?.url_overridden_by_dest.isNullOrEmpty())
            {
                Glide.with(holder.image.context)
                    .load(data?.url_overridden_by_dest)
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(holder.image)
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