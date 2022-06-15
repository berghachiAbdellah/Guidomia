package com.berghachi.guidomia.domain.usecases

import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.domain.model.toCarEntities
import com.berghachi.guidomia.domain.repositories.CarsRepository
import javax.inject.Inject


class InsertCarsToDbUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend fun invoke(cars: List<CarItem>) {
        return carsRepository.insertCarsToDbUseCase(cars.toCarEntities())
    }
}
