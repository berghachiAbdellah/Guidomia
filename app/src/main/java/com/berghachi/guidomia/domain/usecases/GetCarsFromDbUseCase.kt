package com.berghachi.guidomia.domain.usecases

import com.berghachi.guidomia.data.local.model.toCarItems
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.domain.repositories.CarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetCarsFromDbUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend fun invoke(): Flow<List<CarItem>> {
        return carsRepository.getCars().map { it.toCarItems() }
    }
}
