package com.setnameinc.etude.scheduleadd.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.setnameinc.etude.R
import com.setnameinc.etude.mainschedule.ui.ScheduleItem
import com.setnameinc.etude.mainschedule.ui.ScheduleSharedViewModel
import com.setnameinc.etude.utils.viewModel
import kotlinx.android.synthetic.main.fragment_schedule_add.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import timber.log.Timber

class ScheduleAddFragment : Fragment(R.layout.fragment_schedule_add), KodeinAware {

    override val kodein: Kodein by kodein()
    private val scheduleSharedViewModel: ScheduleSharedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeImageView.setOnClickListener {
            activity?.findNavController(R.id.scheduleMainHostFragment)?.popBackStack()
        }
        confirmImageView.setOnClickListener {
            Timber.d("name = |${chosenSubjectTextView.text.toString()}|")
            scheduleSharedViewModel.forSaveInRealmItem
                .onNext(
                    SaveItemsdfasd(
                        chosenSubjectTextView.text.toString(),
                        ScheduleItem.ScheduleBusinessItem(
                            descriptionEditText.text.toString()
                        )
                    )
                )
            activity?.findNavController(R.id.scheduleMainHostFragment)?.popBackStack()
        }
    }

}

data class SaveItemsdfasd(
    val name: String,
    val businessItem: ScheduleItem.ScheduleBusinessItem
)