package com.example.projecto1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.projecto1.R
import com.example.projecto1.data.model.MenuModel
import com.example.projecto1.data.model.PostModel
import com.example.projecto1.ui.Components.PostCard
import com.example.projecto1.ui.Components.PostCardCompact
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ComponentsScreen(navController: NavController) {
    var component by rememberSaveable { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState, // current state of drawer
        //drawer content
        drawerContent = {
            var menuOptions = arrayOf(
                MenuModel(3,"Buttons", "Buttons", Icons.Filled.Add),
                MenuModel(4,"Floating Buttons", "FloatingButtons", Icons.Filled.AccountBox),
                MenuModel(5,"Chips", "Chips", Icons.Filled.ShoppingCart),
                MenuModel(6,"Progress", "Progress", Icons.Filled.Check),
                MenuModel(7,"Sliders", "Sliders", Icons.Filled.Close),
                MenuModel(8,"Switches", "Switches", Icons.Filled.Call),
                MenuModel(9,"Badges", "Badges", Icons.Filled.CheckCircle),
                MenuModel(10,"Time Pickers", "TimePickers", Icons.Filled.Create),
                MenuModel(11,"Date Pickers", "DatePickers", Icons.Filled.ShoppingCart),
                MenuModel(12,"Snack Bar", "SnackBar", Icons.Filled.Home),
                MenuModel(13,"Alert Dialog", "AlertDialog", Icons.Filled.DateRange),
                MenuModel(14,"Bars", "Bars", Icons.Filled.Person),
                MenuModel(15,"Adaptive", "Adaptive", Icons.Filled.Menu)

            )
            ModalDrawerSheet {
                Text(text = "Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider(modifier = Modifier.padding(bottom = 5.dp))
                LazyColumn {
                    items(menuOptions) {item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = "") },
                            label = { Text(item.title) },
                            selected = false,
                            onClick = {
                                component = item.option
                                scope.launch {
                                    drawerState.close()
                                }
                            })
                    }
                }
            }
        }
    ) {
        //Screen Content
        Column {
            when (component) {
                "Buttons" -> {
                    Buttons()
                }

                "FloatingButtons" -> {
                    FloatingButtons()
                }

                "Chips" -> {
                    Chips()
                }

                "Progress" -> {
                    Progress()
                }

                "Sliders" -> {
                    Sliders()
                }

                "Switches" -> {
                    Switches()
                }

                "Badges" -> {
                    Badges()
                }

                "TimePickers" -> {
                    TimePickers()
                }

                "DatePickers" -> {
                    DatePickers()
                }
                "SnackBar" -> {
                    SnackBars()
                }
                "AlertDialog" -> {
                    AlertDialogs()
                }
                "Bars" -> {
                    Bars()
                }
                "Adaptive" ->{
                    Adaptive()
                }
            }
        }
    }

}


@Composable
fun Content1() {
    Column {
        Text(text = "Content 1")
    }
}


@Composable
fun Content2() {
    Column {
        Text(text = "Content 2")
    }
}

@Composable
fun Buttons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {}
        ) {
            Text("Filled")
        }
        FilledTonalButton(onClick = {}) {
            Text("Tonal")
        }
        OutlinedButton(onClick = {}) {
            Text("Outlined")
        }
        ElevatedButton(onClick = {}) {
            Text("Elevated")
        }
        TextButton(onClick = {}) {
            Text("Text")
        }
    }
}

@Composable
fun FloatingButtons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }
        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }
        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }
        ExtendedFloatingActionButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Add, "") },
            text = { Text(text = "Extended FAB") }
        )

    }
}

@Composable
fun Chips() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        AssistChip(
            onClick = { /*TODO*/ },
            label = { Text(text = "Assist Chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.AccountBox,
                    contentDescription = "",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text(text = "Filter Chip") },
            leadingIcon =
            if (selected) {
                {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }

        )
        InputChipExample(text = "Dismiss", {})
    }
}

@Composable
fun InputChipExample(
    text: String,
    onDismiss: () -> Unit
) {
    var enable by remember { mutableStateOf(true) }
    if (!enable) return

    InputChip(
        selected = enable,
        onClick = {
            onDismiss()
            enable = !enable
        },
        label = { Text(text = text) },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Person Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Person Icon",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )

}

//@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
        )
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp)
        )
    }
}

//@Preview(showBackground = true)
@Composable
fun Sliders() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var sliderPosition by remember { mutableStateOf(50f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                steps = 9,
                valueRange = 0f..100f
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = sliderPosition.toString(),
            )
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var checked by remember { mutableStateOf(true) }
        Switch(checked = checked, onCheckedChange = { checked = it })

        var checked2 by remember { mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = { checked2 = it },
            thumbContent = if (checked2) {
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Person Icon",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                }
            } else {
                null
            }

        )

        var checked3 by remember { mutableStateOf(true) }
        Checkbox(checked = checked3, onCheckedChange = { checked3 = it })

    }
}

//@Preview(showBackground = true)
@Composable
fun Badges() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var itemCount by remember { mutableStateOf(0) }

        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(text = "$itemCount")
                    }
                }
            }
        ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Carrito")
        }

        Button(onClick = { itemCount++ }) {
            Text(text = "Add Item")
        }

    }
}

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickers() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        InputExample(onConfirm = { /*TODO*/ }) {}
        DialExample(onConfirm = { /*TODO*/ }) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimeInput(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun DatePickers() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        DatePickerDocked()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

//@Preview(showBackground = true)
@Composable
fun SnackBars(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var snackState = remember { SnackbarHostState() }
        var snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState, Modifier)

        fun launchSnackBar(){
            snackScope.launch { snackState.showSnackbar("The message was sent") }
        }

        Button(::launchSnackBar) {
            Text(text = "Show SnackBar")
        }
//        Button(onClick = { launchSnackBar() }) {
//            Text(text = "Show SnackBar")
//        }

    }
}

//@Preview(showBackground = true)
@Composable
fun AlertDialogs() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }


        if (showAlertDialog){
            AlertDialog(
                icon = { Icon(Icons.Filled.Warning, contentDescription = "") },
                title = { Text(text = "Confirm deletion") },
                text = { Text(text = "Are you sure you want to delete the file?") },
                onDismissRequest = {/*TO DO*/ },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Confirm"
                            showAlertDialog = false
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            selectedOption = "Dismiss"
                            showAlertDialog = false
                        }
                    ) {
                        Text(text = "Dismiss")
                    }
                }
            )
        }

        Text(selectedOption)
        Button(onClick = { showAlertDialog = true }) {
            Text(text = "Show alert dialog")
        }

    }
}

//@Preview(showBackground = true)
@Composable
private fun Bars() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        Row (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp, 50.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(Icons.Filled.Menu, contentDescription = "", tint = Color.White)
            Text(
                text = "App Title",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Icon(Icons.Filled.Settings, contentDescription = "", tint = Color.White)
        }
        var post = arrayOf(
            PostModel(1,"Title 1", "Text 1", painterResource(id = R.drawable.andcat)),
            PostModel(2,"Title 2", "Text 2", painterResource(id = R.drawable.andcat)),
            PostModel(3,"Title 3", "Text 3", painterResource(id = R.drawable.andcat)),
            PostModel(4,"Title 4", "Text 4", painterResource(id = R.drawable.andcat)),
            PostModel(5,"Title 5", "Text 5", painterResource(id = R.drawable.andcat)),
            PostModel(6,"Title 6", "Text 6", painterResource(id = R.drawable.andcat)),
            PostModel(7,"Title 7", "Text 7", painterResource(id = R.drawable.andcat)),
            PostModel(8,"Title 8", "Text 8", painterResource(id = R.drawable.andcat)),
            PostModel(9,"Title 9", "Text 9", painterResource(id = R.drawable.andcat)),
            PostModel(10,"Title 10", "Text 10", painterResource(id = R.drawable.andcat)),
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp, 90.dp, 10.dp, 10.dp)
                .fillMaxSize()
        ) {
            //Posts(arrayPosts = post)
            //PostCard(1,"This is card title", "This is card text", painterResource(id = R.drawable.andcat))
            PostsGrid(arrayPosts = post)
        }
        Row (
            modifier= Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(2.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column {
                IconButton(onClick = {}, modifier=Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(text ="Home", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier=Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(text ="Email", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier=Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Favorite,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(text ="Liked", color = Color.White)
            }
            Column {
                IconButton(onClick = {}, modifier=Modifier.size(30.dp)) {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(text ="Settings", color = Color.White)
            }
        }
    }
}

@Composable
fun Posts(arrayPosts:Array<PostModel>, adaptive:String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        items(arrayPosts){ post->
            when(adaptive){
                "PhoneP" -> {
                    PostCardCompact(id = post.id, title = post.title, text = post.text, image = post.image)
                }
                "PhoneL" -> {
                    PostCard(id = post.id, title = post.title, text = post.text, image = post.image)
                }
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun prueba(){
//    var post = arrayOf(
//        PostModel(1,"Title 1", "Text 1", painterResource(id = R.drawable.andcat)),
//        PostModel(2,"Title 2", "Text 2", painterResource(id = R.drawable.andcat)),
//        PostModel(3,"Title 3", "Text 3", painterResource(id = R.drawable.andcat)),
//        PostModel(4,"Title 4", "Text 4", painterResource(id = R.drawable.andcat)),
//        PostModel(5,"Title 5", "Text 5", painterResource(id = R.drawable.andcat)),
//        PostModel(6,"Title 6", "Text 6", painterResource(id = R.drawable.andcat)),
//        PostModel(7,"Title 7", "Text 7", painterResource(id = R.drawable.andcat)),
//        PostModel(8,"Title 8", "Text 8", painterResource(id = R.drawable.andcat)),
//        PostModel(9,"Title 9", "Text 9", painterResource(id = R.drawable.andcat)),
//        PostModel(10,"Title 10", "Text 10", painterResource(id = R.drawable.andcat)),
//    )
//    Posts(arrayPosts = post, adaptive = "PhoneL")
//}

@Composable
fun PostsGrid(arrayPosts:Array<PostModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxSize()
    ){
        items(arrayPosts){ post->
            PostCard(id = post.id, title = post.title, text = post.text, image = post.image)
        }
    }
}

@Preview(showBackground = true, device = "spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240")
@Composable
fun Adaptive() {
    var WindowSize = currentWindowAdaptiveInfo().windowSizeClass
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    // Compact width < 600dp Phone portrait
    // 600dp <= Medium width < 840dp Tablets portrait
    // Expanded width >= 840dp Tablet landscape

    // Compact height < 480dp Phone Landscape
    // 480 <= Medium height < 900dp tablet Landscape o telephone en portrait
    // Expanded height > 900dp tablet en portrait

    var post = arrayOf(
        PostModel(1,"Title 1", "Text 1", painterResource(id = R.drawable.andcat)),
        PostModel(2,"Title 2", "Text 2", painterResource(id = R.drawable.andcat)),
        PostModel(3,"Title 3", "Text 3", painterResource(id = R.drawable.andcat)),
        PostModel(4,"Title 4", "Text 4", painterResource(id = R.drawable.andcat)),
        PostModel(5,"Title 5", "Text 5", painterResource(id = R.drawable.andcat)),
        PostModel(6,"Title 6", "Text 6", painterResource(id = R.drawable.andcat)),
        PostModel(7,"Title 7", "Text 7", painterResource(id = R.drawable.andcat)),
        PostModel(8,"Title 8", "Text 8", painterResource(id = R.drawable.andcat)),
        PostModel(9,"Title 9", "Text 9", painterResource(id = R.drawable.andcat)),
        PostModel(10,"Title 10", "Text 10", painterResource(id = R.drawable.andcat)),
    )

    if (width == WindowWidthSizeClass.COMPACT){
        Posts(arrayPosts = post, "PhoneP")
    }else if(height == WindowHeightSizeClass.COMPACT) {
        Posts(arrayPosts = post, "PhoneL")
    }else{
        Posts(arrayPosts = post, "PhoneL")
    }

    //Text(text = WindowSize.toString())

}