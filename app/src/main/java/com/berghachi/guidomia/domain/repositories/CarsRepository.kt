package com.berghachi.guidomia.domain.repositories

import com.berghachi.guidomia.data.local.model.CarEntity
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun insertCarsToDbUseCase(cars: List<CarEntity>)
    suspend fun getCars(): Flow<List<CarEntity>>
    suspend fun getCarCount(): Int
}
