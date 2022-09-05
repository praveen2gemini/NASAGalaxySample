package com.cts.galaxy.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.databinding.ViewAcronymSingleItemBinding
import com.cts.galaxy.utils.DeviceUtil
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

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
                    itemTitle.text = it.title
                    itemCenterView.text = it.center
                    itemDateView.text = formattedDate(it.dateCreated)

                    val deviceWidth = DeviceUtil.getScreenWidth(root.context as Activity)
                    thumbItemView.layoutParams.apply {
                        height = deviceWidth.div(1.5f).roundToInt()
                    }
                    Glide.with(root).load(itemUrl)
                        .into(thumbItemView)
                }
            }
        }

        private fun formattedDate(strDate: String): String? {
//            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            return try {
                val date = dateFormat.parse(strDate)
                dateFormat.format(date)
            } catch (pe: ParseException) {
                pe.printStackTrace()
                "N/A"
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