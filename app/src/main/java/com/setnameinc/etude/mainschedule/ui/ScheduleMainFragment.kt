package com.setnameinc.etude.mainschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.component_menu_main.*
import timber.log.Timber

class ScheduleMainFragment : Fragment(R.layout.component_menu_main) {

    private val behaviourList = BehaviorSubject.create<ArrayList<ScheduleItem>>()

    init {
        refreshList()
    }

    private fun refreshList(){
        behaviourList.onNext(
            arrayListOf(
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
                ),
                ScheduleItem.ScheduleAddItem()
            )
        )
    }

    private val scheduleAdapter: ScheduleAdapter by lazy {
        ScheduleAdapter(
            { activity?.findNavController(R.id.scheduleMainHostFragment)?.navigate(R.id.toAddBusinessAction) },
            { scheduleHeaderItem, adapterPosition, state ->
                val localeItem = scheduleHeaderItem
                if (behaviourList.value != null) {
                    val list = behaviourList.value!!
                    if (state == ScheduleSubjectStates.COLLAPSED) {
                        var count = adapterPosition
                        localeItem.listOfBaseness.forEach {
                            list.add(++count, it)
                            scheduleAdapter.notifyItemInserted(count)
                        }
                    } else {
                        localeItem.listOfBaseness.forEach {
                            list.remove(it)
                        }
                        scheduleAdapter.notifyItemRangeRemoved(
                            adapterPosition + 1,
                            adapterPosition + 1 + localeItem.listOfBaseness.size
                        )
                    }
                    behaviourList.onNext(list)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleRecyclerView.apply {
            adapter = scheduleAdapter
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        }
        behaviourList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(AndroidLifecycleScopeProvider.from(this))
            .subscribe(
            {
                scheduleAdapter.submitList(it)
            },
            {
                Timber.e(it)
            }
        )
    }
}