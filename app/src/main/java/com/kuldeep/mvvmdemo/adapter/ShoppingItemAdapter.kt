package com.kuldeep.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kuldeep.mvvmdemo.R
import com.kuldeep.mvvmdemo.ShoppingItem
import com.kuldeep.mvvmdemo.ShoppingViewModel
import com.kuldeep.mvvmdemo.databinding.RecShoppingItemBinding

class ShoppingItemAdapter(
    var item: List<ShoppingItem>,
    var viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.RecViewHolder>() {

    lateinit var binding: RecShoppingItemBinding

    inner class RecViewHolder(val binding: RecShoppingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.rec_shopping_item, parent, false)

        return RecViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {

        val currentPositions = item[position]

        holder.binding.itemName.text = currentPositions.name
        holder.binding.itemAmount.text = currentPositions.amount.toString()

        holder.binding.ivAdd.setOnClickListener {
            currentPositions.amount++
            viewModel.upsert(currentPositions)
        }

        holder.binding.ivMinus.setOnClickListener {
            if (currentPositions.amount>0) {
                currentPositions.amount--
                viewModel.upsert(currentPositions)
            }
        }

        holder.binding.ivDelete.setOnClickListener {
            viewModel.delete(currentPositions)
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }

}