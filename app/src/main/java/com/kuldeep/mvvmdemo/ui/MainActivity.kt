package com.kuldeep.mvvmdemo.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuldeep.mvvmdemo.*
import com.kuldeep.mvvmdemo.adapter.ShoppingItemAdapter
import com.kuldeep.mvvmdemo.databinding.ActivityMainBinding
import com.kuldeep.mvvmdemo.databinding.RecShoppingItemBinding
import com.kuldeep.mvvmdemo.dialog.AddDialogListner
import com.kuldeep.mvvmdemo.dialog.AddItemDialog

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val db = ShoppingDatabase(this)
        val repository = ShoppingRepository(db)
        val viewModelFactory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingViewModel ::class.java)

        val adptr = ShoppingItemAdapter(listOf(), viewModel)

        binding.recyclerView.apply {
            adapter = adptr
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.getAllShoppingList().observe(this, Observer {

            adptr.item = it
            adptr.notifyDataSetChanged()

        })

        binding.fb.setOnClickListener {
            AddItemDialog( this , object : AddDialogListner {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}