package com.setnameinc.etude.mainschdule.ui

import android.view.View
import kotlinx.android.synthetic.main.item_schedule_main_subject.view.*

class ScheduleSubjectViewHolder(
    val view: View
) : ScheduleViewHolder(view = view) {
    override fun bind(item: ScheduleItem) {
        val subject = item as ScheduleItem.ScheduleSubjectItem
        if (subject.size == 0) {
            view.sizeTextView.visibility = View.GONE
        }
    }
}