package com.berghachi.guidomia.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.domain.model.Cars
import com.berghachi.guidomia.domain.usecases.GetCarsCountFromDbUseCase
import com.berghachi.guidomia.domain.usecases.GetCarsFromDbUseCase
import com.berghachi.guidomia.domain.usecases.InsertCarsToDbUseCase
import com.berghachi.guidomia.utils.JsonReader
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getCarsFromDbUseCase: GetCarsFromDbUseCase,
    private val insertCarsToDbUseCase: InsertCarsToDbUseCase,
    private val getCarsCountFromDbUseCase: GetCarsCountFromDbUseCase
) : ViewModel() {

    private val state = MutableStateFlow<List<CarItem>>(arrayListOf())
    val mState: StateFlow<List<CarItem>> get() = state
    private var models: List<String> = arrayListOf()
    private var makes: List<String> = arrayListOf()

    init {
        checkIfWeShouldStoreJsonToDB()
        getCarsFromDb()
    }

    private fun checkIfWeShouldStoreJsonToDB() {
        viewModelScope.launch {
            if (getCarsCountFromDbUseCase.invoke() == 0) {
                insertCars(context = context)
            }
        }
    }

    fun getMakeList(hint: String): List<String> {
        makes = arrayListOf(hint).also {
            state.value.map { it.make }.let { it1 -> it.addAll(it1) }
        }
        return makes
    }

    fun getModelList(hint: String): List<String> {
        models = arrayListOf(hint).also {
            state.value.map { it.model }.let { it1 -> it.addAll(it1) }
        }
        return models
    }

    private fun insertCars(context: Context) {
        val cars = JsonReader.readFile(context, "car_list.json", Cars::class)
        viewModelScope.launch {
            cars?.toList()?.let { insertCarsToDbUseCase.invoke(it) }
        }
    }

    private fun getCarsFromDb() {
        viewModelScope.launch {
            getCarsFromDbUseCase.invoke().collectLatest {
                state.emit(it)
            }
        }
    }

    fun filter(selectedMakeItemPosition: Int, selectedModelItemPosition: Int): List<CarItem> {
        val cars = state.value
        return when {
            selectedMakeItemPosition == 0 && selectedModelItemPosition == 0 -> cars
            selectedMakeItemPosition == 0 && selectedModelItemPosition != 0 -> cars.filter { it.model.lowercase() == models[selectedModelItemPosition].lowercase() }
            selectedMakeItemPosition != 0 && selectedModelItemPosition == 0 -> cars.filter { it.make.lowercase() == makes[selectedMakeItemPosition].lowercase() }
            else -> cars.filter { it.make.lowercase() == makes[selectedMakeItemPosition].lowercase() && it.model.lowercase() == models[selectedModelItemPosition].lowercase() }
        }
    }
}