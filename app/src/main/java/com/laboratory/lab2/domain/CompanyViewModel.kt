package com.laboratory.lab2.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.lab2.domain.models.Companies

class CompanyViewModel : ViewModel() {
    private val companyService = DataService(documentId = "QLuXfgm61ladxyIpyAxi")

    private val companiesMutableLiveData = MutableLiveData<Companies>()
    private val statesMutableLiveData = MutableLiveData<States>()

    val companyData: LiveData<Companies> = companiesMutableLiveData
    val appState: LiveData<States> = statesMutableLiveData

    private fun loadCompanyData() {
        statesMutableLiveData.value = States.LOADING
        companyService.getCompaniesData()
            .addOnSuccessListener { companies ->
                companiesMutableLiveData.value = companies?.let {
                    Companies(companies = companies)
                }
                statesMutableLiveData.value = States.SUCCESS
            }
            .addOnFailureListener {
                statesMutableLiveData.value = States.ERROR
            }
    }

    init {
        loadCompanyData()
    }
}