package com.manoelfreitas.imdmarket.product.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.manoelfreitas.imdmarket.product.model.Product


class ProductViewModel : ViewModel() {
    private val _products = mutableStateListOf<Product>()

    fun addProduct(product: Product, context: Context) {
        _products.add(product)
        saveToSharedPreferences(context)
    }

    fun editProduct(product: Product, context: Context) {
        val index = _products.indexOfFirst {
            it.productCode == product.productCode
        }

        if (index != -1) {
            _products[index] = product
            saveToSharedPreferences(context)
        }
    }

    fun deleteProduct(productCode: Int) {
        val product = _products.find { it.productCode == productCode }

        if (product != null) {
            _products.remove(product)
        }
    }

    fun listProducts(context: Context): List<Product> {
        _products.clear()
        _products.addAll(loadFromSharedPreferences(context))

        return _products
    }

    fun isProductExists(productCode: Int): Boolean {
        return (_products.isNotEmpty() && _products.any { it.productCode == productCode })
    }

    private fun saveToSharedPreferences(context: Context){
        val sharedPreferences = context.getSharedPreferences("product_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val productsJson = Gson().toJson(_products)

        editor.putString("products", productsJson)
        editor.apply()
    }

    private fun loadFromSharedPreferences(context: Context): List<Product> {
        val sharedPreferences = context.getSharedPreferences("product_data", Context.MODE_PRIVATE)
        val productsJson = sharedPreferences.getString("products", null)

        if (productsJson != null) {
            return Gson().fromJson(productsJson, Array<Product>::class.java).toList()
        }
        return emptyList()
    }
}

