package com.setnameinc.etude.mainschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.setnameinc.etude.R
import kotlinx.android.synthetic.main.fragment_schedule_add.*

class ScheduleAddFragment : Fragment(R.layout.fragment_schedule_add){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeImageView.setOnClickListener {
            activity?.findNavController(R.id.scheduleMainHostFragment)?.popBackStack()
        }
    }
}