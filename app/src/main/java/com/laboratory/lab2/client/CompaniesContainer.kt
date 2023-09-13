package com.laboratory.lab2.client

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.laboratory.lab2.domain.models.Companies

@Composable
fun CompaniesContainer(companies: Companies?) {
    LazyColumn() {
        items(companies?.companies ?: emptyList()) { company ->
            CompanyCard(company = company)
        }
    }
}