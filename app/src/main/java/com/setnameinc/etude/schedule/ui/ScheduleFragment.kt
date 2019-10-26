package com.setnameinc.etude.schedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.setnameinc.etude.R
import com.setnameinc.etude.bottommenu.ui.BottomMenuFragment
import com.setnameinc.etude.mainschdule.ui.ScheduleMainFragment
import com.setnameinc.etude.topmenu.ui.TopMenuFragment

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragments()
    }

    private fun setupFragments(){
        fragmentManager?.beginTransaction()?.replace(R.id.upperComponent,
            TopMenuFragment()
        )?.commit()
        fragmentManager?.beginTransaction()?.replace(R.id.bottomComponent,
            BottomMenuFragment()
        )?.commit()
        fragmentManager?.beginTransaction()?.replace(R.id.mainComponent,
            ScheduleMainFragment()
        )?.commit()
    }

}