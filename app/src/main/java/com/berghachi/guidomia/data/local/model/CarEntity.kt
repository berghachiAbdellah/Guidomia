package com.berghachi.guidomia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berghachi.guidomia.domain.model.CarItem

@Entity(tableName = "Car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val consList: List<String>,
    val customerPrice: Long,
    val make: String,
    val marketPrice: Long,
    val model: String,
    val prosList: List<String>,
    val rating: Int
)

fun List<CarEntity>.toCarItems() = this.map {
    CarItem(
        it.consList,
        it.customerPrice,
        it.make,
        it.marketPrice,
        it.model,
        it.prosList,
        it.rating
    )
}