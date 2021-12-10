package com.kuldeep.mvvmdemo

class ShoppingRepository(
    var db: ShoppingDatabase
) {

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().deleteItem(item)

    fun getAllShoppingItem() = db.getShoppingDao().getAllLItemList()

}