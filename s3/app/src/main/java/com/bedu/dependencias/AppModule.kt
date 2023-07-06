package com.bedu.dependencias

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("String1")
    fun provideTestString1() = "Texto inyectado"

    @Singleton
    @Provides
    @Named("randomNum")
    fun provideRandomNum() = (0..100).random().toString()
}