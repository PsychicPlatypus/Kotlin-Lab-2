package com.laboratory.lab2.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.laboratory.lab2.domain.models.CompaniesT
import java.lang.reflect.Type


class DataService(private val documentId: String) {
    private val db = Firebase.firestore

    fun getCompaniesData(): Task<CompaniesT?> {
        return db.collection("companies")
            .document(this.documentId)
            .get()
            .continueWith { task ->
                val document: DocumentSnapshot = getTaskResult(task)
                val companiesField = document.get("companies")

                (companiesField is List<*>).let {
                    val gson = Gson()
                    val type: Type = object : TypeToken<CompaniesT>() {}.type
                    val serializedCompanies = gson.toJson(companiesField)
                    gson.fromJson(serializedCompanies, type)
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