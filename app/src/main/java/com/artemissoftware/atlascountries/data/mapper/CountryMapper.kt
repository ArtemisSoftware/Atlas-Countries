package com.artemissoftware.atlascountries.data.mapper

import com.artemissoftware.CountriesQuery
import com.artemissoftware.CountryQuery
import com.artemissoftware.atlascountries.domain.models.Country
import com.artemissoftware.atlascountries.domain.models.Detail

fun CountryQuery.Country.toDetail(): Detail {
    return Detail(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toCountry(): Country {
    return Country(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
}