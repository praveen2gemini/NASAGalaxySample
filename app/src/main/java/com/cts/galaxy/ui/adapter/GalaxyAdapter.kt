package com.cts.galaxy.ui.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.ui.view.GalaxyCardView

class GalaxyAdapter : RecyclerView.Adapter<GalaxyAdapter.GalaxyViewHolder>() {

    private val galaxyItems = ArrayList<Items>()

    private var itemSelectionListener: GalaxyItemSelectListener? = null

    fun load(items: List<Items>) {
        this.galaxyItems.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class GalaxyViewHolder(
        private val singleView: GalaxyCardView,
        private val itemSelectionListener: GalaxyItemSelectListener?
    ) :
        RecyclerView.ViewHolder(singleView) {

        fun bind(item: Items) {
            singleView.apply {
                setGalaxyItemClickListener {
                    itemSelectionListener?.onGalaxyItemSelected(item)
                }
                recycle(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalaxyViewHolder {
        // Create a new view, which defines the UI of the list item
        val singleView = GalaxyCardView(parent.context).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        }
        return GalaxyViewHolder(singleView, itemSelectionListener)
    }

    override fun onBindViewHolder(holder: GalaxyViewHolder, position: Int) {
        holder.bind(galaxyItems[position])
    }

    override fun getItemCount(): Int {
        return galaxyItems.size
    }

    fun setGalaxyItemSelectListener(itemSelectionListener: GalaxyItemSelectListener?) {
        this.itemSelectionListener = itemSelectionListener
    }

    interface GalaxyItemSelectListener {
        fun onGalaxyItemSelected(selectedItem: Items)
    }
}