package com.github.raininforest.gerberpcb.ui.screens.messagedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.github.raininforest.gerberpcb.R

class MessageDialog(@LayoutRes val layoutId: Int) : DialogFragment(layoutId) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    companion object {
        private const val DIALOG_MSG = "DIALOG_MSG"

        fun createDialog(msg: String): DialogFragment {
            val bundle = bundleOf(DIALOG_MSG to msg)
            return MessageDialog(R.layout.message_dialog).apply {
                arguments = bundle
            }
        }
    }
}