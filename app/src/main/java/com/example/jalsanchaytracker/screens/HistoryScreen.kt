package com.example.jalsanchaytracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.foundation.lazy.items
import com.example.jalsanchaytracker.data.RainfallRecord
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: AppViewModel
) {

    val historyList = viewModel.rainfallHistory

    val totalSavedMonth = viewModel.totalSavings().toInt()

    val totalEntries = historyList.size

    val avgDaily =
        if (totalEntries > 0)
            totalSavedMonth / totalEntries
        else
            0
    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Rainfall History"
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
                }
            )
        }
    )   { paddingValues ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F9FA))
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {

                        Row(

                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceBetween,

                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column {

                                Text(
                                    text = "Monthly Water Report",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xFF1A1A1A)
                                )

                                Text(
                                    text = "Current Month",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFE0F2F1)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null,
                                    tint = Color(0xFF008080),

                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            ReportStat(
                                label = "Total Saved",
                                value = "$totalSavedMonth L",
                                modifier = Modifier.weight(1f)
                            )

                            ReportStat(
                                label = "Entries",
                                value = "$totalEntries",
                                modifier = Modifier.weight(1f),
                                alignment = Alignment.End
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            ReportStat(
                                label = "Avg / Entry",
                                value = "$avgDaily L",
                                modifier = Modifier.weight(1f)
                            )

                            ReportStat(
                                label = "Status",
                                value = "Active",
                                modifier = Modifier.weight(1f),
                                alignment = Alignment.End,
                                valueColor = Color(0xFF4CAF50)
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "RECENT RECORDS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,

                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            items(historyList) { entry ->

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Row(

                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),

                        verticalAlignment = Alignment.CenterVertically,

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                text = entry.date,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "${entry.rainfallMm} mm",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Surface(
                                    color = Color(0xFFE3F2FD),
                                    shape = RoundedCornerShape(4.dp)
                                ) {

                                    Text(
                                        text = "Rainfall",

                                        color = Color(0xFF2196F3),

                                        fontSize = 8.sp,

                                        fontWeight = FontWeight.Bold,

                                        modifier = Modifier.padding(
                                            horizontal = 6.dp,
                                            vertical = 2.dp
                                        )
                                    )
                                }
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "${entry.litersSaved.toInt()} L",
                                color = Color(0xFF008080),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                            Text(
                                text = "Water Wealth",
                                fontSize = 10.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReportStat(
    label: String,
    value: String,
    modifier: Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    valueColor: Color = Color(0xFF008080)
) {

    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {

        Text(
            text = label.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
    }
}
