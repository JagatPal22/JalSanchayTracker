package com.example.jalsanchaytracker.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(true) {

        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        delay(2000)

        navController.navigate("login") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F2F1)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.WaterDrop,
                contentDescription = "Water Logo",
                modifier = Modifier.size(120.dp),
                tint = Color(0xFF008080)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Jal-Sanchay Tracker",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Track Your Water Wealth",
                fontSize = 16.sp,
                color = Color(0xFF008080)
            )
        }
    }
}