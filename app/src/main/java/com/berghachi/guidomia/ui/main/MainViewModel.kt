package com.berghachi.guidomia.ui.main

import android.content.Context
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.domain.model.Cars
import com.berghachi.guidomia.utils.JsonReader

class MainViewModel {

    private var cars: Cars? = null
    private var models: List<String> = arrayListOf()
    private var makes: List<String> = arrayListOf()

    fun getMakeList(hint: String): List<String> {
         makes = arrayListOf(hint).also {
            cars?.map { it.make }?.let { it1 -> it.addAll(it1) }
        }
        return makes
    }

    fun getModelList(hint: String): List<String> {
         models = arrayListOf(hint).also {
            cars?.map { it.model }?.let { it1 -> it.addAll(it1) }
        }
        return models
    }

    fun getCars(context: Context): Cars? {
        cars = JsonReader.readFile(context, "car_list.json", Cars::class)
        return cars
    }

    fun filter(selectedMakeItemPosition: Int, selectedModelItemPosition: Int): List<CarItem>? {
        return when {
            selectedMakeItemPosition == 0 && selectedModelItemPosition == 0-> cars
            selectedMakeItemPosition == 0 && selectedModelItemPosition != 0 -> cars?.filter { it.model == models[selectedModelItemPosition] }
            selectedMakeItemPosition != 0 && selectedModelItemPosition == 0 -> cars?.filter { it.make == makes[selectedMakeItemPosition] }
            else->cars?.filter { it.make == models[selectedMakeItemPosition] && it.model == makes[selectedModelItemPosition] }
        }
    }
}