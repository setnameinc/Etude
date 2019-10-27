package com.setnameinc.etude.mainschedule.ui

import android.view.View
import kotlinx.android.synthetic.main.item_schedule_main_header.view.*
//import kotlinx.android.synthetic.main.item_schedule_main_subject.view.*
import kotlinx.android.synthetic.main.item_schedule_main_header.view.sizeTextView

class ScheduleHeaderViewHolder(
    val view: View
) : ScheduleViewHolder(view = view) {
    override fun bind(item: ScheduleItem) {
        val localeItem = item as ScheduleItem.ScheduleHeaderItem
//        if (localeItem.listOfBaseness.isEmpty()){
//            view.sizeTextView.visibility = View.INVISIBLE
//        }
        view.startTimeTextView.text=localeItem.startTime
        view.endTimeTextView.text=localeItem.endTime
        view.sizeTextView.text= localeItem.size.toString()

    }
}