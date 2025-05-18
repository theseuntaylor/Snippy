package com.theseuntaylor.snippy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theseuntaylor.snippy.ui.destinations.home.HomeScreen
import com.theseuntaylor.snippy.ui.destinations.login.LoginScreen
import com.theseuntaylor.snippy.ui.destinations.signup.SignupScreen
import com.theseuntaylor.snippy.ui.navigation.Home
import com.theseuntaylor.snippy.ui.navigation.Login
import com.theseuntaylor.snippy.ui.navigation.SignUp

@Composable
internal fun SnippyApp(
    navController: NavHostController = rememberNavController(),
){
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            composable<Home> {
                HomeScreen(
                    onClickLoginButton = { navController.navigate(Login) },
                    onClickCreateAccountButton = { navController.navigate(SignUp) },
                )
            }

            composable<SignUp> { SignupScreen() }

            composable<Login> { LoginScreen()  }
        }
    }

}