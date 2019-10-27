package com.setnameinc.etude.app

import android.app.Application
import android.content.ContentResolver
import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import com.setnameinc.etude.app.di.AppInjectionModules
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import io.realm.RealmConfiguration
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

class App : MultiDexApplication(), KodeinAware {

    override val kodein = ConfigurableKodein()

    private val sharedPreferences: SharedPreferences by instance()

    override fun onCreate() {
        super.onCreate()

        setupDependencyInjection()

        setupRealm()

        setupTimber()

        setupRx()
    }

    private fun setupRx() {
        RxJavaPlugins.setErrorHandler { Timber.e(it, "Unhandled RxJava exception") }
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupRealm() {

        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun setupDependencyInjection() {
        kodein.apply {
            mutable = true

            clear()

            addImport(AppInjectionModules.module)

            addImport(Kodein.Module(javaClass.name) {
                bind<ContentResolver>() with singleton { this@App.contentResolver }
                bind<Application>() with singleton { this@App }
            })
        }
    }
}