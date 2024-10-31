package com.example.projecto1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController){
    Column {
        Text(text = "This is MenuScreen")
        Button(onClick = {navController.navigate("home")}) {
            Text(text = "Home")
        }
        Button(onClick = {navController.navigate("components")}) {
            Text(text = "Components")
        }
        Button(onClick = {navController.navigate("secondPlaneProcess")}){
            Text(text = "Proceso en segundo plano")
        }

    }
}