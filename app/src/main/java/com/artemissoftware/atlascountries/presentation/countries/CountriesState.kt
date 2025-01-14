package com.artemissoftware.atlascountries.presentation.countries

import com.artemissoftware.atlascountries.domain.models.Country
import com.artemissoftware.atlascountries.domain.models.Detail

data class CountriesState(
    val countries: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: Detail? = null
)
