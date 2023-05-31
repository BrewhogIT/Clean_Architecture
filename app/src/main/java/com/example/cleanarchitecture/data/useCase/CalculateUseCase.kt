package com.example.cleanarchitecture.data.useCase

import com.example.cleanarchitecture.data.model.Result
import com.example.cleanarchitecture.domain.repository.CalculateRepository
import java.lang.IllegalArgumentException


// зачем этот класс в виде прослойки к вьюмодель?
class CalculateUseCase(private val calculateRepository: CalculateRepository) {
    val readAllResult = calculateRepository.readAllResult

    suspend fun addResult(result: Result){
        calculateRepository.addResult(result)
    }

    fun calculateResult(operation: String, operandFirst: Double, operandSecond: Double):Double {
        return when (operation) {
            "+" -> operandFirst + operandSecond
            "-" -> operandFirst - operandSecond
            "*" -> operandFirst * operandSecond
            "/" -> operandFirst / operandSecond

            else -> throw IllegalArgumentException("Invalid operation")
        }
    }
}