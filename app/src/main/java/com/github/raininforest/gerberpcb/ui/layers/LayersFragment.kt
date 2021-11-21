package com.github.raininforest.gerberpcb.ui.layers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.ui.layers.message.showMessageDialog
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
                val fileName = DocumentFile.fromSingleUri(requireContext(), uri)?.name ?: "<empty>"
                Logger.d("File opened: $fileName")
                layersViewModel.gerberAdded(uri, fileName)
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
        layersViewModel.init()
        layersViewModel.data.observe(viewLifecycleOwner) { screenState -> renderData(screenState) }
        layersViewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
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
                showMessageDialog(message = getString(R.string.error_gerber), childFragmentManager)
            }
        }
}