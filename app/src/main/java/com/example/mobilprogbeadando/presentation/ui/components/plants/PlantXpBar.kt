package com.example.mobilprogbeadando.presentation.ui.components.plants

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.unit.dp


@Composable
fun PlantXpBar (xp : Int) {

    var level = calculateLevel(xp)
    var nextLevelXp = calculateLevelXp(level + 1)
    var remainingXp = nextLevelXp - xp
    var xpBar = xp.toFloat()/ nextLevelXp
    var remainingBar = remainingXp.toFloat() / nextLevelXp
    Log.i("xpBar",xpBar.toString())
    Log.i("remainiong",remainingBar.toString())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement. SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
    ){
        Text(text = level.toString(), modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(8f).padding(8.dp)
        ) {
            Text(text = "$xp/$nextLevelXp", color = Color(0xFF261736))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                LinearProgressIndicator(
                    progress = xpBar,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(text = "Novice", color = Color(0xFF261736))
        }
        Text(text = (level + 1).toString(), modifier = Modifier.weight(1f))
    }
}

fun calculateLevel(xp : Int) : Int{
    var level = 1
    var levelXp = 30
    while (xp % levelXp != xp){
        Log.i("level", level.toString())
        Log.i("xp", xp.toString())
        Log.i("levelXP", levelXp.toString())
        level++
        levelXp += level * (20 * (level.toFloat() / 100)).toInt()
    }
    return level
}

fun calculateLevelXp(lvl : Int) : Int {
    var level = lvl
    var levelXp = 30
    for (i in 2..level){
        level++
        levelXp += level * (20 * (level.toFloat() / 100)).toInt()
    }
    return levelXp
}