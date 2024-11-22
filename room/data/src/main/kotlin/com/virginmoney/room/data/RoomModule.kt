package com.virginmoney.room.data

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
internal fun interface RoomModule {
    @Binds
    @Singleton
    fun bindRoomRepo(vmRoomRepo: VmRoomRepo): RoomRepo

    companion object {
        @Provides
        @Singleton
        internal fun provideRoomService(
            @VmRetrofit retrofit: Retrofit,
        ) = retrofit.create(RoomService::class.java)
    }
}
