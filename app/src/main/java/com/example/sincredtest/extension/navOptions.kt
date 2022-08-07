package com.example.sincredtest.extension

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.sincredtest.R

private val navOptions =
    NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setPopExitAnim(R.anim.slide_out_left)
        .build()

fun NavController.slideWithEffect(navigateToId: Int, bundle: Bundle?) {
    this.navigate(navigateToId, bundle, navOptions)
}