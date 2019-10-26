package com.setnameinc.etude.mainschdule.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ScheduleItem)
}