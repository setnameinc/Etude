package com.setnameinc.etude.scheduleadd.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.setnameinc.etude.R
import kotlinx.android.synthetic.main.fragment_schedule_add.*

class ScheduleAddFragment : Fragment(R.layout.fragment_schedule_add) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //chosenSubjectTextView.text = "Математика 1"
        //descriptionEditText.text
        closeImageView.setOnClickListener {
            findNavController().popBackStack()
        }
        confirmImageView.setOnClickListener {
            //confirm
        }
    }

}