package com.furkan.sisalcase.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.furkan.sisalcase.R
import com.furkan.sisalcase.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}