package com.github.raininforest.gerberpcb.view

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showMsg(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}