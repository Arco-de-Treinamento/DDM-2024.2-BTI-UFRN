package com.manoelfreitas.imdmarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manoelfreitas.imdmarket.ui.screen.auth.forgotpassword.ForgotPasswordScreen
import com.manoelfreitas.imdmarket.ui.screen.auth.login.LoginScreen
import com.manoelfreitas.imdmarket.ui.screen.auth.createaccount.CreateAccountScreen
import com.manoelfreitas.imdmarket.ui.screen.main.menu.MenuScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.addproduct.AddProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.deleteproduct.DeleteProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.editproduct.EditProductScreen
import com.manoelfreitas.imdmarket.ui.screen.main.product.listproducts.ListProductScreen
import com.manoelfreitas.imdmarket.ui.screen.splash.SplashScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        composable("createaccount"){
            CreateAccountScreen(navController)
        }
    }
}

fun NavController.navigateToLogin(){
    navigate("login"){
        popUpTo("splash"){inclusive = true}
    }
}

fun NavController.navigateToCreateAccount(){
    navigate("createaccount"){
        popUpTo("login"){inclusive = true}
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