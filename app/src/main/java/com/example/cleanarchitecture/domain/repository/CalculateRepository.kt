package com.example.cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.model.Result

interface CalculateRepository {
    val readAllResult: LiveData<List<Result>>

    suspend fun addResult(result: Result)

}