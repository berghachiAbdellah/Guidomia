package com.berghachi.guidomia.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val consList: List<String>,
    val customerPrice: Double,
    val make: String,
    val marketPrice: Double,
    val model: String,
    val prosList: List<String>,
    val rating: Int
)