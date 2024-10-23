package com.example.projecto1.ui.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecto1.R

@Composable
fun PostCard(id:Int, title:String, text:String, image:Painter) {
    Card(
        //onClick = { /*TO DO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White

        )
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(100.dp),
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun PostCardCompact(id:Int, title:String, text:String, image:Painter) {
    Card(
        //onClick = { /*TO DO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White

        )
    ) {
        Row {
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp)
                    .padding(5.dp),
                painter = image,
                contentDescription = "AndCat",
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = text,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = 3,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}