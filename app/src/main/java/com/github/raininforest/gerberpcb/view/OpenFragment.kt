package com.github.raininforest.gerberpcb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raininforest.gerberpcb.viewmodel.OpenViewModel
import com.github.raininforest.gerberpcb.R

class OpenFragment : Fragment() {

    companion object {
        fun newInstance() = OpenFragment()
    }

    private lateinit var viewModel: OpenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.open_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OpenViewModel::class.java)
        // TODO: Use the ViewModel
    }
}