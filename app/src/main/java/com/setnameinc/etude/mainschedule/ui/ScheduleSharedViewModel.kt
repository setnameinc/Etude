package com.setnameinc.etude.mainschedule.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.setnameinc.etude.mainschedule.domain.ScheduleBusinessEntity
import com.setnameinc.etude.mainschedule.domain.ScheduleEntity
import com.setnameinc.etude.mainschedule.domain.ScheduleRepository
import com.setnameinc.etude.mainschedule.domain.ScheduleSubjectEntity
import com.setnameinc.etude.utils.ListToArrayListConverter
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class ScheduleSharedViewModel(
    val scheduleRepository: ScheduleRepository
) : ViewModel() {

    val scheduleList = BehaviorSubject.create<ArrayList<ScheduleItem>>()

    init {
        /*saveDemo()*/
        /*refreshList()*/
        refreshLessonsList()
    }

    private fun saveDemo() {
        val groupId = 14
        val subjectGroupId = 19
        scheduleRepository.saveScheduleDay(
            arrayListOf(
                ScheduleEntity(
                    0,
                    0,
                    groupId
                ),
                ScheduleEntity(
                    1,
                    0,
                    groupId
                ),
                ScheduleEntity(
                    2,
                    0,
                    groupId
                )
            )
        ).subscribe(
            {
                Timber.i("complete saveScheduleDay")
            },
            {

            }
        )
        scheduleRepository.saveScheduleSubject(
            listOf(
                ScheduleSubjectEntity(
                    subjectGroupId,
                    groupId,
                    "Math",
                    "415",
                    "9:00",
                    "10:30",
                    0
                ),
                ScheduleSubjectEntity(
                    subjectGroupId + 1,
                    groupId,
                    "Russian",
                    "415",
                    "9:00",
                    "10:30",
                    1
                ),
                ScheduleSubjectEntity(
                    subjectGroupId + 2,
                    groupId,
                    "English",
                    "415",
                    "9:00",
                    "10:30",
                    2
                )
            )
        ).subscribe(
            {
                Timber.i("complete saveScheduleSubject")
            },
            {


            }
        )
        scheduleRepository.saveScheduleBusinessItem(
            listOf(
                ScheduleBusinessEntity(
                    subjectGroupId,
                    "take it"
                ),
                ScheduleBusinessEntity(
                    subjectGroupId,
                    "move it"
                ),
                ScheduleBusinessEntity(
                    subjectGroupId,
                    "did it"
                )
            )
        ).subscribe(
            {
                Timber.i("complete saveScheduleBusinessItem")
            },
            {


            }
        )
    }

    @SuppressLint("CheckResult")
    private fun refreshLessonsList() {
        scheduleRepository.getLessons(0, 0)
            .subscribe(
                { it ->
                    scheduleList.onNext(
                        ListToArrayListConverter.convertToArrayList(
                            it.map {
                                ScheduleItem.ScheduleSubjectItem(
                                    it.name,
                                    it.classroom,
                                    it.startTime,
                                    it.endTime,
                                    it.listOfBaseness
                                )
                            }
                        )
                    )
                },
                {
                    Timber.i(it)
                }
            )
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
                    listOf()
                ),
                ScheduleItem.ScheduleSubjectItem(
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