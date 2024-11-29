package com.manoelfreitas.imdmarket.ui.screen.auth.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manoelfreitas.imdmarket.navigateToMenu
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        OutlinedTextField(value = username,
            onValueChange = {username = it },
            label = {Text("Usuário")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = password,
            onValueChange = {password = it },
            label = {Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { coroutineScope.launch { checkLogin(
            context = context,
            username = username,
            password = password,
            navController = navController)}}) {
            Text("Login")
        }
    }
}

fun checkLogin(context: Context, username: String, password: String, navController: NavController): () -> Unit {
    if (username == "admin" && password == "admin") {
        // Navigate to MenuScreen
        navController.navigateToMenu()
    } else {
        Toast.makeText(context, "Falha ao realizar o login!", Toast.LENGTH_SHORT).show()
    }

    return {}
}