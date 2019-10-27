package com.setnameinc.etude.mainschedule.ui

import android.view.View

class ScheduleBottomViewHolder(
    val view: View,
    val onClick : () -> Unit
) : ScheduleViewHolder(
    view
) {
    init {
        view.setOnClickListener {
            onClick()
        }
    }
    override fun bind(item: ScheduleItem) {
        //empty
    }
}