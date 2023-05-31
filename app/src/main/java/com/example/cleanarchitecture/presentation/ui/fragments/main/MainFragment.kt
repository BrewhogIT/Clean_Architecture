package com.example.cleanarchitecture.presentation.ui.fragments.main

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.data.dao.ResultDao
import com.example.cleanarchitecture.data.database.ResultDatabase
import com.example.cleanarchitecture.data.model.Result
import com.example.cleanarchitecture.data.repository.CalculateRepositoryImpl
import com.example.cleanarchitecture.databinding.FragmentMainBinding
import com.example.cleanarchitecture.data.useCase.CalculateUseCase
import com.example.cleanarchitecture.presentation.ui.fragments.main.viewModel.CalculateViewModelFactory
import com.example.cleanarchitecture.presentation.ui.fragments.main.viewModel.MainViewModel

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    val viewModel:MainViewModel by viewModels { CalculateViewModelFactory(activity?.application ?: Application()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())


        binding.dividedButton.setOnClickListener {
            calculate(binding.dividedButton.text.toString())
        }

        binding.multiplyButton.setOnClickListener {
            calculate(binding.multiplyButton.text.toString())
        }

        binding.plusButton.setOnClickListener {
            calculate(binding.plusButton.text.toString())
        }

        binding.minusButton.setOnClickListener {
            calculate(binding.minusButton.text.toString())
        }

        viewModel.resultData.observe(viewLifecycleOwner){
            binding.resultText.text = it.toString()

            addResultToDatabase(it)
        }

        viewModel.readAllData.observe(viewLifecycleOwner){
            adapter.setData(it)
        }

    }

    fun addResultToDatabase(r: Double){
        val result = Result(0,r)
        viewModel.addResult(result)
    }

    fun calculate(operation: String){
        val firstOperand = binding.firstText.text.toString().toDouble()
        val secondOperand = binding.secondText.text.toString().toDouble()

        viewModel.calculate(operation,firstOperand,secondOperand)
    }

}