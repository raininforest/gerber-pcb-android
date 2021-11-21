package com.github.raininforest.gerberpcb.ui.screens.messagedialog

import androidx.fragment.app.FragmentManager

/**
 * Utils for notifications
 *
 * Created by Sergey Velesko on 18.07.2021
 */
fun showMessageDialog(message: String, childFragmentManager: FragmentManager) {
    MessageDialog.createDialog(message).show(childFragmentManager, null)
}