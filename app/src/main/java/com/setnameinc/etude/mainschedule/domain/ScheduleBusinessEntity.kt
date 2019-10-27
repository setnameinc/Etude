package com.setnameinc.etude.mainschedule.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class ScheduleBusinessEntity(
    var subjectGroupId: Int = 0,
    var description: String? = null
) : RealmModel