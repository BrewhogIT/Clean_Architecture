package com.example.cleanarchitecture.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.model.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResult(result: Result)

    @Query("SELECT * FROM result_table ORDER BY id ASC")
    fun readAllResults(): LiveData<List<Result>>
}