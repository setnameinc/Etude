package com.setnameinc.etude.bottommenu.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.setnameinc.etude.R
import com.setnameinc.etude.bottommenu.domain.DateItem
import kotlinx.android.synthetic.main.component_menu_bottom.*

class BottomMenuFragment : Fragment(R.layout.component_menu_bottom){

    private val dateAdapter: DateAdapter by lazy {
        DateAdapter {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datesRecyclerView.apply {
            adapter = dateAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        dateAdapter.submitList(
            listOf(
                DateItem(
                    "14\njuly",
                    "BC3232",
                    15
                ),
                DateItem(
                    "15\njuly",
                    "FFA123",
                    15
                ),
                DateItem(
                    "16\njuly",
                    "6527B7",
                    15
                ),
                DateItem(
                    "17\njuly",
                    "2272AF",
                    15
                ),
                DateItem(
                    "18\njuly",
                    "10107A",
                    15
                ),
                DateItem(
                    "19\njuly",
                    "BC3232",
                    15
                )
            )
        )

    }
}