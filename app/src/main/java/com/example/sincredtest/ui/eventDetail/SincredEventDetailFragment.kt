package com.example.sincredtest.ui.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sincredtest.R
import com.example.sincredtest.constants.CONSTANTS.EVENT_DETAIL_BUNDLE
import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponseItem
import com.example.sincredtest.databinding.FragmentSincredEventDetailBinding
import com.example.sincredtest.ui.BaseFragment
import com.example.sincredtest.ui.main.SincredViewModel
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class SincredEventDetailFragment : BaseFragment() {

    private val viewModel: SincredViewModel by viewModel()

    private lateinit var binding: FragmentSincredEventDetailBinding
    private lateinit var eventModel: EventsResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventModel = arguments?.getSerializable(EVENT_DETAIL_BUNDLE) as EventsResponseItem
        binding = FragmentSincredEventDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView()
        bindImage()
        return binding.root
    }

    override fun handleObservers() {
        viewModel.sealedClassResponse.observe(viewLifecycleOwner){
            when(it) {
                is SincredSealedClassResponse.CheckSuccessIn -> {
                    findNavController().popBackStack()
                }
                is SincredSealedClassResponse.OnFailure -> {
                    initDialog(it.exception) { handleRequest() }
                }
                is SincredSealedClassResponse.NoInternet -> {
                    initDialog(getString(R.string.no_internet)) { handleRequest() }
                }
                else -> Unit
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleCheckIn()
    }

    private fun handleCheckIn() {
        binding.btnDoCheckIn.setOnClickListener {
            if(validateFields()) handleCheckIn()
        }
    }

    private fun validateFields(): Boolean {
        when {
            binding.etName.text.isNullOrBlank() && binding.etEmail.text.isNullOrBlank() -> {
                binding.tiEmail.isErrorEnabled = true
                binding.tiEmail.error = getString(R.string.empty_field)
                binding.tiName.isErrorEnabled = true
                binding.tiName.error = getString(R.string.empty_field)
                return false
            }
            binding.etName.text.isNullOrBlank() -> {
                binding.tiName.isErrorEnabled = true
                binding.tiName.error = getString(R.string.empty_field)
                binding.tiEmail.isErrorEnabled = false
                return false
            }
            binding.etEmail.text.isNullOrBlank() -> {
                binding.tiEmail.isErrorEnabled = true
                binding.tiEmail.error = getString(R.string.empty_field)
                binding.tiName.isErrorEnabled = false
                return false
            }
            else -> {
                binding.tiName.isErrorEnabled = false
                binding.tiEmail.isErrorEnabled = false
                handleRequest()
                return true
            }
        }
    }

    private fun handleRequest(){
        val checkInRequest = SincredCheckInRequest(
            email = binding.etEmail.text.toString(),
            eventId = eventModel.id,
            name = binding.etName.text.toString()
        )
        viewModel.checkIn(checkInRequest)
    }

    private fun bindView() {
        binding.tvEventDetailTitle.text = eventModel.title
    }

    private fun bindImage() {
        val url = eventModel.image.replace("http", "https")
        val fixedUrl = url.replace("httpss", "https")

        Picasso.get().load(fixedUrl).error(R.drawable.ic_baseline_camera_alt_24)
            .into(binding.ivEventDetail)
    }
}