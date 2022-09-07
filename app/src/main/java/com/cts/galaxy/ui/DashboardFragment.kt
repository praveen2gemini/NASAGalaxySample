package com.cts.galaxy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cts.galaxy.R
import com.cts.galaxy.api.ApiHelper
import com.cts.galaxy.api.ApiService
import com.cts.galaxy.api.Status
import com.cts.galaxy.api.models.Items
import com.cts.galaxy.databinding.FragmentDashboardBinding
import com.cts.galaxy.ui.DetailsFragment.Companion.KEY_SELECTED_ITEM
import com.cts.galaxy.ui.adapter.GalaxyAdapter
import com.cts.galaxy.ui.viewmodels.GalaxyCollectionViewModel
import com.cts.galaxy.ui.viewmodels.ViewModelFactory
import com.cts.galaxy.utils.gone
import com.cts.galaxy.utils.show

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashboardFragment : Fragment(), GalaxyAdapter.GalaxyItemSelectListener {

    private var _binding: FragmentDashboardBinding? = null

    private val galaxyCollectionViewModel by viewModels<GalaxyCollectionViewModel> {
        ViewModelFactory(ApiHelper(ApiService.getInstance()))
    }

    private val binding get() = _binding!!

    private val galaxyAdapter by lazy { GalaxyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerArticleView.apply {
            layoutManager = LinearLayoutManager(context)
            galaxyAdapter.setGalaxyItemSelectListener(this@DashboardFragment)
            adapter = galaxyAdapter
        }
        galaxyCollectionViewModel.currentState.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideProgress()
                        resource.data?.collection?.items?.let { items ->
                            updateItemsOnUI(items)
                        } ?: showError()
                        Log.d(TAG, "SUCCESS ${resource.data}")
                    }
                    Status.ERROR -> {
                        showError(R.string.generic_error_message)
                        Log.e(TAG, "ERROR")
                    }
                    Status.LOADING -> {
                        showProgress()
                        Log.i(TAG, "LOADING")
                    }
                }
            }
        }
        galaxyCollectionViewModel.fetchGalaxyCollectionInformation() // First time call with empty, It can be disabled if don't want to trigger on first time
    }

    private fun showError(resID: Int = R.string.empty_status_text) {
        binding.apply {
            progressContainer.root.gone()
            recyclerArticleView.gone()
            emptyStatus.apply {
                text = getString(resID)
                show()
            }
        }
    }

    private fun showProgress() {
        binding.apply {
            progressContainer.root.show()
            recyclerArticleView.gone()
            emptyStatus.gone()
        }
    }

    private fun hideProgress() {
        binding.apply {
            progressContainer.root.gone()
            recyclerArticleView.show()
            emptyStatus.gone()
        }
    }

    private fun updateItemsOnUI(data: List<Items>) {
        galaxyAdapter.load(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = DashboardFragment::class.java.simpleName
    }

    override fun onGalaxyItemSelected(selectedItem: Items) {
        findNavController().navigate(
            R.id.startDetailsFragment,
            bundleOf(KEY_SELECTED_ITEM to selectedItem)
        )
    }
}