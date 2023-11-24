package com.mirkwoodsoftware.data.localDataSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mirkwoodsoftware.data.localDataSource.models.CategoryLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getAll(): Flow<List<CategoryLocal>>

    @Query("SELECT * FROM Category WHERE name = :name LIMIT 1")
    fun getCategoryByName(name: String): Flow<CategoryLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryLocal)

    @Update
    suspend fun updateCategory(category: CategoryLocal)

    @Delete
    suspend fun delete(category: CategoryLocal)

    @Query("DELETE FROM Category")
    suspend fun deleteAll()
}