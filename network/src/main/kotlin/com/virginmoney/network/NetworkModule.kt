package com.virginmoney.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CommonOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkMoshi

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
        fun provideVmRetrofit(
            @NetworkMoshi moshi: Moshi,
            @CommonOkHttpClient okHttpClient: OkHttpClient,
            @Named("VmBaseUrl") baseUrl: String,
        ): Retrofit =
            buildRetrofit(
                moshi,
                okHttpClient,
                baseUrl,
            )

        @Provides
        @Singleton
        @CommonOkHttpClient
        fun provideCommonOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()

        private fun buildRetrofit(
            moshi: Moshi,
            okHttpClient: OkHttpClient,
            baseUrl: String,
        ) = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        @Provides
        @Named("VmBaseUrl")
        fun provideVmBaseUrl() = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/"

        @Provides
        @Singleton
        fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        @Singleton
        @NetworkMoshi
        fun provideMoshi(): Moshi =
            Moshi
                .Builder()
                .build()
    }
}
