package com.example.sincredtest.ui.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sincredtest.R
import com.example.sincredtest.constants.CONSTANTS.EVENT_DETAIL_BUNDLE
import com.example.sincredtest.data.response.EventsResponseItem

class SincredEventDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = arguments?.getSerializable(EVENT_DETAIL_BUNDLE) as EventsResponseItem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sincred_event_detail, container, false)
    }

}