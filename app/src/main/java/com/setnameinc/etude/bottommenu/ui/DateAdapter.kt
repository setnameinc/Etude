package com.setnameinc.etude.bottommenu.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import com.setnameinc.etude.bottommenu.domain.DateItem
import kotlinx.android.synthetic.main.item_date.view.*
import timber.log.Timber

class DateAdapter(
    val onClick: (DateItem) -> Unit
) : ListAdapter<DateItem, DateAdapter.BottomMenuViewHolder>(DateAdapterDiffCallback()) {

    private var currentPosition : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomMenuViewHolder =
        BottomMenuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_date,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: BottomMenuViewHolder, position: Int) {
        holder.setup(getItem(position))
        if (currentPosition == position) {
            holder.setupSelector()
        } else {
            holder.disableSelector()
        }
    }

    inner class BottomMenuViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                if (isNotSelected()) {
                    removePreviousSelector()
                    setCurrentSelector()
                }
            }
        }

        fun setup(item: DateItem) {
            Timber.d("item completely set")
            view.dateTextView.text = item.date
            if (item.countOfBanges != 0) {
                setupBadge(item)
            } else {
                disableBadge()
            }
        }

        private fun setupBadge(item: DateItem) {
            view.badgeCountTextView.apply {
                text = "${item.countOfBanges}"
                visibility = View.VISIBLE
            }
            view.badgeBackground.apply {
                background.setTint(Color.parseColor("#${item.notificationBackgroundColor}"))
                visibility = View.VISIBLE
            }
        }

        private fun disableBadge() {
            view.badgeCountTextView.visibility = View.GONE
            view.badgeBackground.visibility = View.GONE
        }

        fun setupSelector() {
            view.selectorView.visibility = View.VISIBLE
            view.dateTextView.setTextColor(Color.parseColor("#FAFAFA"))
        }

        fun disableSelector(){
            view.selectorView.visibility = View.GONE
            view.dateTextView.setTextColor(Color.parseColor("#212121"))
        }

        private fun setCurrentSelector() {
            currentPosition = adapterPosition
            notifyItemChanged(adapterPosition)
        }

        private fun removePreviousSelector() {
            notifyItemChanged(currentPosition)
        }

        private fun isNotSelected(): Boolean = currentPosition != adapterPosition

    }
}

class DateAdapterDiffCallback : DiffUtil.ItemCallback<DateItem>() {

    override fun areItemsTheSame(oldItem: DateItem, newItem: DateItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: DateItem, newItem: DateItem): Boolean =
        oldItem.date == newItem.date
                && oldItem.countOfBanges == newItem.countOfBanges

}