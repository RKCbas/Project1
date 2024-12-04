package com.example.projecto1.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projecto1.R
import com.example.projecto1.data.controller.ServiceViewModel
import com.example.projecto1.data.database.AppDatabase
import com.example.projecto1.data.database.DatabaseProvider
import com.example.projecto1.data.model.ServiceEntity
import com.example.projecto1.data.model.ServiceModel
import com.example.projecto1.ui.Components.ServiceCard
import com.example.projecto1.ui.Components.ServiceDetailCard
import com.example.projecto1.ui.Components.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ServiceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    var services by remember { mutableStateOf<List<ServiceEntity>>(emptyList()) }

    val serviceDao = db.serviceDao()
    var serviceDetail by remember { mutableStateOf<ServiceEntity?>(null) }
    var sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar("Password Manager", navController, false) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(R.color.purple_500),
                contentColor = Color.Black,
                onClick = {
                    navController.navigate("manage-service/0")
                }) {
                Icon(Icons.Default.Add, contentDescription = "Add icon")
            }
        }
    ) { innerPadding ->

        LaunchedEffect(Unit) {
            services = withContext(Dispatchers.IO) {
                viewModel.getServices(db)
                serviceDao.getAll()
            }
        }

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.black))
                .fillMaxSize(),
            state = listState
        ) {
            Log.d("debuginfo", services.toString())
            items(services) { service ->
                ServiceCard(
                    service.id, service.name, service.username, service.imageURL,
                    onButtonClick = {

//                        viewModel.showService(service.id){ response ->
//                            if(response.isSuccessful){
//                                serviceDetail = response.body()
//
//                            }
//                        }
//                        showBottomSheet = true

                        CoroutineScope(Dispatchers.IO).launch {
                            serviceDetail = serviceDao.show(service.id)
                            println(service.id)
                        }
                        showBottomSheet = true

//                        serviceDetail = serviceDao.show(service.id)
//                        println("Button pressed")
//                        serviceDetail?.let {
//                            println(it.id)
//                            println(it.name)
//                            println(it.username)
//                            println(it.password)
//                        }
//                        showBottomSheet=true

                        //println(serviceDao.show(service.id))
                        //showBottomSheet = true

                    }
                )
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                containerColor = colorResource(id = R.color.teal_200),
                contentColor = Color.White,
                modifier = Modifier.fillMaxHeight(),
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                ServiceDetailCard(
                    id = serviceDetail?.id ?: 0,
                    name = serviceDetail?.name ?: "",
                    username = serviceDetail?.username ?: "",
                    password = serviceDetail?.password ?: "",
                    description = serviceDetail?.description ?: "",
                    imageURL = serviceDetail?.imageURL,
                    onEditClick = {
                        showBottomSheet = false
                        navController.navigate("manage-service/" + serviceDetail?.id)
                    }
                )
            }
        }
    }
}


/*
Column (
    modifier = Modifier
        .fillMaxSize()
){
    Text(text = "This is the HomeScreen")
    Button(onClick = {navController.navigate("menu")}) {
        Text(text = "MenuScreen")
    }
    Button(onClick = {navController.navigate("components")}) {
        Text(text = "ComponentScreen")
    }
}*/