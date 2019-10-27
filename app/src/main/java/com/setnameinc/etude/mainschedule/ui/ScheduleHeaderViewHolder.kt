package com.setnameinc.etude.mainschedule.ui

import android.view.View
import kotlinx.android.synthetic.main.item_schedule_main_header.view.*

class ScheduleHeaderViewHolder(
    val view: View
) : ScheduleViewHolder(view = view) {
    override fun bind(comp: ScheduleItem) {
        val item = comp as ScheduleItem.ScheduleHeaderItem
        view.startTimeTextView.text = item.startTime
        view.endTimeTextView.text = item.endTime
        view.sizeTextView.text = "${item.size}"
    }
}