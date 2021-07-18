package com.github.raininforest.gerberpcb.view.graphics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.viewmodel.graphics.GraphicsViewModel

class GraphicsFragment : Fragment() {

    companion object {
        fun newInstance() = GraphicsFragment()
    }

    private lateinit var viewModel: GraphicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.graphics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraphicsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}