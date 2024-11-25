package com.example.projecto1

import android.media.Image
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.projecto1.ui.theme.Projecto1Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.projecto1.ui.maps.viewModel.SearchViewModel
import com.example.projecto1.ui.maps.views.HomeView
import com.example.projecto1.ui.maps.views.MapsSearchView
import com.example.projecto1.ui.network.NetworkMonitor
import com.example.projecto1.ui.background.CustomWorker
import com.example.projecto1.ui.screens.BiometricsScreen
import com.example.projecto1.ui.screens.CalendarAndContactsScreen
import com.example.projecto1.ui.screens.CameraScreen
import com.example.projecto1.ui.screens.ComponentsScreen
import com.example.projecto1.ui.screens.HomeScreen
import com.example.projecto1.ui.screens.LoginScreen
import com.example.projecto1.ui.screens.ManageServiceScreen
import com.example.projecto1.ui.screens.MenuScreen
import java.time.Duration


class MainActivity : AppCompatActivity() {


    //--------------------------------------------
    //Internet
    // Inicializamos los objetos que vamos a usar para el monitoreo de la red
    private lateinit var wifiManager: WifiManager  // Para gestionar el Wi-Fi
    private lateinit var connectivityManager: ConnectivityManager  // Para gestionar las conexiones de red
    private lateinit var networkMonitor: NetworkMonitor  // Clase que monitorea el estado de la red
    //--------------------------------------------


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Colors also baterry and stuff bar

        //WorkManager
        //------------------------------------------
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        //By adding this, message "Hello from worker!" should be seen from LogCat

        //--------------------------------------------


        //-------------------------------------------
        //Internet
        // Obtenemos los servicios necesarios para controlar Wi-Fi y la conectividad de red
        wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Creamos una instancia de NetworkMonitor, pasando los servicios y la actividad actual
        networkMonitor = NetworkMonitor(wifiManager, connectivityManager, this)


        //---------------------------------------------


        enableEdgeToEdge()
        setContent {
            val viewModel: SearchViewModel by viewModels()
            ComposeMultiScreenApp(this, viewModel, networkMonitor)


//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
//
//            ) {
////                Text(text = "Hola Android!")
////                ModifierExample()
////                ModifierExample2()
////                ModifierExample3()
//                CustomText()
//                Picture()
//                Content1()
//            }


            //layouts
//            Column {
//                Text(text = "First Row")
//                Text(text = "Second Row")
//                Text(text = "Third Row")
//                Row {
//                    Text(text = "Text 1")
//                    Text(text = "Text 2")
//                    Text(text = "Text 3")
//                    Text(text = "Text 4")
//                    Text(text = "Text 5")
//                    Text(text = "Text 6")
//                }
//                Box{
//                    Text(text = "BoxText 1")
//                    Text(text = "BoxText 2")
//                }
//                Greeting(name = "World")
//            }
//test

//            Projecto1Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
*/
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Projecto1Theme {
//        Greeting("Sebastián")
//    }
//}

//@Preview(showBackground = true)
@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .clickable(onClick = { clickAction() })
    ) {
        Text(text = "Hola Android!")
    }
}

//@Preview(showBackground = true)
@Composable
fun ModifierExample2() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction() })
    ) {
        Text(text = "Hola Android!")
    }
}

//@Preview(showBackground = true)
@Composable
fun ModifierExample3() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(onClick = { clickAction() })
            .padding(16.dp)
            .background(Color.Cyan)
            .border(width = 2.dp, Color.Green)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
        Text(text = "Item 6")
    }
}

//@Preview(showBackground = true)
@Composable
fun CustomText() {
    Column {
        Text(
            stringResource(R.string.hello_world_text),
            color = colorResource(R.color.purple_200),
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Cyan, Blue)
        Text(
            stringResource(R.string.hello_world_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Picture() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.andcat),
            contentDescription = "Logo Andorid",
            contentScale = ContentScale.Crop
        )
    }
}
/*
@Preview(showBackground = true)
@Composable
fun Content1() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = "This is a title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.andcat),
            contentDescription = "ANDCAT LOGO",
            contentScale = ContentScale.Crop
        )
        Text(
            stringResource(id = R.string.text_card),
            textAlign = TextAlign.Justify,
            lineHeight = 18.sp,
            modifier = Modifier
                .padding(10.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun Content2() {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row {
            Column (
                verticalArrangement = Arrangement.Center
                //modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(0.dp, 10.dp),

                    painter = painterResource(id = R.drawable.andcat),
                    contentDescription = "ANDCAT LOGO",
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                Text(
                    text = "This is a title",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    stringResource(id = R.string.text_card),
                    textAlign = TextAlign.Justify,
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    maxLines = 10,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample1(){
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.andcat),
            contentDescription = "ANDCAT",
            contentScale = ContentScale.FillBounds
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "Acount Box"
            )
            Text(
                text = "Text",

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample2(){
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .padding(5.dp)
            .size(275.dp)
    ){
        Text(
            text = "TopStart",
            Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "TopCenter",
            Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "TopEnd",
            Modifier.align(Alignment.TopEnd)
        )
        Text(
            text = "CenterStart",
            Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "Center",
            Modifier.align(Alignment.Center)
        )
        Text(
            text = "CenterEnd",
            Modifier.align(Alignment.CenterEnd)
        )
        Text(
            text = "BottomStart",
            Modifier.align(Alignment.BottomStart)
        )
        Text(
            text = "BottomCenter",
            Modifier.align(Alignment.BottomCenter)
        )
        Text(
            text = "BottomEnd",
            Modifier.align(Alignment.BottomEnd)
        )
    }
}
*/
//git test2

fun clickAction(element: String = "Element") {
    println("$element Clicked")
}


//@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(
    activity: AppCompatActivity,
    viewModel: SearchViewModel,
    networkMonitor: NetworkMonitor
) {
    val navController = rememberNavController()
    Surface(color = Color.White) {
        SetupNavGraph(navController = navController, activity, viewModel, networkMonitor)
    }
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    activity: AppCompatActivity,
    viewModel: SearchViewModel,
    networkMonitor: NetworkMonitor
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "login") {
        composable("menu") { MenuScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("components") { ComponentsScreen(navController) }
        composable("homeMaps") { HomeView(navController = navController, searchVM = viewModel) }
        composable("network") { networkMonitor.NetworkMonitorScreen(navController = navController) }
        composable(
            "MapsSearchView/{lat}/{long}/{address}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("long") { type = NavType.FloatType },
                navArgument("address") { type = NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapsSearchView(lat.toDouble(), long.toDouble(), address)
        }
        composable("CalendarContacts") { CalendarAndContactsScreen() }
        composable("Biometrics") { BiometricsScreen(activity) }
        composable("Camera") { CameraScreen(context = context) }
        composable("login") { LoginScreen(navController = navController) }
        composable("manage-service/{serviceId}"){backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId")
            ManageServiceScreen(navController, serviceId = serviceId)
        }
    }
}