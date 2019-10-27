package com.setnameinc.etude.mainschedule.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import kotlinx.android.synthetic.main.item_schedule_main_subject.view.*

class ScheduleAdapter(
    val onClick: () -> Unit,
    val onHeaderClick: (ScheduleItem.ScheduleSubjectItem, Int, ScheduleSubjectStates) -> Unit
) : ListAdapter<ScheduleItem, ScheduleViewHolder>(
    DateAdapterDiffCallback()
) {

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
                    .inflate(R.layout.item_schedule_main_bottom, parent, false),
                onClick
            )
    }

    inner class ScheduleSubjectViewHolder(
        val view: View
    ) : ScheduleViewHolder(view = view) {

        private var state: ScheduleSubjectStates = ScheduleSubjectStates.COLLAPSED

        init {
            view.setOnClickListener {
                onHeaderClick(
                    getItem(adapterPosition) as ScheduleItem.ScheduleSubjectItem,
                    adapterPosition,
                    state
                )
                state = if (state == ScheduleSubjectStates.COLLAPSED) {
                    ScheduleSubjectStates.EXPANDED
                } else {
                    ScheduleSubjectStates.COLLAPSED
                }
            }
        }

        override fun bind(item: ScheduleItem) {
            val localeItem = item as ScheduleItem.ScheduleSubjectItem
            if (localeItem.listOfBaseness.isEmpty()){
                view.sizeTextView.visibility = View.INVISIBLE
            }
        }
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type

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