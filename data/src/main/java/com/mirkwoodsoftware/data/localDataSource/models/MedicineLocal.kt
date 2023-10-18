package com.mirkwoodsoftware.data.localDataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirkwoodsoftware.data.localDataSource.room.LocalDbTableNames.MEDICINE_TABLE_NAME

@Entity(tableName = MEDICINE_TABLE_NAME)
data class MedicineLocal(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "url") val url: String,
)
