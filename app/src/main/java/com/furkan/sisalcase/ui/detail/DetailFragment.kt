package com.furkan.sisalcase.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.furkan.sisalcase.R
import com.furkan.sisalcase.data.model.ChildrenDetailModel
import com.furkan.sisalcase.databinding.FragmentDetailBinding
import com.furkan.sisalcase.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import androidx.activity.OnBackPressedCallback
import com.furkan.sisalcase.utils.loadImage
import java.lang.Exception


class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.get("data") as ChildrenDetailModel

        binding.let {
            if (!data.title.isNullOrEmpty())
            {
                title.append(data.title)
            }
            if (!data.author_fullname.isNullOrEmpty())
            {
                author.append(data.author_fullname)
            }

            if (!data.preview?.images?.get(0)?.source?.url.isNullOrEmpty())
            {
                data.preview?.images?.get(0)?.source?.url?.replace("amp;","")?.let {
                    icon.loadImage(
                        it
                    )
                }
            }
            else
            {
                data.thumbnail?.let { it1 -> icon.loadImage(it1) }
            }
        }
    }


    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

}