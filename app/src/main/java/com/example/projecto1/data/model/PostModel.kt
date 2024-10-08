package com.example.projecto1.data.model

import androidx.compose.ui.graphics.painter.Painter

data class PostModel(
    val id:Int,
    val title:String,
    val text:String,
    val image: Painter
)

