package com.example.amphabians.ui.theme

import android.app.Application
import com.example.amphabians.data.AppContainer
import com.example.amphabians.data.DefaultAppContainer

class AmphibiansPhotoApplication:Application() {
    lateinit var container:AppContainer
    override fun onCreate(){
        super.onCreate()
        container=DefaultAppContainer()
    }
}