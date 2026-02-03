package com.tictactoe.ui.screens

import android.icu.text.AlphabeticIndex
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AppBg = Color(0xFF77A0FA)
private val CardBg = Color(0xFF151922)
private val TextPrimary = Color(0xFFE8EAED)

private val TurnXColor = Color(0xFFE91E63)
private val TurnDefaultColor = Color(0xFF8BC34A)
private val TurnOColor = Color(0xFF0040FF)

@Composable
fun TicTacToe() {

    val xMoves = remember { mutableStateListOf<Int>() }
    val oMoves = remember { mutableStateListOf<Int>() }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    var gameOver by remember { mutableStateOf(false) }

    fun resetGame() {
        xMoves.clear()
        oMoves.clear()
        currentPlayer="X"
        winner=null
        gameOver=false
    }
    fun checkWinner(moves: List<Int>): Boolean {
        val lines = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3, 6, 9),
            listOf(1, 5, 9),
            listOf(3, 5, 7),
        )
        for (line in lines) {
            if (line.all { moves.contains(it) }) return true
        }
        return false
    }

    fun onCellClick(index: Int){
        if (gameOver) return
        if (index in xMoves||index in oMoves) return
        if (currentPlayer=="X"){
            xMoves.add(index)
            if (checkWinner(xMoves)){
                winner="X"
                gameOver=true
                return
            }else{
                currentPlayer="O"
            }
        }else{
            oMoves.add(index)
            if (checkWinner(oMoves)){
                winner="O"
                gameOver=true
                return
            }else{
                currentPlayer="X"
            }
        }
        if (xMoves.size+oMoves.size==9){
            gameOver=true
        }
    }

    fun symbolFor(index: Int): String {
        return when{
            index in xMoves->"X"
            index in oMoves->"O"
            else->""
        }
    }

    val statusColor=when{
        winner=="X"->TurnXColor
        winner=="O"->TurnOColor
        else->TurnDefaultColor
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBg)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Tic Tac Toe",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = TextPrimary

        )

        Text(
            text = when {
                winner != null->"Winner: $winner"
                gameOver->"Draw!"
                else -> "Turn: $currentPlayer"
            },
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = statusColor
        )

        Column {
            Row {
                Cell(value = symbolFor(1), onClick = {onCellClick(1)})
                Cell(value = symbolFor(2), onClick = {onCellClick(2)})
                Cell(value = symbolFor(3), onClick = {onCellClick(3)})
            }
            Row {
                Cell(value = symbolFor(4), onClick = {onCellClick(4)})
                Cell(value = symbolFor(5), onClick = {onCellClick(5)})
                Cell(value = symbolFor(6), onClick = {onCellClick(6)})
            }
            Row {
                Cell(value = symbolFor(7), onClick = {onCellClick(7)})
                Cell(value = symbolFor(8), onClick = {onCellClick(8)})
                Cell(value = symbolFor(9), onClick = {onCellClick(9)})
            }

        }
        Button(
            onClick = {resetGame()},
            modifier = Modifier
                .padding(top = 8.dp)
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CardBg
            )
        ) {
            Text(
                text = "Reset",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
    }
}