package com.setnameinc.etude.mainschedule.ui

import android.view.View
import kotlinx.android.synthetic.main.item_schedule_main_business.view.*

class ScheduleBusinessViewHolder(
    val view:View
) : ScheduleViewHolder(view) {
    override fun bind(item: ScheduleItem) {
        val com = item as ScheduleItem.ScheduleBusinessItem
        view.nameTextView.text = item.description
    }
}