package com.setnameinc.etude.mainschedule.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

open class ScheduleDayEntity(
    var day: Int = 0,
    var date: String? = null,
    var notificationBackgroundColor: String? = null,
    var countOfBanges: Int = 0
) : RealmModel