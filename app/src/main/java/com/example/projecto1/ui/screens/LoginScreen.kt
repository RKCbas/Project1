package com.example.projecto1.ui.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.projecto1.data.controller.LoginState
import com.example.projecto1.data.controller.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    val loginState by loginViewModel.loginState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginForm(navController, loginViewModel, loginState)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ShowLoginForm() {
//    var loginViewModel = viewModel()
//    val loginState by loginViewModel.loginState.collectAsState()
//    LoginForm(navController = rememberNavController(), loginViewModel, loginState)
//}

@Composable
fun LoginForm(navController: NavController, loginViewModel: LoginViewModel, loginState: LoginState) {

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
            AsyncImage(
                model = "https://marketizados.com/wp-content/uploads/2023/11/765900ba9-article-200807-github-gitguardbody-text.jpg",
                contentDescription = "Github logo",
                contentScale = ContentScale.Fit
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = user,
                onValueChange = { user = it },
                label = { Text("User") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    loginViewModel.login(user, password)
                }
            ) {
                Text("LOG IN")
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    navController.navigate("menu")
                }
            ) {
                Text("CREATE AN ACCOUNT")
            }

            when (loginState) {
                is LoginState.Idle -> {}
                is LoginState.Loading -> {
                    Text("Loading...", color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
                is LoginState.Error -> {
                    Text(
                        text = (loginState as LoginState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }
}