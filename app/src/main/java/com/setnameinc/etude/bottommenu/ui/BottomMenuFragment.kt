package com.setnameinc.etude.bottommenu.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.setnameinc.etude.R
import com.setnameinc.etude.bottommenu.domain.DateItem
import com.setnameinc.etude.mainschedule.ui.DayToWeek
import com.setnameinc.etude.mainschedule.ui.ScheduleSharedViewModel
import com.setnameinc.etude.utils.viewModel
import kotlinx.android.synthetic.main.component_menu_bottom.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import timber.log.Timber

class BottomMenuFragment : Fragment(R.layout.component_menu_bottom), KodeinAware {

    override val kodein: Kodein by kodein()
    private val scheduleSharedViewModel: ScheduleSharedViewModel by viewModel()

    private val dateAdapter: DateAdapter by lazy {
        DateAdapter { item, position ->
            scheduleSharedViewModel.pickedDate.onNext(position)
        }
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datesRecyclerView.apply {
            adapter = dateAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        dateAdapter.submitList(fakeDates())

    }

    private fun fakeDates() = listOf(
        DateItem(
            "28\nоктября",
            "BC3232",
            1
        ),
        DateItem(
            "29\nоктября",
            "FFA123",
            1
        ),
        DateItem(
            "30\nоктября",
            "6527B7",
            1
        ),
        DateItem(
            "31\nоктября",
            "2272AF",
            1
        ),
        DateItem(
            "1\nноября",
            "10107A",
            1
        ),
        DateItem(
            "2\nноября",
            "BC3232",
            1
        )
    )

}