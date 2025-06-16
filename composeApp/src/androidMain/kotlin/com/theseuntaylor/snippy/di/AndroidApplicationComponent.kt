package com.theseuntaylor.snippy.di

import android.app.Application
import io.ktor.client.engine.okhttp.OkHttp
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(AppScope::class)
@SingleIn(AppScope::class)
abstract class AndroidApplicationComponent(val application: Application) : SharedApplicationComponent {

    override fun getHttpClientEngine() = OkHttp.create()

}