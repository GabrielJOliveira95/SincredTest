package com.example.sincredtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sincredtest.R
import com.example.sincredtest.constants.CONSTANTS.EVENT_DETAIL_BUNDLE
import com.example.sincredtest.data.response.EventsResponseItem
import com.example.sincredtest.databinding.FragmentSincredEventsBinding
import com.example.sincredtest.extension.slideWithEffect
import com.example.sincredtest.ui.adapter.SincredEventsAdapter
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class SincredEvents : Fragment() {
    private val viewModel: SincredViewModel by viewModel()

    lateinit var binding: FragmentSincredEventsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSincredEventsBinding.inflate(layoutInflater)
        viewModel.loadEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        handleObservables()
    }

    private fun handleObservables() {
        viewModel.sealedClassResponse.observe(viewLifecycleOwner) {
            when (it) {
                is SincredSealedClassResponse.OnSuccess -> {
                    bindEventsList(it)
                }
                is SincredSealedClassResponse.OnFailure -> {

                }
            }
        }
    }

    private fun bindEventsList(sincredSealedClassResponse: SincredSealedClassResponse.OnSuccess) {
        binding.rvEvents.apply {
            val eventsAdapter = SincredEventsAdapter(sincredSealedClassResponse)
            {
                goToEventDetail(it)
            }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventsAdapter
        }
    }

    private fun goToEventDetail(eventsResponse: EventsResponseItem) {
        val bundle = bundleOf(EVENT_DETAIL_BUNDLE to eventsResponse)
        findNavController()
            .slideWithEffect(
                navigateToId = R.id.action_sincredEvents_to_sincredEventDetailFragment,
                bundle = bundle
            )
    }
}