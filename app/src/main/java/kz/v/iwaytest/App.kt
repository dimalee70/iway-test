package kz.v.iwaytest

import android.app.Application
import android.content.Context
import kz.v.auth.presentation.di.authModule
import kz.v.dep_inject.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        Timber.i("onCreate")
        super.onCreate()
        startKoin {
//            fileProperties()
            androidContext(this@App)
            modules(authModule)
            modules(networkModule)
        }
        appContext = applicationContext
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}