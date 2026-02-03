package com.tictactoe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val CellBackgroundDefault = Color(0xFFE8EAED)
private val TextSecondary = Color(0xFF673AB7)
private val CellBackgroundO  = Color(0xFF673AB7)

private val CellBackgroundX = Color(0xFFFFB800)
private val CellTextX = Color(0xFF9C27B0)
private val CellTextO = Color(0xFF00BCD4)
private val CellTextDefault = Color(0xFFCDDC39)

@Composable
fun Cell (
    value: String,
    onClick: () -> Unit){
    val backgroundColor= when(value){
        "X" -> CellBackgroundX
    "O" -> CellBackgroundO
    else -> CellBackgroundDefault}

    val textColor=when(value){
        "X" -> CellTextX
    "0" -> CellTextO
    else -> CellTextDefault}
    Box(
        modifier = Modifier
            .size(90.dp)
            .padding(5.dp)
            .shadow(6.dp, RoundedCornerShape(20.dp))
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .clickable{onClick()},
        contentAlignment = Alignment.Center
    ){
        Text(
            text = value,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color= textColor

        )
    }
}