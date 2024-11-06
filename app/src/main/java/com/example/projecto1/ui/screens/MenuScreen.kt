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
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
    ) {
        Text(text = "This is MenuScreen", modifier = Modifier.padding(top = 20.dp))
        Button(onClick = {navController.navigate("home")}) {
            Text(text = "Home")
        }
        Button(onClick = {navController.navigate("components")}) {
            Text(text = "Components")
        }
        Button(onClick = {navController.navigate("secondPlaneProcess")}){
            Text(text = "Proceso en segundo plano")
        }
        Button(onClick = { navController.navigate("CalendarContacts") }) {
            Text(text = "Calendario y Contactos")
        }
        Button(onClick = { navController.navigate("Biometrics") }) {
            Text(text = "Autentificacion por biometricos")
        }
        Button(onClick = { navController.navigate("Camera") }) {
            Text(text = "Camara")
        }
        Button(onClick = { navController.navigate("homeMaps") }) {
            Text(text = "Maps")
        }
    }
}