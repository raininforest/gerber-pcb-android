package com.github.raininforest.gerberpcb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raininforest.gerberpcb.viewmodel.LayersViewModel
import com.github.raininforest.gerberpcb.R

class LayersFragment : Fragment() {

    companion object {
        fun newInstance() = LayersFragment()
    }

    private lateinit var viewModel: LayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LayersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}