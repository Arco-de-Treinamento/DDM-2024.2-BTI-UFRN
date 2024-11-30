package com.manoelfreitas.imdmarket.ui.screen.main.product.listproducts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manoelfreitas.imdmarket.R
import com.manoelfreitas.imdmarket.ui.screen.main.product.Product
import com.manoelfreitas.imdmarket.ui.screen.main.product.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductScreen(navController: NavController){
    val productViewModel: ProductViewModel = viewModel()
    var context = LocalContext.current

    var _products: List<Product> = productViewModel.listProducts(context)

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
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center){
            LazyColumn(){
                items(_products.size){
                    index -> ProductListItem(product = _products[index])
                }
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(painter = painterResource(id = R.drawable.baseline_storefront_24),
                contentDescription = "Logo App",
                modifier = Modifier.size(48.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = product.productName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = product.productDescription, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }

}