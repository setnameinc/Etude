package com.setnameinc.etude.mainschedule.domain

import com.setnameinc.etude.mainschedule.ui.ScheduleItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import timber.log.Timber

class ScheduleRepository {

    //day range from 0(monday) to 5(saturday)
    fun getLessons(
        day: Int?
    ): Flowable<List<ScheduleItem.ScheduleSubjectItem>> {
        val realm = Realm.getDefaultInstance()
        return Flowable.fromCallable {
            realm.where(ScheduleSubjectEntity::class.java)
                .equalTo("dayId", day)
                .sort("priority", Sort.ASCENDING)
                .findAll()
                .map {
                    val businesses =
                        realm.where(ScheduleBusinessEntity::class.java)
                            .equalTo("subjectGroupId", it.subjectGroupId)
                            .findAll()
                    LessonsConverter.fromDatabase(it, businesses)
                }
        }

    }

    fun getSubjectGroupId(
        day: Int?,
        week: Int?,
        name: String?
    ): Int {
        val realm = Realm.getDefaultInstance()
        return realm.where(ScheduleEntity::class.java)
            .equalTo("day", day)
            .equalTo("week", week)
            .findAllAsync()
            .map { scheduleEntity ->
                val groupId = scheduleEntity?.groupId
                Timber.i("groupId = ${groupId}")
                realm.where(ScheduleSubjectEntity::class.java)
                    .equalTo("groupId", groupId)
                    /*.equalTo("name", name)*/
                    .findFirstAsync()
                    .subjectGroupId
            }.first()
    }

    fun saveScheduleDay(scheduleEntity: List<ScheduleEntity>) =
        Completable.fromCallable {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    it.insertOrUpdate(scheduleEntity)
                }
            }
        }

    fun saveScheduleSubject(scheduleSubjectEntity: List<ScheduleSubjectEntity>) =
        Completable.fromCallable {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    it.insertOrUpdate(scheduleSubjectEntity)
                }
            }
        }

    fun saveScheduleBusinessItem(scheduleBusinessEntity: List<ScheduleBusinessEntity>) =
        Completable.fromCallable {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    it.insertOrUpdate(scheduleBusinessEntity)
                }
            }
        }
}