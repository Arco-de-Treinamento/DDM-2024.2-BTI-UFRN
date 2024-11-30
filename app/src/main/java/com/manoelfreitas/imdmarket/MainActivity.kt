package com.manoelfreitas.imdmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import com.manoelfreitas.imdmarket.ui.screen.splash.SplashScreen
import com.manoelfreitas.imdmarket.ui.screen.auth.login.LoginScreen
import com.manoelfreitas.imdmarket.ui.screen.main.menu.MenuScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.addproduct.AddProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.editproduct.EditProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.deleteproduct.DeleteProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.listproducts.ListProductScreen
import com.manoelfreitas.imdmarket.ui.screen.auth.forgotpassword.ForgotPasswordScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(1000)
        showSplashScreen = false
    }

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("menu"){
            MenuScreen(navController)
        }
        composable("additem"){
            AddProductScreen(navController)
        }
        composable("edititem"){
            EditProductScreen(navController)
        }
        composable("deleteitem"){
            DeleteProductScreen(navController)
        }
        composable("listitems"){
            ListProductScreen(navController)
        }
        composable("forgotpassword"){
            ForgotPasswordScreen(navController)
        }
    }
}

fun NavController.navigateToLogin(){
    navigate("login"){
        popUpTo("splash"){inclusive = true}
    }
}

fun NavController.navigateToMenu(){
    navigate("menu"){
        popUpTo("login"){inclusive = true}
    }
}

fun NavController.returnToLogin(scope: CoroutineScope){
    scope.launch {
        delay(2000)
        navigate("login"){
            popUpTo("forgotpassword"){inclusive = true}
        }
    }
}
