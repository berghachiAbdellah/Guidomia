package com.berghachi.guidomia.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.berghachi.guidomia.R
import com.berghachi.guidomia.databinding.ActivityScrollingBinding
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.ui.main.adapter.CarItemAdapter

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val anyMake = "Any make"
    private val anyModel = "Any model"

    private lateinit var binding: ActivityScrollingBinding
    lateinit var carAdapter: CarItemAdapter
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cars = viewModel.getCars(this)
        setupRecyclerView(cars?.toList())
        setupMakeSpinner()
        setupModelSpinner()
    }

    private fun setupMakeSpinner() {
        viewModel.getMakeList(anyMake).let {
            val makeAdapter: ArrayAdapter<*> =
                ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, it)
            makeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.include.spMake.adapter = makeAdapter
        }
        binding.include.spMake.onItemSelectedListener = this
    }

    private fun setupModelSpinner() {
        viewModel.getModelList(anyModel).let {
            val modelAdapter: ArrayAdapter<*> =
                ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, it)
            modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.include.spModel.adapter = modelAdapter
        }
        binding.include.spModel.onItemSelectedListener = this
    }

    private fun setupRecyclerView(cars: List<CarItem>?) {
        carAdapter = CarItemAdapter(cars)
        binding.include.recyclerCars.apply {
            adapter = carAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.filter(
            binding.include.spModel.selectedItemPosition ,
            binding.include.spMake.selectedItemPosition
        )?.let {
            setupRecyclerView(it)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}