package com.example.cleanarchitecture.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.dao.ResultDao
import com.example.cleanarchitecture.data.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class ResultDatabase : RoomDatabase(){

    abstract fun resultDao(): ResultDao

    companion object{
        @Volatile
        private var INSTANCE: ResultDatabase?= null

        fun getDatabase(context: Context): ResultDatabase{
            val tempDatabase = INSTANCE

            if (tempDatabase!= null){
                return tempDatabase
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "result_database")
                    .build()
                INSTANCE= instance
                return instance
            }
        }
    }
}