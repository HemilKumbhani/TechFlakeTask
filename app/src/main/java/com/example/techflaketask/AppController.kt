package com.example.techflaketask

import android.content.Context
import android.location.Geocoder
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.techflaketask.data.ApiServices
import com.example.techflaketask.data.api.ApiClient
import com.example.techflaketask.di.component.DaggerAppComponent
import com.example.techflaketask.di.module.AppModule

import java.util.*


class AppController : MultiDexApplication() {

    companion object {
        lateinit var mAppController: AppController
        lateinit var mServices: ApiServices
    }

    lateinit var mAppComponent: DaggerAppComponent

    private var geocoder: Geocoder? = null

    override fun onCreate() {
        super.onCreate()

        mAppController = this
        mServices = ApiClient().getApiServices()


        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build() as DaggerAppComponent

        mAppComponent.inject(this)

    }



    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}