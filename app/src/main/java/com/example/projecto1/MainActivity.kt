package com.example.projecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projecto1.ui.theme.Projecto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            //layouts
            Column {
                Text(text = "First Row")
                Text(text = "Second Row")
                Text(text = "Third Row")
                Row {
                    Text(text = "Text 1")
                    Text(text = "Text 2")
                    Text(text = "Text 3")
                    Text(text = "Text 4")
                    Text(text = "Text 5")
                    Text(text = "Text 6")
                }
                Box{
                    Text(text = "BoxText 1")
                    Text(text = "BoxText 2")
                }
                Greeting(name = "World")
            }


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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projecto1Theme {
        Greeting("Sebastián")
    }
}