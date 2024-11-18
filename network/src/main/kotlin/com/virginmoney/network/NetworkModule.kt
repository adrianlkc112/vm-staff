package com.virginmoney.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VmRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {
    companion object {
        @Provides
        @Singleton
        @VmRetrofit
        fun provideVmRetrofit(): Retrofit =
            Retrofit
                .Builder()
                .baseUrl("https://61e947967bc0550017bc61bf.mockapi.io/api/v1")
                .build()
    }
}
