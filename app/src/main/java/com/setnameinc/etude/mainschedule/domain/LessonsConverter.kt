package com.setnameinc.etude.mainschedule.domain

import com.setnameinc.etude.mainschedule.ui.ScheduleItem

object LessonsConverter {
    fun fromDatabase(
        subjectEntity: ScheduleSubjectEntity,
        scheduleBusinessEntityList : List<ScheduleBusinessEntity>
    ) : ScheduleItem.ScheduleSubjectItem {
        return ScheduleItem.ScheduleSubjectItem(
            subjectEntity.name,
            subjectEntity.classroom,
            subjectEntity.startTime,
            subjectEntity.endTime,
            scheduleBusinessEntityList.map {
                ScheduleItem.ScheduleBusinessItem(
                    it.description
                )
            }
        )
    }
}