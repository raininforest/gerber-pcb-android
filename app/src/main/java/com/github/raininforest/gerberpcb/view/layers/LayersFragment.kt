package com.github.raininforest.gerberpcb.view.layers

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.viewmodel.layers.LayersViewModel
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import com.github.raininforest.gerberpcb.view.showMsg
import com.github.raininforest.gerberpcb.viewmodel.layers.LayersScreenState

class LayersFragment : Fragment() {

    companion object {
        fun newInstance() = LayersFragment()
        private const val REQUEST_OPEN_FILE: Int = 1
    }

    private val listAdapter = GerberListAdapter()
    private lateinit var viewModel: LayersViewModel
    private var _binding: LayersFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initRecycler()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LayersViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getList()
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
            LayersScreenState.Loading -> {
                //TODO показать диалог загрузки
            }
            is LayersScreenState.Success -> {
                listAdapter.setList(screenState.gerberList)
            }
            is LayersScreenState.Error -> {
                showMsg("Error!")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //TODO
        if (requestCode == REQUEST_OPEN_FILE) {
            val uri: Uri = data?.data ?: Uri.EMPTY
            Toast.makeText(context, uri.toString(), Toast.LENGTH_LONG).show()
        }
    }

}