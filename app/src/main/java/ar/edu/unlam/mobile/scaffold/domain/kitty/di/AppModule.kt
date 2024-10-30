package ar.edu.unlam.mobile.scaffold.domain.kitty.di

import ar.edu.unlam.mobile.scaffold.data.kitty.repository.KittyRepository
import ar.edu.unlam.mobile.scaffold.domain.kitty.services.KittyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideKittyService(repository: KittyRepository): KittyService {
        return KittyService(repository)
    }
}
