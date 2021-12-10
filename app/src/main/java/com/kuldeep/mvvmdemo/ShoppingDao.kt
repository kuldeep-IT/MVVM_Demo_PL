package com.kuldeep.mvvmdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ShoppingDao {

    @Insert(onConflict = REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("SELECT * From shopping_table")
    fun getAllLItemList() : LiveData<List<ShoppingItem>>
}