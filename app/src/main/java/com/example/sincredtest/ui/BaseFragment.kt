package com.example.sincredtest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.sincredtest.R

abstract class BaseFragment: Fragment() {

    abstract fun handleObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleObservers()
    }

    fun initDialog(msg: String, action: () -> Unit){
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog
            .setTitle(getString(R.string.ops))
            .setMessage(msg)
            .setPositiveButton(R.string.ok
            ) { _, _ -> action.invoke()}
            .setCancelable(false)
            .create()
            .show()
    }
}