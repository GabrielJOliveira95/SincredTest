package com.example.sincredtest.ui.viewholder

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.example.sincredtest.customview.CardEventView
import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.response.EventsResponseItem

class SincredEventsViewHolder(private val view: CardEventView) : RecyclerView.ViewHolder(view) {

    init {
        view.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun bind(eventResponseModel: EventsResponseItem, onItemClicked: (item: EventsResponseItem) -> Unit) =
        view.bindView(eventResponseModel, onItemClicked)
}