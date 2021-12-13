package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.app.SharedPrefManager
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.ui.screens.messagedialog.showErrorDialog
import com.github.raininforest.logger.Logger
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [Fragment] for screen with list of gerbers
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersFragment : Fragment(R.layout.layers_fragment) {

    private val binding by viewBinding(LayersFragmentBinding::bind)
    private val listAdapter = GerberListAdapter { id, isChecked ->
        layersViewModel.gerberVisibilityChanged(id, isChecked)
    }
    private val layersViewModel: LayersViewModel by viewModel()
    private val sharedPrefManager: SharedPrefManager by inject()

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
        val wasRun = sharedPrefManager.getRunFirst()
        if (!wasRun) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.shake)
            binding.fab.startAnimation(anim)
            sharedPrefManager.setRunFirst(true)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" }
            openFileResult.launch(intent)
        }
    }

    private fun initRecycler() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerView.addItemDecoration(SpaceDecorator(resources.getDimensionPixelSize(R.dimen.list_spacing)))
            recyclerView.adapter = listAdapter
        }
        initSwipe()
    }

    private fun initSwipe() {
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                layersViewModel.gerberRemoved(listAdapter.getGerberItemId(position))
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
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
                listAdapter.submitList(screenState.gerberList)
            }
            is LayersScreenState.Error -> {
                showErrorDialog(
                    message = screenState.error,
                    errorDetails = screenState.errorDetails,
                    childFragmentManager
                )
            }
        }
}