package com.manoelfreitas.imdmarket.ui.screen.auth.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import com.manoelfreitas.imdmarket.ui.navigation.navigateToMenu
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "IMD Market")
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Usuário:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Informe seu usuário") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Senha:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Informe sua senha") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    checkLogin(
                        context = context,
                        username = username,
                        password = password,
                        navController = navController
                    )
                }
            }) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Esqueci minha senha",
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                    navController.navigate("forgotpassword")
                }
            )
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