package com.example.sincredtest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.sincredtest.R
import com.example.sincredtest.data.response.EventsResponseItem
import com.example.sincredtest.databinding.CardEventsItemBinding
import com.squareup.picasso.Picasso

class CardEventView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var linkButtonText: String? = null
    private val binding = CardEventsItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    fun bindView(
        eventResponseModel: EventsResponseItem,
        onItemClicked: (item: EventsResponseItem) -> Unit
    ) {
        binding.tvTitle.text = eventResponseModel.title
        binding.tvDecription.text = eventResponseModel.description
        bindImage(eventResponseModel.image)
        setOnItemClickListener(onItemClicked, eventResponseModel)
        bindExpandableIcon()
    }

    private fun setOnItemClickListener(
        onItemClicked: (item: EventsResponseItem) -> Unit,
        eventResponseModel: EventsResponseItem
    ) {
        binding.ivEventLogo.setOnClickListener {
            onItemClicked.invoke(eventResponseModel)
        }
    }

    private fun bindImage(image: String) {
        val url = image.replace("http", "https")
        val fixedUrl = url.replace("httpss", "https")

        Picasso.get().load(fixedUrl).error(R.drawable.ic_baseline_camera_alt_24)
            .into(binding.ivEventLogo)
    }

    private fun bindExpandableIcon() {
        binding.tvLink.setOnClickListener {
            if (binding.container.isVisible) {
                binding.container.visibility = GONE
                binding.tvLink.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            } else {
                binding.container.visibility = VISIBLE
                binding.tvLink.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }
        }
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardEventView)
            val buttonLinkResId =
                attributes.getResourceId(R.styleable.CardEventView_link_button_title, 0)

            if (buttonLinkResId != 0) {
                linkButtonText = context.getString(buttonLinkResId)
            }
            attributes.recycle()
        }
    }
}