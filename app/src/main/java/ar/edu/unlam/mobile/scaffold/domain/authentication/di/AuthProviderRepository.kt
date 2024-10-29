package ar.edu.unlam.mobile.scaffold.domain.authentication.di

import ar.edu.unlam.mobile.scaffold.data.authentication.repository.AuthRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.authentication.services.AuthService
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.CreateNewAccount
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.GetCurrentUser
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithEmailAndPassword
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithGoogle
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignOut
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthProviderRepository {
    @Provides
    fun authProviderRepo(authRepository: AuthRepositoryInterface): AuthService {
        return AuthService(authRepository)
    }

    @Provides
    fun provideSignInWithGoogle(authService: AuthService): SignInWithGoogle {
        return authService
    }

    @Provides
    fun providesSignInWithEmailAndPassword(authService: AuthService): SignInWithEmailAndPassword {
        return authService
    }

    @Provides
    fun provideSignOut(authService: AuthService): SignOut {
        return authService
    }

    @Provides
    fun providesCreateNewAccount(authService: AuthService): CreateNewAccount {
        return authService
    }

    @Provides
    fun provideGetCurrentUser(authService: AuthService): GetCurrentUser {
        return authService
    }
}