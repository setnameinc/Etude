package com.setnameinc.etude.mainschdule.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.setnameinc.etude.R

class ScheduleAdapter
    : ListAdapter<ScheduleItem, ScheduleViewHolder>(
    DateAdapterDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleViewHolder = when (viewType) {
        ScheduleTypes.mainHeaderType ->
            ScheduleHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_header, parent, false)
            )
        ScheduleTypes.subjectType ->
            ScheduleSubjectViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_subject, parent, false)
            )
        ScheduleTypes.businessType ->
            ScheduleBusinessViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_business, parent, false)
            )
        else ->
            ScheduleBottomViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_bottom, parent, false)
            )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

}

class DateAdapterDiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {

    override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

}