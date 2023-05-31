package com.example.cleanarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.dao.ResultDao
import com.example.cleanarchitecture.data.model.Result
import com.example.cleanarchitecture.domain.repository.CalculateRepository
import java.lang.IllegalArgumentException

class CalculateRepositoryImpl(private val resultDao: ResultDao): CalculateRepository{

    override val readAllResult: LiveData<List<Result>> = resultDao.readAllResults()

    override suspend fun addResult(result: Result) {
        resultDao.addResult(result)
    }

}