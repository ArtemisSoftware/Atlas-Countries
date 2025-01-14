package com.artemissoftware.atlascountries.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.atlascountries.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel constructor(
    private val repository: CountryRepository
): ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        getCountries()
    }

    fun onTriggerEvent(event : CountriesEvent){
        when(event){
            CountriesEvent.CloseDialog -> dismissCountryDialog()
            is CountriesEvent.SelectCountry -> selectCountry(event.code)
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            val result = repository.getCountries()

            _state.update {
                it.copy(
                    countries = result,
                    isLoading = false
                )
            }
        }
    }

    private fun selectCountry(code: String) {
        viewModelScope.launch {

            val result = repository.getCountry(code)

            _state.update {
                it.copy(selectedCountry = result)
            }
        }
    }

    private fun dismissCountryDialog() {
        _state.update {
            it.copy(selectedCountry = null)
        }
    }

}