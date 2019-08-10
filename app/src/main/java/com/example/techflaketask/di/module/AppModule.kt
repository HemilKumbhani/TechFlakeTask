package com.example.techflaketask.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.techflaketask.data.DbHelper
import com.example.techflaketask.data.*
import com.example.techflaketask.data.api.ApiClient

import com.example.techflaketask.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule constructor(val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }
    @Provides
    @Singleton
    internal fun provideApiService(): ApiServices {
        return ApiClient().getApiServices()
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "TechFlake_Ratings.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManger): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }
}