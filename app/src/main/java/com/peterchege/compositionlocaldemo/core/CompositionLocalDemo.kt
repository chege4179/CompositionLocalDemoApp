package com.peterchege.compositionlocaldemo.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CompositionLocalDemo :Application() {

    override fun onCreate() {
        super.onCreate()

    }
}