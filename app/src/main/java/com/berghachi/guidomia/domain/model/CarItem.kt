package com.berghachi.guidomia.domain.model

import com.berghachi.guidomia.data.local.model.CarEntity


data class CarItem(
    val consList: List<String>,
    val customerPrice: Long,
    val make: String,
    val marketPrice: Long,
    val model: String,
    val prosList: List<String>,
    val rating: Int
)

fun List<CarItem>.toCarEntities() = this.map {
    CarEntity(
        0,
        it.consList,
        it.customerPrice,
        it.make,
        it.marketPrice,
        it.model,
        it.prosList,
        it.rating
    )
}