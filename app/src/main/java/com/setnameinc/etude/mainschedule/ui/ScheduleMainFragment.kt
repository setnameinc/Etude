package com.setnameinc.etude.mainschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import kotlinx.android.synthetic.main.component_menu_main.*

class ScheduleMainFragment : Fragment(R.layout.component_menu_main) {

    private val scheduleAdapter: ScheduleAdapter by lazy {
        ScheduleAdapter {
            activity?.findNavController(R.id.scheduleMainHostFragment)?.navigate(R.id.toAddBusinessAction)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleRecyclerView.apply {
            adapter = scheduleAdapter
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        }
        scheduleAdapter.addList(
            listOf(
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
                    "",
                    listOf()
                ),
                ScheduleItem.ScheduleSubjectItem(
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf()
                )
            )
        )
    }
}