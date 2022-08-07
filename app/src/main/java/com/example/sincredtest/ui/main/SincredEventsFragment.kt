package com.example.sincredtest.ui.main

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
import com.example.sincredtest.ui.BaseFragment
import com.example.sincredtest.ui.adapter.SincredEventsAdapter
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class SincredEvents : BaseFragment() {
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

    override fun handleObservers() {
        viewModel.sealedClassResponse.observe(viewLifecycleOwner) {
            when (it) {
                is SincredSealedClassResponse.OnSuccess -> {
                    showContent()
                    bindEventsList(it)
                }
                is SincredSealedClassResponse.OnFailure -> {
                    showContent()
                    initDialog(it.exception) { viewModel.loadEvents() }
                    binding.progressAction.visibility = View.GONE
                }
                is SincredSealedClassResponse.NoInternet -> {
                    showContent()
                    initDialog(getString(R.string.no_internet)) {  viewModel.loadEvents() }
                }
                else -> Unit
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

    private fun showContent() {
        binding.rvEvents.visibility = View.VISIBLE
        binding.progressAction.visibility = View.GONE
    }
}