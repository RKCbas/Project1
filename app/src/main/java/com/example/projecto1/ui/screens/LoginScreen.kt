package com.example.projecto1.ui.screens

import android.text.method.PasswordTransformationMethod
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginForm(navController)
    }

}

@Composable
fun LoginForm(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ),
        modifier = Modifier
            .padding(40.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            //https://1000marcas.net/wp-content/uploads/2020/02/logo-GitHub.png
//            AsyncImage(
//                model = "https://www.google.com/url?sa=i&url=https%3A%2F%2F1000marcas.net%2Fgithub-logo%2F&psig=AOvVaw18u0YbmxbhnxunmRaHDUG-&ust=1729862894375000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOCFy8uPp4kDFQAAAAAdAAAAABAE",
//                contentDescription = "Github Logo",
//                contentScale = ContentScale.Fit
//            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = user,
                onValueChange = { user = it },
                maxLines = 1,
                label = { Text(text = "User") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                maxLines = 1,
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = { /*TO DO*/ }
            ) {
                Text(text = "LOG IN")
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = { navController.navigate("home") }
            ) {
                Text(text = "CREATE AN ACCOUNT")
            }
        }
    }
}