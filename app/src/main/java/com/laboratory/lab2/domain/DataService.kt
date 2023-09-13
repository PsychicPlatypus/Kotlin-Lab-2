package com.laboratory.lab2.domain

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.laboratory.lab2.domain.models.Companies


// Create a class that takes in a document Id and fetches the data from Firestore
class DataService(private val documentId: String) {

    private val db = Firebase.firestore

    fun getCompaniesData(): Task<Companies?> {
        return db.collection("companies")
            .document(documentId)
            .get()
            .continueWith { task ->
//                Log.e("DataService", "Error getting documents.", task.exception)
                handleCompaniesDataTask(task)
            }
    }

    private fun handleCompaniesDataTask(task: Task<DocumentSnapshot>): Companies? {
        if (!task.isSuccessful) {
            Log.e("DataService", "Error getting documents.", task.exception)
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