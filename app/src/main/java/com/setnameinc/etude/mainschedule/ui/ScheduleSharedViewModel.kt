package com.setnameinc.etude.mainschedule.ui

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject

class ScheduleSharedViewModel : ViewModel() {

    val scheduleList = BehaviorSubject.create<ArrayList<ScheduleItem>>()

    init {
        refreshList()
    }

    private fun refreshList(){
        scheduleList.onNext(
            arrayListOf(
                ScheduleItem.ScheduleHeaderItem(
                    1,
                    "",
                    ""
                ),
                ScheduleItem.ScheduleSubjectItem(
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf(
                        ScheduleItem.ScheduleBusinessItem(""),
                        ScheduleItem.ScheduleBusinessItem(""),
                        ScheduleItem.ScheduleBusinessItem(""),
                        ScheduleItem.ScheduleBusinessItem("")
                    )
                ),
                ScheduleItem.ScheduleSubjectItem(
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf()
                ),
                ScheduleItem.ScheduleSubjectItem(
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf()
                ),
                ScheduleItem.ScheduleAddItem()
            )
        )
    }

}