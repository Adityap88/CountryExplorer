package com.research.adverb_assigment.di

import android.content.Context
import androidx.room.Room
import com.research.adverb_assigment.data.database.CountryDatabase
import com.research.adverb_assigment.util.Constants.Companion.DATABASE_NAME

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CountryDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: CountryDatabase) = database.countriesDao()
}