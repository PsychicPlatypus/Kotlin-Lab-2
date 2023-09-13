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
import com.laboratory.lab2.domain.models.Companies
import com.laboratory.lab2.domain.models.Company

@Composable
fun Main() {
    val viewModel: CompanyViewModel = viewModel()
    val companyData by viewModel.companyData.observeAsState()
    val appState by viewModel.appState.observeAsState()

    when (appState) {
        States.LOADING -> {
            Text(text = "Loading...")
        }

        States.COMPLETED -> {
            Log.d("Main", "companyData: $companyData")
            Text(text = "success $companyData")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CompaniesContainer(
                    companies = Companies(
                        companies = listOf(
                            Company(
                                id = 0,
                                title = "Company 1",
                                city = "City 1",
                                webpage = "Webpage 1",
                            ),
                            Company(
                                id = 1,
                                title = "Company 2",
                                city = "City 2",
                                webpage = "Webpage 2",
                            ),
                            Company(
                                id = 2,
                                title = "Company 3",
                                city = "City 3",
                                webpage = "Webpage 3",
                            ),
                        )
                    )
                )
            }
        }

        States.ERROR -> {
            Text(text = "Error")
        }

        else -> Unit
    }
}