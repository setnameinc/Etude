package com.setnameinc.etude.mainschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setnameinc.etude.R
import com.setnameinc.etude.utils.viewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.component_menu_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import timber.log.Timber

class ScheduleMainFragment : Fragment(R.layout.component_menu_main), KodeinAware {

    override val kodein: Kodein by kodein()
    private val scheduleSharedViewModel: ScheduleSharedViewModel by viewModel()

    private val scheduleAdapter: ScheduleAdapter by lazy {
        ScheduleAdapter(
            { activity?.findNavController(R.id.scheduleMainHostFragment)?.navigate(R.id.toAddBusinessAction) },
            { scheduleHeaderItem, adapterPosition, state ->
                val localeItem = scheduleHeaderItem
                if (scheduleSharedViewModel.scheduleList.value != null) {
                    val list = scheduleSharedViewModel.scheduleList.value!!
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
                    scheduleSharedViewModel.scheduleList.onNext(list)
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
        scheduleSharedViewModel.scheduleList
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