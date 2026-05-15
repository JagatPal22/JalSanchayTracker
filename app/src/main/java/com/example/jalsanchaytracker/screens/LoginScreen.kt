package com.example.jalsanchaytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Surface(
            color = Color(0xFFE0F2F1),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.size(80.dp)
        ) {

            Icon(
                imageVector = Icons.Default.WaterDrop,
                contentDescription = "Water Logo",
                tint = Color(0xFF008080),
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Secure your water wealth data",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },

            label = {
                Text("Username")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            visualTransformation = PasswordVisualTransformation(),

            modifier = Modifier.fillMaxWidth(),

            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("setup")
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = MaterialTheme.shapes.medium,

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF008080)
            )
        ) {

            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            onClick = {
                navController.navigate("signup")
            }
        ) {

            Text(
                text = "Don't have an account? Create Account",
                color = Color(0xFF008080)
            )
        }
    }
}
