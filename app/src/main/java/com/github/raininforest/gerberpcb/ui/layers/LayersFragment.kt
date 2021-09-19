package com.github.raininforest.gerberpcb.ui.layers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.ui.showMsg
import timber.log.Timber

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersFragment : Fragment(R.layout.layers_fragment) {

    private val binding by viewBinding(LayersFragmentBinding::bind)
    private val listAdapter = GerberListAdapter()
    private val viewModel: LayersViewModel by viewModels()

    private val openFileResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val uri: Uri = data?.data ?: Uri.EMPTY
            //TODO pass uri to ViewModel
            Timber.d("File opened: ${uri.path}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initRecycler()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.data().observe(
            viewLifecycleOwner,
            { screenState -> renderData(screenState) }
        )
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

    private fun renderData(screenState: LayersScreenState) {
        when (screenState) {
            is LayersScreenState.Loading -> {
                screenState.progress.observe(viewLifecycleOwner) { value ->
                    renderProgress(value)
                }
            }
            is LayersScreenState.Success -> {
                listAdapter.setList(screenState.gerberList)
            }
            is LayersScreenState.Error -> {
                showMsg(screenState.error)
            }
        }
    }

    private fun renderProgress(value: String) {
        //TODO
    }
}