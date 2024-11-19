package com.virginmoney.people.data

import com.virginmoney.network.VmRetrofit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface PeopleModule {
    @Binds
    @Singleton
    fun bindPeopleRepo(vmPeopleRepo: VmPeopleRepo): PeopleRepo

    companion object {
        @Provides
        @Singleton
        internal fun providePeopleService(
            @VmRetrofit retrofit: Retrofit,
        ) = retrofit.create(PeopleService::class.java)
    }
}
