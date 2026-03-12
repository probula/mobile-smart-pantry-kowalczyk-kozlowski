package com.example.mobile_smart_pantry_project_iv.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_smart_pantry_project_iv.databinding.ItemProductBinding
import com.example.mobile_smart_pantry_project_iv.models.Product

class PantryAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<PantryAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvProductName.text = product.name
            tvProductCategory.text = product.category
            tvProductQuantity.text = product.quantity.toString()

            val imageResId = context.resources.getIdentifier(
                product.imageRef, "drawable", context.packageName
            )
            if (imageResId != 0) {
                ivProductIcon.setImageResource(imageResId)
            } else {
                ivProductIcon.setImageResource(android.R.drawable.ic_menu_report_image)
            }

            if (product.quantity < 5) {
                layoutContainer.setBackgroundColor(Color.parseColor("#FFCDD2"))
            } else {
                layoutContainer.setBackgroundColor(Color.WHITE)
            }

            btnAdd.setOnClickListener {
                product.quantity++
                notifyItemChanged(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
