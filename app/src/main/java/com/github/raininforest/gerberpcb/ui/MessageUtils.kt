package com.github.raininforest.gerberpcb.ui

import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

/**
 * Utils for notifications
 *
 * Created by Sergey Velesko on 18.07.2021
 */
fun showSnack(rootView: CoordinatorLayout, msg: String) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show()
}