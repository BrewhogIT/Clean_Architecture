package com.example.cleanarchitecture.presentation.ui.fragments.main.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.dao.ResultDao
import com.example.cleanarchitecture.data.database.ResultDatabase
import com.example.cleanarchitecture.data.model.Result
import com.example.cleanarchitecture.data.repository.CalculateRepositoryImpl
import com.example.cleanarchitecture.data.useCase.CalculateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application ): ViewModel() {
    val resultData = MutableLiveData<Double>()
    val readAllData : LiveData<List<Result>>
    private val calculateUseCase: CalculateUseCase

    init{
        val resultDao = ResultDatabase.getDatabase(application).resultDao()
        val repository = CalculateRepositoryImpl(resultDao)
        calculateUseCase = CalculateUseCase(repository)
        readAllData = calculateUseCase.readAllResult
    }

    fun calculate(operation: String, operandFirst:Double, operandSecond: Double){
        val result = calculateUseCase.calculateResult(operation,operandFirst,operandSecond)
        resultData.value = result
    }

    fun addResult(result: Result){
        viewModelScope.launch(Dispatchers.IO){
            calculateUseCase.addResult(result)
        }
    }

}