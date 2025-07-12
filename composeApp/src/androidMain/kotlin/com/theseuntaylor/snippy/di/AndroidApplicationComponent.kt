package com.theseuntaylor.snippy.di

import android.app.Application
import io.ktor.client.engine.okhttp.OkHttp
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(scope = AppScope::class)
@SingleIn(scope = AppScope::class)
abstract class AndroidApplicationComponent: SharedApplicationComponent {
    override fun httpClientEngine() = OkHttp.create()

    companion object
}