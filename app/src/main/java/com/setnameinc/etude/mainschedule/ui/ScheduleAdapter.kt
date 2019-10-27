package com.setnameinc.etude.mainschedule.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import kotlinx.android.synthetic.main.item_schedule_main_subject.view.*

class ScheduleAdapter(
    val onClick: () -> Unit
) : RecyclerView.Adapter<ScheduleViewHolder>() {

    private val list = arrayListOf<ScheduleItem>()

    fun addList(
        localeList: List<ScheduleItem>
    ) {
        list.apply {
            clear()
            addAll(localeList)
            add(ScheduleItem.ScheduleAddItem())
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
                    .inflate(R.layout.item_schedule_main_bottom, parent, false),
                onClick
            )
    }

    inner class ScheduleSubjectViewHolder(
        val view: View
    ) : ScheduleViewHolder(view = view) {

        private var state: ScheduleSubjectStates = ScheduleSubjectStates.COLLAPSED

        init {
            view.setOnClickListener { it ->
                val localeItem = list[adapterPosition] as ScheduleItem.ScheduleSubjectItem
                if (state == ScheduleSubjectStates.COLLAPSED) {
                    var count = adapterPosition
                    localeItem.listOfBaseness.forEach {
                        list.add(++count, it)
                        notifyItemInserted(count)
                    }
                    state = ScheduleSubjectStates.EXPANDED
                } else {
                    localeItem.listOfBaseness.forEach {
                        list.remove(it)
                    }
                    notifyItemRangeRemoved(
                        adapterPosition + 1,
                        adapterPosition + 1 + localeItem.listOfBaseness.size
                    )
                    state = ScheduleSubjectStates.COLLAPSED
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