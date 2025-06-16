package com.theseuntaylor.snippy.di

import com.theseuntaylor.snippy.data.repository.auth.AuthRepository
import com.theseuntaylor.snippy.data.source.remote.api.auth.AuthApi
import com.theseuntaylor.snippy.ui.viewmodels.CreateAccountViewmodel

interface AuthenticationComponent {

    val createAccountViewmodel: CreateAccountViewmodel

    val repository: AuthRepository

    val authApi: AuthApi

}