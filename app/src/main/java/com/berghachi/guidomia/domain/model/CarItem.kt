package com.berghachi.guidomia.domain.model

data class CarItem(
    val consList: List<String>,
    val customerPrice: Long,
    val make: String,
    val marketPrice: Long,
    val model: String,
    val prosList: List<String>,
    val rating: Int
)