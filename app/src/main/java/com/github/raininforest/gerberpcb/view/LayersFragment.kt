package com.github.raininforest.gerberpcb.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.raininforest.gerberpcb.viewmodel.LayersViewModel
import com.github.raininforest.gerberpcb.databinding.LayersFragmentBinding

class LayersFragment : Fragment() {

    companion object {
        fun newInstance() = LayersFragment()
        private const val REQUEST_OPEN_FILE: Int = 1
    }

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
        binding.addButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(
                Intent.createChooser(intent, "messageTitle"),
                REQUEST_OPEN_FILE
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LayersViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_OPEN_FILE) {
            val uri: Uri = data?.data ?: Uri.EMPTY
            Toast.makeText(context, uri.toString(), Toast.LENGTH_LONG).show()
        }
    }

}