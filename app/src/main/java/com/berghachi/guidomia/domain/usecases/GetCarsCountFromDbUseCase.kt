package com.berghachi.guidomia.domain.usecases

import com.berghachi.guidomia.domain.repositories.CarsRepository
import javax.inject.Inject


class GetCarsCountFromDbUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend fun invoke(): Int {
        return carsRepository.getCarCount()
    }
}
