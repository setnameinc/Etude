package com.setnameinc.etude.mainschedule.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class ScheduleSubjectEntity(
    var subjectGroupId:Int = 0,
    var groupId: Int = 0,
    var name: String? = null,
    var classroom: String? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var priority : Int = 0
) : RealmModel