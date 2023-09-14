package com.laboratory.lab2.domain

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.laboratory.lab2.domain.models.Company


class DataService(private val documentId: String) {
    private val db = Firebase.firestore

    fun getCompaniesData(): Task<List<Company>?> {
        return db.collection("companies")
            .document(this.documentId)
            .get()
            .continueWith { task ->
                val document: DocumentSnapshot = getTaskResult(task)
                val companiesField = document.get("companies")
                Log.d("DataService", "companiesField: $companiesField")
                (companiesField is List<*>).let {
                    val gson = Gson()
                    val type = object : TypeToken<List<Company>>() {}.type
                    gson.fromJson<List<Company>>(gson.toJson(companiesField), type)
                }
            }
    }

    private fun getTaskResult(task: Task<DocumentSnapshot>): DocumentSnapshot {
        if (!task.isSuccessful) {
            throw task.exception!!
        }
        val document = task.result
        if (document == null || !document.exists()) {
            throw task.exception!!
        }
        return document
    }
}