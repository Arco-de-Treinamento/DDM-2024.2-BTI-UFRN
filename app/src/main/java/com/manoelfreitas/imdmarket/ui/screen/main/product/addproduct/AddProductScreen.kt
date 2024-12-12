package com.manoelfreitas.imdmarket.ui.screen.main.product.addproduct

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.manoelfreitas.imdmarket.product.model.Product
import com.manoelfreitas.imdmarket.product.viewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController){
    var productCode: Int? by remember {(mutableStateOf(0))}
    var productName: String? by remember {(mutableStateOf(""))}
    var productDescription: String? by remember {(mutableStateOf(""))}
    var productQuantity: Int? by remember {(mutableStateOf(0))}

    val context = LocalContext.current
    val productViewModel: ProductViewModel = viewModel()

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
                Text(text = "CADASTRAR PRODUTO", fontWeight = FontWeight.Bold, fontSize = 24.sp)
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
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = productName ?: "",
                onValueChange = {productName = it },
                label = {Text("Nome do Produto:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = productDescription ?: "",
                onValueChange = {productDescription = it },
                label = {Text("Descrição do Produto:") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = productQuantity.toString() ?: "",
                onValueChange = {
                    value -> productQuantity = value.toIntOrNull()
                },
                label = {Text("Quantidade em Estoque:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        addProduct(
                            context = context,
                            productViewModel = productViewModel,
                            productCode = productCode,
                            productName = productName,
                            productDescription = productDescription,
                            productQuantity = productQuantity
                        )
                    }) {
                    Text("Cadastrar")
                }
                Button(onClick = {
                    productCode = 0
                    productName = ""
                    productDescription = ""
                    productQuantity = 0
                },
                ) {
                    Text("Limpar")
                }
            }
        }
    }
}

fun addProduct(context: Context, productViewModel: ProductViewModel,
               productCode: Int?, productName: String?,
               productDescription: String?, productQuantity: Int?) {
    if(productCode != null && productName != null &&
        productDescription != null && productQuantity != null) {

        if (productViewModel.isProductExists(productCode)) {
            Toast.makeText(
                context,
                "Produto já cadastrado!",
                Toast.LENGTH_SHORT).show()
        }else{
            var _product = Product(
                productCode = productCode,
                productName = productName,
                productDescription = productDescription,
                productQuantity = productQuantity
            )

            productViewModel.addProduct(_product, context)

            Toast.makeText(
                context,
                "Item cadastrado com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }else {
        Toast.makeText(
            context,
            "Preencha todos os campos!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
