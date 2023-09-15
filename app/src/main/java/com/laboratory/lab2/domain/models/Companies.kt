package com.laboratory.lab2.domain.models

typealias CompaniesT = List<Company>

data class Companies(
    val companies: CompaniesT = listOf()
)