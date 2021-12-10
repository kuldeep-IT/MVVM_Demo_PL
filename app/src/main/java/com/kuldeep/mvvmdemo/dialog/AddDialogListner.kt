package com.kuldeep.mvvmdemo.dialog

import com.kuldeep.mvvmdemo.ShoppingItem

interface AddDialogListner {

    fun onAddButtonClicked(item: ShoppingItem)

}