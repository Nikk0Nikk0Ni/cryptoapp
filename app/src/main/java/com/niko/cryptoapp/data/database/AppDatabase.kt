package com.niko.cryptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.niko.cryptoapp.data.database.entity.CoinPriceEntity

@Database(entities = [CoinPriceEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao() : CoinPriceInfoDao
    companion object {
        private var db: AppDatabase? = null
        private val lock = Any()
        private const val DB_NAME = "main_db"
        fun getInstance(context: Context): AppDatabase {
            db?.let {
                return it
            }
            synchronized(lock) {
                db?.let {
                    return it
                }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
}