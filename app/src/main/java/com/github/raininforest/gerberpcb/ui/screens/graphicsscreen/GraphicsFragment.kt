package com.github.raininforest.gerberpcb.ui.screens.graphicsscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [Fragment] for screen with 2D image
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GraphicsFragment : Fragment(R.layout.graphics_fragment) {

    private val binding by viewBinding(LayersFragmentBinding::bind)
    private val graphicsViewModel: GraphicsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}