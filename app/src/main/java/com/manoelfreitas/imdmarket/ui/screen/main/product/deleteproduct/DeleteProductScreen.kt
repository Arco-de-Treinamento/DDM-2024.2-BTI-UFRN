package com.manoelfreitas.imdmarket.ui.screen.main.product.deleteproduct

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.manoelfreitas.imdmarket.ui.screen.main.product.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteProductScreen(navController: NavController){
    var productCode: Int? by remember {(mutableStateOf(0))}
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
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Text(text = "DELETAR PRODUTO", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(value = productCode.toString() ?: "",
                onValueChange = {
                    value -> productCode = value.toIntOrNull()
                },
                label = {Text("Código do produto:")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        deleteProduct(
                            context = context,
                            productCode = productCode
                        )
                    }) {
                    Text("Deletar")
                }
                Button(onClick = {
                    productCode = 0
                },
                ) {
                    Text("Limpar")
                }
            }
        }
    }
}


fun deleteProduct(context: Context, productCode: Int?) {
    if(productCode != null) {
        ProductViewModel().deleteProduct(productCode)

        Toast.makeText(
            context,
            "Item removido com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
    }else {
        Toast.makeText(
            context,
            "Informe o código do produto!",
            Toast.LENGTH_SHORT
        ).show()
    }
}