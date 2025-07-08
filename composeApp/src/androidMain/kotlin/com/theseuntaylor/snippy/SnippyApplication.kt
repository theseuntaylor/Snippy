package com.theseuntaylor.snippy

import android.app.Application
import com.theseuntaylor.snippy.di.AndroidApplicationComponent
import com.theseuntaylor.snippy.di.create

class SnippyApplication: Application() {
    val component: AndroidApplicationComponent by lazy {
        AndroidApplicationComponent::class.create()
    }
}