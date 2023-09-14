package com.laboratory.lab2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laboratory.lab2.client.CompaniesContainer

@Composable
fun Main() {
    val viewModel: CompanyViewModel = viewModel()
    val companyData by viewModel.companyData.observeAsState()
    val appState by viewModel.appState.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (appState) {

            States.LOADING -> {
                Text(text = "Loading...")
            }

            States.COMPLETED -> {
                Log.d("Main", "companyData: $companyData")
                CompaniesContainer(
                    companies = companyData
                )

            }

            States.ERROR -> {
                Text(text = "Error")
            }

            else -> Unit
        }
    }
}