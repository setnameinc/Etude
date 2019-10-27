package com.setnameinc.etude.app.di

import com.setnameinc.etude.app.api.di.ApiInjectionModule
import com.setnameinc.etude.mainschedule.di.ScheduleInjectionModule
import org.kodein.di.Kodein

object AppInjectionModules {
    val module = Kodein.Module(AppInjectionModules.javaClass.name) {
        import(ApiInjectionModule.module)
        import(ScheduleInjectionModule.module)
    }
}