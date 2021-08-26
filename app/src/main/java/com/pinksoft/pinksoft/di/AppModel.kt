package com.pinksoft.pinksoft.di

import android.app.Application
import com.pinksoft.pinksoft.db.AppDB
import com.pinksoft.pinksoft.db.DAO
import com.pinksoft.pinksoft.network.RetroServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModel {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): AppDB {
        return AppDB.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDB): DAO {
        return appDatabase.getDAO()
    }

    //val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): RetroServiceInterface {
        return retrofit.create(RetroServiceInterface::class.java)
    }

/*    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/
}