package com.example.techflaketask.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.techflaketask.data.AppDataManger
import com.example.techflaketask.di.ActivityContext
import com.example.techflaketask.ui.Home.HomeViewModel
import com.example.techflaketask.utils.rx.AppSchedulerProvider
import com.example.techflaketask.utils.rx.SchedulerProvider


import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule constructor(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    internal fun provideHomeViewModel(
        appDataManger: AppDataManger,
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): HomeViewModel =
        HomeViewModel(appDataManger, schedulerProvider, compositeDisposable)


}