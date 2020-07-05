package com.learnwright.kotlindi.di.application

import com.learnwright.kotlindi.di.networking.StackOverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {
    @Singleton
    @Provides
    fun getRetrofit() = Retrofit.Builder().baseUrl(STACKOVERFLOWAPI).addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun getStackoverflowApi(retrofit: Retrofit) = retrofit.create(StackOverflowApi::class.java)


    companion object {
        private const val STACKOVERFLOWAPI = "https://api.stackexchange.com/2.2/"
    }
}