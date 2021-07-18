package com.github.raininforest.gerberpcb.view

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Sergey Velesko on 18.07.2021
 */
fun Fragment.showMsg(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}