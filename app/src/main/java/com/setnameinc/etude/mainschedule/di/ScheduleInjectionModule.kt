package com.setnameinc.etude.mainschedule.di

import com.setnameinc.etude.mainschedule.ui.ScheduleSharedViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

object ScheduleInjectionModule {

    val module = Kodein.Module(ScheduleInjectionModule.javaClass.name){

        bind<ScheduleSharedViewModel>() with singleton {
            ScheduleSharedViewModel()
        }

    }

}