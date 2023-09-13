package com.laboratory.lab2.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.laboratory.lab2.domain.models.Companies

class DataService(private val documentId: String) {
    private val db = Firebase.firestore

    fun getCompaniesData(): Task<Companies?> {
        return db.collection("companies")
            .document(documentId)
            .get()
            .continueWith { task ->
                handleCompaniesDataTask(task)
            }
    }

    private fun handleCompaniesDataTask(task: Task<DocumentSnapshot>): Companies? {
        if (!task.isSuccessful) {
            return Companies(emptyList())
        }
        val result = task.result
        if (result != null) {
            val gson = Gson()
            val companies = gson.toJson(result)
            return gson.fromJson(companies, Companies::class.java)
        }
        return Companies(emptyList())
    }


}