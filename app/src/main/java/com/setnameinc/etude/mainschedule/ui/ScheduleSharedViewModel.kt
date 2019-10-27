package com.setnameinc.etude.mainschedule.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.setnameinc.etude.mainschedule.domain.*
import com.setnameinc.etude.scheduleadd.ui.SaveItemsdfasd
import com.setnameinc.etude.utils.ListToArrayListConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class ScheduleSharedViewModel(
    val scheduleRepository: ScheduleRepository
) : ViewModel() {

    val scheduleList = BehaviorSubject.create<ArrayList<ScheduleItem>>()
    val forSaveInRealmItem = BehaviorSubject.create<SaveItemsdfasd>()
    val pickedDate = BehaviorSubject.create<Int>()

    init {
        /*saveDemo()*/
        subscrubeToDayToWeek()
        refreshLessonsList()
        refreshIt()
    }

    @SuppressLint("CheckResult")
    private fun refreshIt() {
        forSaveInRealmItem
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.i("invoked it")
                    scheduleRepository.saveScheduleBusinessItem(
                        listOf(
                            ScheduleBusinessEntity(
                                1,
                                "экзамен"
                            )
                        )
                    )
                        .subscribe(
                            {
                                Timber.i("completable")
                            }, {
                                Timber.e(it)
                            }
                        )
                },
                {
                    Timber.i(it)
                }
            )
    }

    @SuppressLint("CheckResult")
    private fun subscrubeToDayToWeek() {
        pickedDate.subscribe(
            {
                refreshLessonsList()
            }, {
                Timber.e(it)
            }
        )
    }

    @SuppressLint("CheckResult")
    private fun refreshLessonsList() {
        val value = if (pickedDate.value == null) 0 else pickedDate.value
        Timber.i("data = ${value}")
        scheduleRepository.getLessons(value)
            .subscribe(
                {
                    Timber.i("complete, list = ${it}")
                    scheduleList.onNext(
                        normalList(ListToArrayListConverter.convertToArrayList(it))
                    )
                },
                {
                    Timber.i(it)
                }
            )
    }

    private fun normalList(arrayList: ArrayList<ScheduleItem>): ArrayList<ScheduleItem> {
        if (arrayList.isNotEmpty()) {
            arrayList.apply {
                add(
                    0, ScheduleItem.ScheduleHeaderItem(
                        3,
                        "9:00",
                        "14:10"
                    )
                )
                add(this.size, ScheduleItem.ScheduleAddItem())
            }
        }
        return arrayList
    }


    @SuppressLint("CheckResult")
    private fun saveDemo() {
        val dayId = 0
        val dayId1 = 1
        val dayId2 = 2
        val dayId3 = 3
        val subjectGroupId1 = 1
        scheduleRepository.saveScheduleSubject(
            listOf(
                ScheduleSubjectEntity(
                    1,
                    dayId,
                    "Математика",
                    "415 лекция",
                    "9:00",
                    "10:30",
                    0
                ),
                ScheduleSubjectEntity(
                    2,
                    dayId,
                    "Русский",
                    "405 лекция",
                    "9:00",
                    "10:30",
                    1
                ),
                ScheduleSubjectEntity(
                    3,
                    dayId,
                    "Английский",
                    "215 лекция",
                    "9:00",
                    "10:30",
                    2
                ),



                ScheduleSubjectEntity(
                    4,
                    dayId1,
                    "Информатика",
                    "501 лекция",
                    "9:00",
                    "10:30",
                    0
                ),
                ScheduleSubjectEntity(
                    5,
                    dayId1,
                    "Инж. графика",
                    "615 практика",
                    "10:50",
                    "12:20",
                    1
                ),
                ScheduleSubjectEntity(
                    6,
                    dayId1,
                    "Информатика практика",
                    "105 лекция",
                    "12:40",
                    "14:10",
                    2
                ),



                ScheduleSubjectEntity(
                    7,
                    dayId2,
                    "Философия",
                    "115 лекция",
                    "9:00",
                    "10:30",
                    0
                ),
                ScheduleSubjectEntity(
                    8,
                    dayId2,
                    "Химия",
                    "213 практика",
                    "10:50",
                    "12:20",
                    1
                ),
                ScheduleSubjectEntity(
                    9,
                    dayId2,
                    "Биология",
                    "231 практика",
                    "12:40",
                    "14:10",
                    2
                ),


                ScheduleSubjectEntity(
                    10,
                    dayId3,
                    "Физ-ра",
                    "314 практика",
                    "9:00",
                    "10:30",
                    0
                ),
                ScheduleSubjectEntity(
                    11,
                    dayId3,
                    "Русский",
                    "230 практика",
                    "10:50",
                    "12:20",
                    1
                ),
                ScheduleSubjectEntity(
                    12,
                    dayId3,
                    "Английский",
                    "320 практика",
                    "12:40",
                    "14:10",
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
                    subjectGroupId1,
                    "забрать тетрадь"
                ),
                ScheduleBusinessEntity(
                    subjectGroupId1,
                    "сдать лабораторную"
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




}

data class DayToWeek(
    val day: Int,
    val week: Int
)