package com.setnameinc.etude.scheduleadd.ui

import androidx.lifecycle.ViewModel
import com.setnameinc.etude.mainschedule.domain.ScheduleBusinessEntity
import com.setnameinc.etude.mainschedule.domain.ScheduleRepository
import com.setnameinc.etude.mainschedule.ui.ScheduleItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class ScheduleAddFragmentViewModel(
    val scheduleRepository: ScheduleRepository
) : ViewModel() {


    override fun onCleared() {
        super.onCleared()

    }
}

data class AddFragentSaveType(
    val day: Int,
    val week: Int,
    val name: String
)