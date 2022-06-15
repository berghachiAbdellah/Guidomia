package com.berghachi.guidomia.data.repositories

import com.berghachi.guidomia.data.local.dao.CarDao
import com.berghachi.guidomia.data.local.model.CarEntity
import com.berghachi.guidomia.domain.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val carDao: CarDao
) : CarsRepository {
    override suspend fun insertCarsToDbUseCase(cars: List<CarEntity>) {
        carDao.insertCars(cars)
    }

    override suspend fun getCars(): Flow<List<CarEntity>> {
        return carDao.getCars()
    }

    override suspend fun getCarCount(): Int {
        return carDao.getCarCount()
    }

}