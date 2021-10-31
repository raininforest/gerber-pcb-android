package com.github.raininforest.gerberpcb.ui.layers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.ui.showSnack
import com.github.raininforest.logger.Logger
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [Fragment] for screen with list of gerbers
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersFragment : Fragment(R.layout.layers_fragment) {

    private val binding by viewBinding(LayersFragmentBinding::bind)
    private val listAdapter = GerberListAdapter()
    private val layersViewModel: LayersViewModel by viewModel()

    private val openFileResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val uri: Uri = data?.data ?: Uri.EMPTY
                layersViewModel.gerberAdded(uri)
                Logger.d("File opened: ${uri.path}")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initRecycler()
        initViewModel()
    }

    private fun initButtons() {
        binding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" }
            openFileResult.launch(intent)
        }
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.addItemDecoration(SpaceDecorator(resources.getDimensionPixelSize(R.dimen.list_spacing)))
        binding.recyclerView.adapter = listAdapter
    }

    private fun initViewModel() {
        layersViewModel.data.observe(viewLifecycleOwner) { screenState -> renderData(screenState) }
        layersViewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
        layersViewModel.list()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingIndicator.isGone = !isLoading
    }

    private fun renderData(screenState: LayersScreenState) =
        when (screenState) {
            is LayersScreenState.Success -> {
                listAdapter.setList(screenState.gerberList)
            }
            is LayersScreenState.Error -> {
                showSnack(binding.root, screenState.error)
            }
        }
}