package com.example.hw4l7.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.hw4l7.data.AddedProductDaoImpl
import com.example.hw4l7.domain.AddedProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAddedProduct(preferences: SharedPreferences): AddedProductDao =
        AddedProductDaoImpl(preferences)
}