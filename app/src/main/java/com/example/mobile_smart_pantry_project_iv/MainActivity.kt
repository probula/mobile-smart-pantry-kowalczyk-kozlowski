package com.example.mobile_smart_pantry_project_iv

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_smart_pantry_project_iv.adapters.PantryAdapter
import com.example.mobile_smart_pantry_project_iv.databinding.ActivityMainBinding
import com.example.mobile_smart_pantry_project_iv.models.Product
import com.example.mobile_smart_pantry_project_iv.utils.JsonParser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PantryAdapter
    private var allProducts: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        loadInventory()
        setupControls()
    }

    private fun setupRecyclerView() {
        adapter = PantryAdapter(emptyList())
        binding.rvInventory.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun loadInventory() {
        allProducts = JsonParser.loadProductsFromRaw(this, R.raw.inventory)
        adapter.updateData(allProducts)
    }
    private fun setupControls() {
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            val filtered = allProducts.filter { it.name.contains(query, ignoreCase = true) }
            adapter.updateData(filtered)
        }

        binding.btnFood.setOnClickListener {
            val filtered = allProducts.filter { it.category.contains("Food", ignoreCase = true) }
            adapter.updateData(filtered)
        }

        binding.btnAll.setOnClickListener {
            adapter.updateData(allProducts)
        }
    }
}
