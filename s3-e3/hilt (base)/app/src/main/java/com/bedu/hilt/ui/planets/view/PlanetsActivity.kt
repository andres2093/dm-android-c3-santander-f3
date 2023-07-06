package com.bedu.hilt.ui.planets.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bedu.hilt.data.model.People
import com.bedu.hilt.databinding.ActivityRecyclerBinding
import com.bedu.hilt.ui.planets.adapter.PlanetsAdapter
import com.bedu.hilt.utils.Status
import com.bedu.hilt.ui.planets.viewmodel.PlanetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    private val planetsViewModel : PlanetsViewModel by viewModels()
    private lateinit var adapter: PlanetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlanetsAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            planetsViewModel.onRefresh()
        }
    }

    private fun setupObserver() {
        planetsViewModel.planets.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = false
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { planets -> renderList(planets) }
                    binding.recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(planets: List<People>) {
        adapter.addData(planets)
        adapter.notifyDataSetChanged()
    }
}
