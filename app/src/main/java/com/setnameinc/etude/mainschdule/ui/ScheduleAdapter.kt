package com.setnameinc.etude.mainschdule.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import timber.log.Timber

class ScheduleAdapter : RecyclerView.Adapter<ScheduleViewHolder>() {

    private val list = arrayListOf<ScheduleItem>()

    fun addList(
        localeList: List<ScheduleItem>
    ) {
        list.apply {
            clear()
            addAll(localeList)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleViewHolder = when (viewType) {
        ScheduleTypes.mainHeaderType ->
            ScheduleHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_header, parent, false)
            )
        ScheduleTypes.subjectType ->
            ScheduleSubjectViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_subject, parent, false)
            )
        ScheduleTypes.businessType ->
            ScheduleBusinessViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_business, parent, false)
            )
        else ->
            ScheduleBottomViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule_main_bottom, parent, false)
            )
    }

    inner class ScheduleSubjectViewHolder(
        val view: View
    ) : ScheduleViewHolder(view = view) {

        private var state: ScheduleSubjectStates = ScheduleSubjectStates.COLLAPSED

        init {
            view.setOnClickListener {
                Timber.i("position = $adapterPosition")
                val localeItem = list[adapterPosition] as ScheduleItem.ScheduleSubjectItem
                if (state == ScheduleSubjectStates.COLLAPSED) {
                    list.addAll(adapterPosition + 1, localeItem.listOfBaseness)
                    notifyItemRangeInserted(adapterPosition + 1, adapterPosition + localeItem.listOfBaseness.size)
                    notifyDataSetChanged()
                    state = ScheduleSubjectStates.EXPANDED
                } else {
                    localeItem.listOfBaseness.forEach {
                        list.remove(it)
                    }
                    /*notifyItemRangeRemoved(adapterPosition - localeItem.listOfBaseness.size, adapterPosition)*/
                    notifyDataSetChanged()
                    state = ScheduleSubjectStates.COLLAPSED
                }
            }
        }

        override fun bind(item: ScheduleItem) {
            val localeItem = item as ScheduleItem.ScheduleSubjectItem
        }
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int = list[position].type

}

enum class ScheduleSubjectStates {
    COLLAPSED,
    EXPANDED
}

class DateAdapterDiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {

    override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean =
        oldItem == newItem

}