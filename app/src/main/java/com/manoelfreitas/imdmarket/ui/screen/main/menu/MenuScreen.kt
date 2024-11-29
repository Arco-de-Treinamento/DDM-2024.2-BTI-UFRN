package com.manoelfreitas.imdmarket.ui.screen.main.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(navController: NavController){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
            ){
            Button(onClick = { coroutineScope.launch{navController.navigate("additem") }}){
                Text("Cadastrar Produto")
            }
            Button(onClick = { coroutineScope.launch{navController.navigate("listitems") }}){
                Text("Listar Produto")
            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
        ){
            Button(onClick = { coroutineScope.launch{navController.navigate("edititem") }}){
                Text("Alterar Produto")
            }
            Button(onClick = { coroutineScope.launch{navController.navigate("deleteitem") }}){
                Text("Deletar Produto")
            }
        }
    }
}

