package com.cts.galaxy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.databinding.FragmentDetailsBinding
import com.cts.galaxy.utils.loadCenter
import com.cts.galaxy.utils.loadFormattedDate

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private val selectedItem: Items? by lazy {
        arguments?.getParcelable(KEY_SELECTED_ITEM)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            val itemUrl =
                if (selectedItem?.links?.isNotEmpty() == true) selectedItem?.links?.get(0)?.href else ""
            Glide.with(root).load(itemUrl).into(expandedHeaderImageView)
            val singleItem =
                if (selectedItem?.data?.isNotEmpty() == true) selectedItem?.data?.get(0) else null
            singleItem?.let {
                itemTitleView.text = it.title
                itemCenterView.loadCenter(it.center)
                itemDateCreationView.loadFormattedDate(it.dateCreated)
                itemDescriptionView.text = it.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_SELECTED_ITEM = "SELECTED_ITEM"
    }
}
