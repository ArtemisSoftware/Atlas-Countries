package com.artemissoftware.atlascountries.presentation.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.atlascountries.presentation.countries.composables.CountryDialog
import com.artemissoftware.atlascountries.presentation.countries.composables.CountryItem

@Composable
fun CountriesScreen(
    viewModel: CountriesViewModel = hiltViewModel()
) {
    CountriesContent(
        state = viewModel.state.collectAsState().value,
        event = viewModel::onTriggerEvent
    )
}

@Composable
private fun CountriesContent(
    state: CountriesState,
    event: (CountriesEvent) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.countries) { country ->
                        CountryItem(
                            country = country,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { event.invoke(CountriesEvent.SelectCountry(country.code)) }
                                .padding(16.dp)
                        )
                    }
                }

                if (state.selectedCountry != null) {
                    CountryDialog(
                        country = state.selectedCountry,
                        onDismiss = { event.invoke(CountriesEvent.CloseDialog) },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.White)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}



