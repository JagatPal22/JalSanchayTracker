package com.example.jalsanchaytracker.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun DashboardScreen(
    navController: NavController,
    viewModel: AppViewModel
    ) {

    Scaffold(

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("entry")
                },

                containerColor = Color(0xFF008080),
                contentColor = Color.White
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Rainfall"
                )
            }
        },

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(

                    selected = true,

                    onClick = {

                        navController.navigate("dashboard")
                    },

                    icon = {

                        Icon(
                            Icons.Default.Home,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Home")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate("history")
                    },

                    icon = {

                        Icon(
                            Icons.Default.History,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("History")
                    }
                )

                NavigationBarItem(

                    selected = false,

                    onClick = {

                        navController.navigate("tips")
                    },

                    icon = {

                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null
                        )
                    },

                    label = {

                        Text("Tips")
                    }
                )
            }
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            item {

                Text(
                    text = "Welcome back,",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Text(
                    text = "User Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    StatCard(
                        label = "Saved Today",
                        value =  "${viewModel.savedToday().toInt()} L",
                        modifier = Modifier.weight(1f),
                        bgColor = Color(0xFF008080),
                        textColor = Color.White
                    )

                    StatCard(
                        label = "Total Savings",
                        value = "${viewModel.totalSavings().toInt()} L",
                        modifier = Modifier.weight(1f),
                        bgColor = Color.White,
                        textColor = Color(0xFF333333)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF004D40)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {

                        Text(
                            text = "HOUSEHOLD WATER DAYS",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${viewModel.waterDays()} Days",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Your harvested rain can sustain your home for 36 full days.",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                WaterTankVisual(level = viewModel.tankLevel())

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun StatCard(
    label: String,
    value: String,
    modifier: Modifier,
    bgColor: Color,
    textColor: Color
) {

    Card(
        modifier = modifier,

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = bgColor
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = label,
                color = textColor.copy(alpha = 0.7f),
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WaterTankVisual(level: Float) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Water Tank",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Surface(
                    color = Color(0xFFE0F2F1),
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Text(
                        text = "${(level * 100).toInt()}% Full",

                        color = Color(0xFF008080),

                        fontSize = 12.sp,

                        fontWeight = FontWeight.Bold,

                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Color(0xFFF5F5F5),
                        RoundedCornerShape(16.dp)
                    ),

                contentAlignment = Alignment.BottomCenter
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(level)
                        .background(
                            Color(0xFF2196F3),
                            RoundedCornerShape(
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                )
            }
        }
    }
}