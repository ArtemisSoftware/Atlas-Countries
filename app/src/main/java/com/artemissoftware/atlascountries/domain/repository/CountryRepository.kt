package com.artemissoftware.atlascountries.domain.repository

import com.artemissoftware.atlascountries.domain.models.Country
import com.artemissoftware.atlascountries.domain.models.Detail

interface CountryRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getCountry(code: String): Detail?
}