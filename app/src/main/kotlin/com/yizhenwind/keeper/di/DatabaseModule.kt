package com.yizhenwind.keeper.di

import android.content.Context
import androidx.paging.PagingConfig
import androidx.room.Room
import com.yizhenwind.keeper.data.database.KeeperDatabase
import com.yizhenwind.keeper.data.database.dao.AccountDao
import com.yizhenwind.keeper.data.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "keeper.db"

    @Provides
    @Singleton
    fun provideKeeperDatabase(@ApplicationContext appContext: Context): KeeperDatabase =
        Room.databaseBuilder(appContext, KeeperDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun providePagingConfig(): PagingConfig =
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 3,
            enablePlaceholders = false
        )

    @Provides
    @Singleton
    fun provideAccountDao(database: KeeperDatabase): AccountDao =
        database.accountDao()

    @Provides
    @Singleton
    fun provideCharacterDao(database: KeeperDatabase): CharacterDao =
        database.characterDao()

}