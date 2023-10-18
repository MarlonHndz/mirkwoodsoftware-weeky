package com.mirkwoodsoftware.data.localDataSource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mirkwoodsoftware.data.localDataSource.models.MedicineLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM Medicine")
    fun getAll(): Flow<List<MedicineLocal>>

    @Query("SELECT * FROM Medicine WHERE id = :id LIMIT 1")
    fun getMedicineByID(id: String): Flow<MedicineLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicine: MedicineLocal)

    @Update
    suspend fun updateMedicine(medicine: MedicineLocal)

    @Delete
    suspend fun delete(medicine: MedicineLocal)

    @Query("DELETE FROM Medicine")
    suspend fun deleteAll()
}
