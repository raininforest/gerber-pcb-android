package com.github.raininforest.gerberpcb.ui.screens.messagedialog

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.databinding.ErrorDialogBinding

class ErrorDialog(@LayoutRes val layoutId: Int) : DialogFragment(layoutId) {

    private val binding by viewBinding(ErrorDialogBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainErrorTV.text = arguments?.getString(DIALOG_MSG) ?: "Unknown error"
        binding.errorDetailsTV.text = arguments?.getString(DIALOG_ERROR_DETAILS) ?: ""
    }

    companion object {
        private const val DIALOG_MSG = "DIALOG_MSG"
        private const val DIALOG_ERROR_DETAILS = "DIALOG_ERROR_DETAILS"

        fun createDialog(msg: String, details: String): DialogFragment {
            val bundle = bundleOf(
                DIALOG_MSG to msg,
                DIALOG_ERROR_DETAILS to details
            )
            return ErrorDialog(R.layout.error_dialog).apply {
                arguments = bundle
            }
        }
    }
}
