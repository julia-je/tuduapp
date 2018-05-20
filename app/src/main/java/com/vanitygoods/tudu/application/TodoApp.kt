package com.vanitygoods.tudu.application

import android.app.Application
import com.facebook.stetho.Stetho


class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}