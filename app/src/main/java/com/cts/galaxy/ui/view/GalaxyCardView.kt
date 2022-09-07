package com.cts.galaxy.ui.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.databinding.ViewGalaxySingleItemBinding
import com.cts.galaxy.utils.DeviceUtil
import com.cts.galaxy.utils.Recyclable
import com.cts.galaxy.utils.getFormattedDate
import kotlin.math.roundToInt

class GalaxyCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr), Recyclable<Items> {

    private var onClickListener: (Items) -> Unit = {}

    private val binding: ViewGalaxySingleItemBinding =
        ViewGalaxySingleItemBinding.inflate((context as Activity).layoutInflater, this, true)

    override fun recycle(data: Items) {
        binding.apply {
            root.setOnClickListener {
                onClickListener.invoke(data)
            }
            val singleItem = if (data.data.isNotEmpty()) data.data[0] else null
            val itemUrl = if (data.links.isNotEmpty()) data.links[0].href else ""
            singleItem?.let {
                itemTitle.text = it.title
                itemCenterView.text = it.center
                itemDateView.text = getFormattedDate(it.dateCreated)

                val deviceWidth = DeviceUtil.getScreenWidth(root.context as Activity)
                thumbItemView.layoutParams.apply {
                    height = deviceWidth.div(1.5f).roundToInt()
                }
                Glide.with(root).load(itemUrl)
                    .into(thumbItemView)
            }
        }
    }

    fun setGalaxyItemClickListener(onClickListener: (Items) -> Unit) {
        this.onClickListener = onClickListener
    }

}