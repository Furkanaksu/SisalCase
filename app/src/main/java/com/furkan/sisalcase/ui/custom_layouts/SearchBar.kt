package com.furkan.sisalcase.ui.custom_layouts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.furkan.sisalcase.databinding.CustomSearchBinding
import com.furkan.sisalcase.ui.base.BaseLinearLayout
import com.furkan.sisalcase.ui.base.Callback
import com.furkan.sisalcase.ui.base.CallbackObject

//┌──────────────────────────┐
//│ Created by Furkan Aksu   │
//│ ──────────────────────── │
//│ aksufurkan8@gmail.com    │
//│ ──────────────────────── │
//│ 17/03/2022 / 09:39       │
//└──────────────────────────┘

class SearchBar(context: Context, attrs: AttributeSet? = null) :
    BaseLinearLayout<CustomSearchBinding>(context, attrs) {

    var callbackOnTextChange: Callback<String>? = null


    override fun createView(inflater: LayoutInflater): CustomSearchBinding {
        return CustomSearchBinding.inflate(inflater, this, true)
    }

    override fun viewCreated(attrs: AttributeSet?) {

        binding.ivClose.setOnClickListener {
            binding.editTextSearch.text = null
            callbackOnTextChange?.invoke("")
        }
    }

    fun clearText(){
        binding.editTextSearch.setText("")
    }

}