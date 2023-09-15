package com.laboratory.lab2.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.laboratory.lab2.domain.models.Company


@Composable
fun CompanyCardContent(company: Company) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 18.dp)
        ) {
            Text(
                text = company.title,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(18.dp),
                textAlign = TextAlign.Center,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 18.dp),
        ) {
            Text(
                text = "Location: ",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = company.city,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Text(
                text = company.webpage,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(end = 12.dp),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
