package com.setnameinc.etude.mainschedule.domain

import com.setnameinc.etude.mainschedule.ui.ScheduleItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.Sort

class ScheduleRepository {

    //day range from 0(monday) to 5(saturday)
    fun getLessons(
        day: Int,
        week: Int
    ): Flowable<List<ScheduleItem.ScheduleSubjectItem>> {

        val realm = Realm.getDefaultInstance()

        return Flowable.fromCallable {
            realm.where(ScheduleEntity::class.java)
                .equalTo("day", day)
                .equalTo("week", week)
                .findAllAsync()
                .flatMap { scheduleEntity ->
                    val groupId = scheduleEntity?.groupId
                    realm.where(ScheduleSubjectEntity::class.java)
                        .equalTo("groupId", groupId)
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