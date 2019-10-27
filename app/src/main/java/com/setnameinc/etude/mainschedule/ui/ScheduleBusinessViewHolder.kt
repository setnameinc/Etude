package com.setnameinc.etude.mainschedule.ui

import android.view.View

class ScheduleBusinessViewHolder(
    val view:View
) : ScheduleViewHolder(view) {
    override fun bind(item: ScheduleItem) {
        //must
        val localeItem = item as ScheduleItem.ScheduleBusinessItem
//        if (localeItem.listOfBaseness.isEmpty()){
//            view.sizeTextView.visibility = View.INVISIBLE
//        }

    }
}