package com.laboratory.lab2.client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laboratory.lab2.domain.models.Company

@Composable
fun CompanyCard(company: Company) {
    ElevatedCard(
        modifier = Modifier
            .padding(all = 20.dp)
            .height(180.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Box() {
            CompanyCardContent(company)
        }
    }
}