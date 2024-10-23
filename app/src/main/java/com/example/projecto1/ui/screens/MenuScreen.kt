package com.example.projecto1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "This is MenuScreen", modifier = Modifier.padding(top = 20.dp))
        Button(onClick = {navController.navigate("home")}) {
            Text(text = "Home")
        }
        Button(onClick = {navController.navigate("components")}) {
            Text(text = "Components")
        }
    }
}