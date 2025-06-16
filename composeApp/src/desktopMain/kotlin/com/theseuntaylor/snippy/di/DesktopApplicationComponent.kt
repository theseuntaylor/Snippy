package com.theseuntaylor.snippy.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(AppScope::class)
@SingleIn(AppScope::class)
abstract class DesktopApplicationComponent: SharedApplicationComponent {

    override fun getHttpClientEngine() = Java.create(

    )
}