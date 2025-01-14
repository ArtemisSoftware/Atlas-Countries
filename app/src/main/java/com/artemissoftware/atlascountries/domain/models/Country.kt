package com.artemissoftware.atlascountries.domain.models

data class Country(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)