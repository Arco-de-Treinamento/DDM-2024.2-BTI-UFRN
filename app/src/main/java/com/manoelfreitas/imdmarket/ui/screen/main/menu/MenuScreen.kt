package com.manoelfreitas.imdmarket.ui.screen.main.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class MenuItem(val title: String, val route: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController){
    val menuItems = listOf(
        MenuItem("Cadastrar Produto", "additem"),
        MenuItem("Listar Produto", "listitems"),
        MenuItem("Alterar Produto", "edititem"),
        MenuItem("Deletar Produto", "deleteitem")
    )

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
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Text(text = "O que deseja Fazer?", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(48.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(menuItems){
                    menuItem -> MenuButton(
                    navController = navController,
                    title = menuItem.title,
                    route = menuItem.route)
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "IMD Market v1.0.0",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun MenuButton(navController: NavController, route: String, title: String){
    val coroutineScope = rememberCoroutineScope()

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            coroutineScope.launch{navController.navigate(route)
            }
        }){
        Text(
            title,
            fontSize = 16.sp,
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}