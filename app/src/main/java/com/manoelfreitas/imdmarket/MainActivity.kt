package com.manoelfreitas.imdmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.manoelfreitas.imdmarket.navigation.AppNavigation
import com.manoelfreitas.imdmarket.user.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adminUser = UserViewModel( this).findByName("admin")
        if (adminUser == null)
            // Cria conta admin
            UserViewModel(this).createUser(username = "admin", password = "admin")

        installSplashScreen()

        setContent {
            AppNavigation()
        }
    }
}


