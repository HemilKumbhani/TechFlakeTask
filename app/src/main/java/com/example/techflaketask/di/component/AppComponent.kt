package com.example.techflaketask.di.component

import android.app.Application
import android.content.Context
import com.example.techflaketask.AppController
import com.example.techflaketask.data.AppDataManger

import com.example.techflaketask.di.ApplicationContext
import com.example.techflaketask.di.module.AppModule

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun appDataManager(): AppDataManger

    fun inject(app: AppController)

}