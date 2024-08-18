package com.example.firebase.di

import com.example.firebase.Repository.AuthRepository
import com.example.firebase.Repository.DefaultAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthRepository() = DefaultAuthRepository() as AuthRepository
}