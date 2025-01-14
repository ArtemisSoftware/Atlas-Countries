package com.artemissoftware.atlascountries.presentation.countries

sealed interface CountriesEvent {
    data class SelectCountry(val code: String) : CountriesEvent
    data object CloseDialog: CountriesEvent
}