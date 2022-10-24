package com.example.potenseek.DI

import com.example.potenseek.repository.AuthRepository
import com.example.potenseek.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthRepository() : AuthRepository = AuthRepository(query = FirebaseAuth.getInstance())

    @Singleton
    @Provides
    fun provideProfileRepository() : ProfileRepository = ProfileRepository(query = FirebaseFirestore.getInstance(), queryAuth = FirebaseAuth.getInstance())
}