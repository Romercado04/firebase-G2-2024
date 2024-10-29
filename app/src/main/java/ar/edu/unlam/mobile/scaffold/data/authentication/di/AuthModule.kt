package ar.edu.unlam.mobile.scaffold.data.authentication.di

import ar.edu.unlam.mobile.scaffold.data.authentication.network.AuthNetworkImpl
import ar.edu.unlam.mobile.scaffold.data.authentication.network.AuthNetworkInterface
import ar.edu.unlam.mobile.scaffold.data.authentication.repository.AuthRepository
import ar.edu.unlam.mobile.scaffold.data.authentication.repository.AuthRepositoryInterface
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthNetworkImpl(auth: FirebaseAuth): AuthNetworkInterface = AuthNetworkImpl(auth)

    @Provides
    fun provideAuthRepository(authNet: AuthNetworkInterface): AuthRepositoryInterface = AuthRepository(authNet)
}