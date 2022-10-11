package com.example.bitfitt

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DayDao{
    @Query("SELECT * FROM dayTable")
    fun getAll(): Flow<List<DayEntity>>

    @Insert
    fun insertAll(days: List<DayEntity>)

    @Insert
    fun insert(day: DayEntity)

    @Query("DELETE FROM dayTable")
    fun deleteAll()

}
