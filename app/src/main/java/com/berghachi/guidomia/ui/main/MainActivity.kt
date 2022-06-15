package com.berghachi.guidomia.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berghachi.guidomia.R
import com.berghachi.guidomia.databinding.ActivityMainBinding
import com.berghachi.guidomia.domain.model.CarItem
import com.berghachi.guidomia.ui.main.adapter.CarItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var carAdapter: CarItemAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.mState.collectLatest {
                setupRecyclerView(it)
                setupMakeSpinner()
                setupModelSpinner()
            }
        }
    }

    private fun setupMakeSpinner() {
        viewModel.getMakeList(getString(R.string.any_make)).let {
            val makeAdapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, it)
            makeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.include.spMake.adapter = makeAdapter
            binding.include.spMake.onItemSelectedListener = this
        }

    }

    private fun setupModelSpinner() {
        viewModel.getModelList(getString(R.string.any_model)).let {
            val modelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.include.spModel.adapter = modelAdapter
            binding.include.spModel.onItemSelectedListener = this
        }

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
            binding.include.spModel.selectedItemPosition,
            binding.include.spMake.selectedItemPosition
        ).let {
            setupRecyclerView(it)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}