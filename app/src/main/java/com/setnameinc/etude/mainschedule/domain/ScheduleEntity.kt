package com.setnameinc.etude.mainschedule.domain

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ScheduleEntity(
    @PrimaryKey
    var day : Int = 0,
    var groupId : Int = 0
) : RealmModel