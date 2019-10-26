package com.setnameinc.etude.mainschdule.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ScheduleAdapter : ListAdapter<ScheduleItem, ScheduleViewHolder<*>>(DateAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder<*>, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

}

class DateAdapterDiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {

    override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

}