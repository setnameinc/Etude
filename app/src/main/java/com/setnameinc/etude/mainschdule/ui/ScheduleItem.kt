package com.setnameinc.etude.mainschdule.ui

sealed class ScheduleItem(
    open val type: Int
) {
    data class ScheduleHeaderItem(
        val size: Int,
        val startTime: String,
        val endTime: String
    ) : ScheduleItem(
        type = ScheduleTypes.mainHeaderType
    )

    data class ScheduleSubjectItem(
        val name: String,
        val objectType: String,
        val classroom: String,
        val startTime: String,
        val endTime: String,
        val listOfBaseness:List<ScheduleBusinessItem>
    ) : ScheduleItem(
        type = ScheduleTypes.subjectType
    )

    data class ScheduleBusinessItem(
        val description: String
    ) : ScheduleItem(
        type = ScheduleTypes.businessType
    )

    override fun equals(other: Any?): Boolean {
        return other.hashCode() == this.hashCode()
                && other?.javaClass == this.javaClass
    }

}

object ScheduleTypes {
    const val mainHeaderType = 0
    const val subjectType = 1
    const val businessType = 2
}