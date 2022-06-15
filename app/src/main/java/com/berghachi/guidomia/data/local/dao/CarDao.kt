package com.berghachi.guidomia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berghachi.guidomia.data.local.model.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(cars: List<CarEntity>)

    @Query("SELECT * FROM Car")
    fun getCars(): Flow<List<CarEntity>>

    @Query("SELECT count(*) FROM Car")
    suspend fun getCarCount(): Int

}