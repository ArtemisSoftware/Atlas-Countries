package com.artemissoftware.atlascountries.di

import com.apollographql.apollo3.ApolloClient
import com.artemissoftware.atlascountries.data.repository.CountryRepositoryImpl
import com.artemissoftware.atlascountries.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryRepository {
        return CountryRepositoryImpl(apolloClient)
    }
}