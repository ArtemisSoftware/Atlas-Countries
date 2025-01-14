package com.artemissoftware.atlascountries.data.repository

import com.apollographql.apollo3.ApolloClient
import com.artemissoftware.CountriesQuery
import com.artemissoftware.CountryQuery
import com.artemissoftware.atlascountries.data.mapper.toCountry
import com.artemissoftware.atlascountries.data.mapper.toDetail
import com.artemissoftware.atlascountries.domain.models.Country
import com.artemissoftware.atlascountries.domain.models.Detail
import com.artemissoftware.atlascountries.domain.repository.CountryRepository


class CountryRepositoryImpl(private val apolloClient: ApolloClient):CountryRepository {

    override suspend fun getCountries(): List<Country> {
        return (apolloClient
                    .query(CountriesQuery())
                    .execute()
                    .data
                    ?.countries
                    ?.map { it.toCountry() }
                    ?: emptyList<Country>()
                ).sortedBy { it.name }

    }

    override suspend fun getCountry(code: String): Detail? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetail()
    }
}