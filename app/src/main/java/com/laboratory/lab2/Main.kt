package com.laboratory.lab2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laboratory.lab2.client.CompaniesContainer
import com.laboratory.lab2.domain.CompanyViewModel
import com.laboratory.lab2.domain.States

@Composable
fun Main() {
    val viewModel: CompanyViewModel = viewModel()
    val companyData by viewModel.companyData.observeAsState()
    val state by viewModel.appState.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (state) {

            States.LOADING -> {
                Text(text = "Loading...")
            }

            States.SUCCESS -> {
                Log.d("Main", "companyData: $companyData")
                CompaniesContainer(
                    companies = companyData
                )

            }

            States.ERROR -> {
                Text(
                    text = "An Unexpected Error Occurred",
                    color = Color.Red
                )
            }

            else -> Unit
        }
    }
}