package com.example.jalsanchaytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jalsanchaytracker.AppViewModel

@Composable
fun SetupScreen (
    navController: NavController,
    viewModel: AppViewModel
    ) {

    var roofArea by remember {
        mutableStateOf("")
    }

    var tankCapacity by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Basic Setup",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We need a few details to calculate your savings.",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = roofArea,
            onValueChange = {
                roofArea = it
            },

            label = {
                Text("Roof Area (sq ft)")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = tankCapacity,
            onValueChange = {
                tankCapacity = it
            },

            label = {
                Text("Tank Capacity (Liters)")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                viewModel.saveRoofArea(
                    roofArea.toFloatOrNull() ?: 0f
                )

                viewModel.saveTankCapacity(
                    tankCapacity.toFloatOrNull() ?: 0f
                )

                navController.navigate("dashboard")
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF008080)
            )
        ) {

            Text(
                text = "Continue",
                color = Color.White
            )
        }
    }
}