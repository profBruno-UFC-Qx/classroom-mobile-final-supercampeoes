package com.example.superchampions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superchampions.model.Athlete
import com.example.superchampions.ui.theme.CorDourado
import com.example.superchampions.ui.theme.CorPrimaria
import com.example.superchampions.ui.theme.CorSecundaria

@Composable
fun RankingPersonCard(position: Int, athlete: Athlete, modifier: Modifier = Modifier) {
    val first = position == 1

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (first) CorPrimaria else Color.Transparent)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$position°",
                color = if (first) CorDourado else Color.White.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.width(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CorSecundaria),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = athlete.name.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = athlete.name,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        if (first) {
            Icon(
                imageVector = Icons.Filled.Stars,
                contentDescription = null,
                tint = CorDourado,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = "${athlete.points} pts",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp
            )
        }
    }
}
