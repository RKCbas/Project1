package com.example.projecto1.ui.screens

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

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


    if (hasPermission) {
        AgendaScreen()
    } else {
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
fun AgendaScreen() {
    var selectedContact by remember {
        mutableStateOf("Selecciona un contacto")
    }
    var selectedDate by remember {
        mutableStateOf("Selecciona una fecha")
    }
    var selectedStartTime by remember {
        mutableStateOf("Selecciona una hora de inicio")
    }
    var selectedEndTime by remember {
        mutableStateOf("Selecciona una hora de fun")
    }

    // Obtiene el contexto actual y crea una variable para mostrar el diálogo de selección de contacto
    val context = LocalContext.current
    var showContactDialog by remember { mutableStateOf(false) }

    // Obtiene la lista de contactos
    val contacts = remember { fetchContacts(context) }

    // Columna principal de la interfaz
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        // Botón que muestra el contacto seleccionado
        Button(onClick = { showContactDialog = true }) {
            Text(text = selectedContact)
        }

        // Diálogo de selección de contactos
        if (showContactDialog) {
            AlertDialog(
                onDismissRequest = { showContactDialog = false },
                title = { Text("Selecciona un contacto") },
                text = {
                    LazyColumn {
                        items(contacts) { contact ->
                            TextButton(onClick = {
                                // Al seleccionar un contacto, se actualiza el estado y se cierra el diálogo
                                selectedContact = contact
                                showContactDialog = false
                            }) {
                                Text(text = contact)
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showContactDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para seleccionar la fecha
        Button(onClick = {
            val currentDate = Calendar.getInstance()
            val datePickerDialog = android.app.DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    selectedDate = "$dayOfMonth/${month + 1}/$year"
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }) {
            Text(text = selectedDate)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para seleccionar la hora de inicio
        Button(onClick = {
            val currentTime = Calendar.getInstance()
            val timePickerDialog = android.app.TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedStartTime = String.format("%02d:%02d", hourOfDay, minute)
                },
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true // Formato de 24 horas
            )
            timePickerDialog.show()
        }) {
            Text(text = selectedStartTime)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para seleccionar la hora de fin
        Button(onClick = {
            val currentTime = Calendar.getInstance()
            val timePickerDialog = android.app.TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedEndTime = String.format("%02d:%02d", hourOfDay, minute)
                },
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }) {
            Text(text = selectedEndTime)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar el evento en el calendario
        Button(onClick = {
            if (selectedContact != "Selecciona un contacto" &&
                selectedDate != "Selecciona una fecha" &&
                selectedStartTime != "Selecciona hora de inicio" &&
                selectedEndTime != "Selecciona hora de fin"
            ) {

                // Convierte la fecha y la hora en milisegundos
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateInMillis = dateFormat.parse(selectedDate)?.time

                if (dateInMillis != null) {
                    val (startHour, startMinute) = selectedStartTime.split(":").map { it.toInt() }
                    val (endHour, endMinute) = selectedEndTime.split(":").map { it.toInt() }

                    val startTimeInMillis = Calendar.getInstance().apply {
                        timeInMillis = dateInMillis
                        set(Calendar.HOUR_OF_DAY, startHour)
                        set(Calendar.MINUTE, startMinute)
                    }.timeInMillis

                    val endTimeInMillis = Calendar.getInstance().apply {
                        timeInMillis = dateInMillis
                        set(Calendar.HOUR_OF_DAY, endHour)
                        set(Calendar.MINUTE, endMinute)
                    }.timeInMillis

                    // Obtiene el ID del calendario y guarda el evento
                    val calendarId = getCalendarId(context)
                    if (calendarId != null) {
                        saveEventToCalendar(
                            context,
                            selectedContact,
                            startTimeInMillis,
                            endTimeInMillis,
                            calendarId
                        )
                    } else {
                        Toast.makeText(
                            context,
                            "No se encontró un calendario local.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Error al convertir la fecha. Por favor, intenta de nuevo.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "Por favor, selecciona un contacto, una fecha y las horas.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Guardar Evento")
        }
    }
}

// Función para obtener los contactos del teléfono
fun fetchContacts(context: Context): List<String> {
    val contacts = mutableListOf<String>()

    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        null
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        while (it.moveToNext()) {
            val name = it.getString(nameIndex)
            contacts.add(name)
        }
    }

    return contacts
}

// Función para obtener el ID de un calendario local
fun getCalendarId(context: Context): Long? {
    val projection = arrayOf(
        CalendarContract.Calendars._ID,
        CalendarContract.Calendars.ACCOUNT_TYPE
    )

    val cursor = context.contentResolver.query(
        CalendarContract.Calendars.CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val idIndex = it.getColumnIndex(CalendarContract.Calendars._ID)
        val accountTypeIndex = it.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE)

        while (it.moveToNext()) {
            val accountType = if (accountTypeIndex != -1) {
                it.getString(accountTypeIndex)
            } else {
                null
            }
            if (accountType == CalendarContract.ACCOUNT_TYPE_LOCAL) {
                return if (idIndex != -1) {
                    it.getLong(idIndex)
                } else {
                    null
                }
            }
        }
    }
    Log.w("CalendarDebug", "No se encontró un calendario local.")
    return null
}

// Función para guardar el evento en el calendario
fun saveEventToCalendar(
    context: Context,
    contact: String,
    startTimeInMillis: Long,
    endTimeInMillis: Long,
    calendarId: Long
) {
    val contentValues = ContentValues().apply {
        put(CalendarContract.Events.TITLE, "Evento con $contact")
        put(CalendarContract.Events.DTSTART, startTimeInMillis)
        put(CalendarContract.Events.DTEND, endTimeInMillis)
        put(CalendarContract.Events.CALENDAR_ID, calendarId)
        put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
    }

    try {
        val uri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues)
        if (uri != null) {
            Toast.makeText(context, "Evento guardado: $uri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error al guardar el evento", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}