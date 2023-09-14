package com.laboratory.lab2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.lab2.domain.DataService
import com.laboratory.lab2.domain.models.Companies

class CompanyViewModel : ViewModel() {

    private val companyService = DataService(documentId = "QLuXfgm61ladxyIpyAxi")

    private val _companyData = MutableLiveData<Companies?>()
    private val _appState = MutableLiveData<States>()

    val companyData: LiveData<Companies?> = _companyData
    val appState: LiveData<States> = _appState

    init {
        loadCompanyData()
    }

    private fun loadCompanyData() {
        _appState.value = States.LOADING
        companyService.getCompaniesData()
            .addOnSuccessListener { companies ->
                if (companies != null) {
                    _companyData.value = Companies(companies = companies)
                    _appState.value = States.COMPLETED
                } else {
                    _appState.value = States.ERROR
                }
            }
            .addOnFailureListener {
                _appState.value = States.ERROR
            }
    }

}