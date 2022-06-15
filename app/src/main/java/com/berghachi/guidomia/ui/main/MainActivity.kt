package com.berghachi.guidomia.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.guidomia.R
import com.berghachi.guidomia.databinding.ActivityScrollingBinding
import com.berghachi.guidomia.domain.model.Cars
import com.berghachi.guidomia.ui.main.adapter.CarItemAdapter
import com.berghachi.guidomia.utils.JsonReader


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    lateinit var carAdapter: CarItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        carAdapter = CarItemAdapter ()

        val cars = JsonReader.readFile(this, "car_list.json", Cars::class)
        binding.include.recyclerCars.apply {
            adapter = carAdapter
            addItemDecoration(
                getDivider()
            )
        }
        carAdapter.submitList(cars)
    }

    private fun getDivider(): DividerItemDecoration {
        val decoration =
            object : DividerItemDecoration(this, VERTICAL) {

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val position = parent.getChildAdapterPosition(view)
                    // hide the divider for the last child
                    if (position == state.itemCount - 1) {
                        outRect.setEmpty()
                    } else {
                        super.getItemOffsets(outRect, view, parent, state)
                    }
                }
            }
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { decoration.setDrawable(it) }
        return decoration
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}