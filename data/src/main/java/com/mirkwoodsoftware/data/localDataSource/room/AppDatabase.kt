package com.mirkwoodsoftware.data.localDataSource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mirkwoodsoftware.data.localDataSource.daos.CategoryDao
import com.mirkwoodsoftware.data.localDataSource.daos.MedicineDao
import com.mirkwoodsoftware.data.localDataSource.models.CategoryLocal
import com.mirkwoodsoftware.data.localDataSource.models.MedicineLocal

@Database(
    entities = [MedicineLocal::class, CategoryLocal::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MedicineDao(): MedicineDao
    abstract fun CategoryDao(): CategoryDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "weeky_database"
        const val DATABASE_VERSION = 1
    }
}
