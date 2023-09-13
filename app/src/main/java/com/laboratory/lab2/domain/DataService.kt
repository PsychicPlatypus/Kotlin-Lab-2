package com.laboratory.lab2.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.laboratory.lab2.domain.models.Companies

class DataService(private val documentId: String) {
    private val db = Firebase.firestore

    fun getCompaniesData(): Task<Companies> {
        return db.collection("companies")
            .document(documentId)
            .get()
            .continueWith { task ->
                val result = task.result
                if (result != null) {
                    val companies = result.toObject(Companies::class.java)
                    if (companies?.companies != null) {
                        companies
                    } else {
                        Companies(emptyList())
                    }
                } else {
                    Companies(emptyList())
                }
            }
    }

}