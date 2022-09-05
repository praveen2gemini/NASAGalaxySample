package com.cts.galaxy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cts.galaxy.R
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.databinding.ViewAcronymSingleItemBinding

class GalaxyAdapter : RecyclerView.Adapter<GalaxyAdapter.GalaxyViewHolder>() {

    private val galaxyItems = ArrayList<Items>()

    fun load(items: List<Items>) {
        this.galaxyItems.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class GalaxyViewHolder(private val binding: ViewAcronymSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.apply {
                val singleItem = if (item.data.isNotEmpty()) item.data[0] else null
                val itemUrl = if (item.links.isNotEmpty()) item.links[0].href else ""
                singleItem?.let {
                    itemAcronymName.text = it.title
                    itemAcronymFrequency.text =
                        root.resources.getString(R.string.text_label_frequency, it.nasaId)
                    itemAcronymSince.text = it.center.plus(itemUrl)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalaxyViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding =
            ViewAcronymSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalaxyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalaxyViewHolder, position: Int) {
        holder.bind(galaxyItems[position])
    }

    override fun getItemCount(): Int {
        return galaxyItems.size
    }
}