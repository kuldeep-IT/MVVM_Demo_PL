package com.kuldeep.mvvmdemo.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import com.kuldeep.mvvmdemo.R
import com.kuldeep.mvvmdemo.ShoppingItem
import com.kuldeep.mvvmdemo.databinding.CustomDialogBinding

class AddItemDialog(
    var activity: Activity?,
    var addDialogListner: AddDialogListner
) : Dialog(activity!!) {

    lateinit var binding: CustomDialogBinding

    init {
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    /*constructor(context: Context) : this(context as Activity){

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_dialog, null, false)
        setContentView(binding.root)
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        binding.tvAdd.setOnClickListener {

            val name = binding.etNameD.text
            val amt = binding.etAmtD.text.toString()

            name.isNotEmpty().apply {
                val item = ShoppingItem(name.toString(), amt.toInt())
                addDialogListner.onAddButtonClicked(item)
                dismiss()
            }

        }

        binding.tvCancelD.setOnClickListener {
            dismiss()
        }
    }

}