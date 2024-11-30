package com.manoelfreitas.imdmarket.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manoelfreitas.imdmarket.R
import com.manoelfreitas.imdmarket.ui.navigation.navigateToLogin
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.baseline_storefront_24),
            contentDescription = "Logo App",
            modifier = Modifier.size(256.dp))
    }
    
    LaunchedEffect(key1 = true){
        delay(1000)
        navController.navigateToLogin()
    }
}
