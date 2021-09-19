package com.github.raininforest.gerberpcb.ui.layers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.ui.showMsg

/**
 * Created by Sergey Velesko on 18.07.2021
 */
class LayersFragment : Fragment() {

    companion object {
        private const val REQUEST_OPEN_FILE: Int = 1
    }

    private val listAdapter = GerberListAdapter()
    private val viewModel: LayersViewModel by viewModels()
    private var _binding: LayersFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayersFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(
                Intent.createChooser(intent, "messageTitle"),
                REQUEST_OPEN_FILE
            )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //TODO
        if (requestCode == REQUEST_OPEN_FILE) {
            val uri: Uri = data?.data ?: Uri.EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}