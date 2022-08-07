package com.example.sincredtest.ui.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sincredtest.R
import com.example.sincredtest.constants.CONSTANTS.EVENT_DETAIL_BUNDLE
import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponseItem
import com.example.sincredtest.databinding.FragmentSincredEventDetailBinding
import com.example.sincredtest.ui.main.SincredViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class SincredEventDetailFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleCheckIn()
    }

    private fun handleCheckIn() {
        binding.btnDoCheckIn.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        when {
            binding.etName.text.isNullOrBlank() && binding.etEmail.text.isNullOrBlank() -> {
                binding.tiEmail.isErrorEnabled = true
                binding.tiEmail.error = "Digite todos os campos"
                binding.tiName.isErrorEnabled = true
                binding.tiName.error = "Digite todos os campos"
            }
            binding.etName.text.isNullOrBlank() -> {
                binding.tiName.isErrorEnabled = true
                binding.tiName.error = "Digite todos os campos"
                binding.tiEmail.isErrorEnabled = false
            }
            binding.etEmail.text.isNullOrBlank() -> {
                binding.tiEmail.isErrorEnabled = true
                binding.tiEmail.error = "Digite todos os campos"
                binding.tiName.isErrorEnabled = false
            }
            else -> {
                binding.tiName.isErrorEnabled = false
                binding.tiEmail.isErrorEnabled = false
                val checkInRequest = SincredCheckInRequest(
                    email = binding.etEmail.text.toString(),
                    eventId = eventModel.id,
                    name = binding.etName.text.toString()
                )
                viewModel.checkIn(checkInRequest)
            }
        }
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