package com.example.cleanarchitecture.presentation.ui.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.data.model.Result
import com.example.cleanarchitecture.databinding.ResultCardBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainHolder>() {
    private var resultList = emptyList<Result>()

    class MainHolder(view: View):RecyclerView.ViewHolder(view) {
        lateinit var binding: ResultCardBinding

        constructor(b:ResultCardBinding) : this(b.root) {
            binding = b
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ResultCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val currentResult = resultList[position]

        holder.binding.textResult.text = currentResult.value.toString()
    }

    fun setData(list: List<Result>){
        this.resultList = list
        notifyDataSetChanged()
    }
}