package com.mirkwoodsoftware.data.localDataSource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mirkwoodsoftware.data.localDataSource.room.LocalDbTableNames.CATEGORY_TABLE_NAME

@Entity(tableName = CATEGORY_TABLE_NAME)
data class CategoryLocal(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "examples") val examples: String,
    @ColumnInfo(name = "categoryType") val categoryType: String,
)
