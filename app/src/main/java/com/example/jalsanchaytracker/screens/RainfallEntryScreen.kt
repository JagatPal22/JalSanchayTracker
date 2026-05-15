package com.example.jalsanchaytracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jalsanchaytracker.AppViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RainfallEntryScreen(
    navController: NavController,
    viewModel: AppViewModel
) {

    var rainfall by remember {
        mutableStateOf("")
    }
    val liters = rainfall.toFloatOrNull()?.let {
        viewModel.calculateLiters(it)
    } ?: 0f

    Scaffold(

        containerColor = Color(0xFFF8F9FA),

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Add Rainfall",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8F9FA)
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {

            Text(
                text = "Rainfall Depth (mm)",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(

                value = rainfall,

                onValueChange = {
                    rainfall = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text("0.0")
                },

                suffix = {
                    Text("mm")
                },

                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Color(0xFF008080),

                    unfocusedBorderColor = Color.LightGray,

                    focusedContainerColor = Color.White,

                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "ESTIMATED IMPACT",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${liters.toInt()} Liters",
                        color = Color(0xFF008080),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Calculated based on roof area.",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(

                onClick = {

                    val mm = rainfall.toFloatOrNull() ?: 0f

                    viewModel.addRainfall(mm)

                    navController.popBackStack()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF008080)
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Save Record",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}