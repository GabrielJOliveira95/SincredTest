package com.example.sincredtest.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sincredtest.customview.CardEventView
import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.response.EventsResponseItem
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import com.example.sincredtest.ui.viewholder.SincredEventsViewHolder

class SincredEventsAdapter(
    private val sincredSealedClassResponse: SincredSealedClassResponse.OnSuccess,
    private val onItemClicked : (item: EventsResponseItem) -> Unit
) :
    RecyclerView.Adapter<SincredEventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SincredEventsViewHolder {
        return SincredEventsViewHolder(CardEventView(parent.context))
    }

    override fun onBindViewHolder(holder: SincredEventsViewHolder, position: Int) {
        holder.bind(
            sincredSealedClassResponse.response[position],
            onItemClicked
        )
    }

    override fun getItemCount(): Int = sincredSealedClassResponse.response.size
}