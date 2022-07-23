package com.github.raininforest.gerberpcb.ui.screens.messagedialog

import androidx.fragment.app.FragmentManager

/**
 * Utils for notifications
 *
 * Created by Sergey Velesko on 18.07.2021
 */
fun showErrorDialog(message: String, errorDetails: String, childFragmentManager: FragmentManager) {
    ErrorDialog.createDialog(message, errorDetails).show(childFragmentManager, null)
}
