package com.manoelfreitas.imdmarket.ui.screen.auth.forgotpassword

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.manoelfreitas.imdmarket.navigation.navigateToLogin
import com.manoelfreitas.imdmarket.ui.components.PasswordTextField
import com.manoelfreitas.imdmarket.user.viewModel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
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
            PasswordTextField(
                label = "Informe sua senha",
                value = password,
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Confirme a senha:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            PasswordTextField(
                label = "Informe sua senha",
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {
                    changePassword(
                        context = context,
                        username = username,
                        password = password,
                        confPassword = confirmPassword,
                        navController = navController
                    )
                }
            }) {
                Text("Mudar senha")
            }
        }
    }
}

fun changePassword(context: Context, username: String, password: String, confPassword: String, navController: NavController): () -> Unit {
    if (password == confPassword) {

        try {
            UserViewModel(context).changePassword(username, password)
            Toast.makeText(context, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show()

            navController.navigateToLogin()
        } catch (e: Exception) {
            Toast.makeText(context, "Falha ao alterar a senha!", Toast.LENGTH_SHORT).show()
            Log.e("ChangePassword", "Erro ao alterar a senha: ${e.message}")
        }

    } else {
        Toast.makeText(context, "Senhas não conferem!", Toast.LENGTH_SHORT).show()
    }

    return {}
}