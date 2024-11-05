package com.example.projecto1.ui.screens

import android.Manifest
import android.content.Context
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun CalendarAndContactsScreen() {

    var hasPermission by remember { mutableStateOf(false) }

    val permisssionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasPermission = permissions[Manifest.permission.READ_CONTACTS] == true &&
                permissions[Manifest.permission.READ_CALENDAR] == true &&
                permissions[Manifest.permission.WRITE_CALENDAR] == true

    }

    //Efecto lanzado para solicitar permisos cuando se carga la antalla
    LaunchedEffect(Unit) {
        permisssionLauncher.launch(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
            )
        )
    }


    if (hasPermission){
        AgendaScreen()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Se necesitan permisos para acceder a los contactos u a el calendario")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                permisssionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR
                    )
                )
            }) {
                Text(text = "Solicitar Permisos")
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(){
    var selectedContact by remember {
        mutableStateOf("Selecciona un contacto")
    }
    var selectedDate by remember {
        mutableStateOf("Selecciona una fecha")
    }
    var selectedStartTime by remember {
        mutableStateOf("Selecciona una hora de inicio")
    }
    var SelectedEndTime by remember {
        mutableStateOf("Selecciona una hora de fun")
    }

    val context = LocalContext.current
    var showContractDialog by remember {
        mutableStateOf(false)
    }

    val contacts = remember {
        fetchContacts(context)
    }


}


fun fetchContacts(context:Context) {

}