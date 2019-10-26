package com.setnameinc.etude.app.di

import com.setnameinc.etude.app.api.di.ApiInjectionModule
import org.kodein.di.Kodein

object AppInjectionModules {
    val module = Kodein.Module(AppInjectionModules.javaClass.name) {
        import(ApiInjectionModule.module)
        /*import(NetworkInjectionModule.module)
        import(BookmarkInjectionModule.module)*/
    }
}