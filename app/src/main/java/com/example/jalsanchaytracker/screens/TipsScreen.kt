package com.example.jalsanchaytracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jalsanchaytracker.AppViewModel
import androidx.compose.foundation.layout.navigationBarsPadding
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(
    navController: NavController,
    viewModel: AppViewModel
) {

    val tips = listOf(

        Tip(
            title = "What is Rainwater Harvesting?",
            description = "Rainwater harvesting is the process of collecting and storing rainwater from rooftops or other surfaces for future use."
        ),

        Tip(
            title = "Why is Rainwater Harvesting Important?",
            description = "It helps conserve water, reduce groundwater depletion, lower water bills, and provide water during shortages."
        ),

        Tip(
            title = "What is a Catchment Area?",
            description = "Catchment area is the surface area, usually a rooftop, used to collect rainwater."
        ),

        Tip(
            title = "How is Rainwater Calculated?",
            description = "Harvested water depends on roof area, rainfall amount, and runoff efficiency."
        ),

        Tip(
            title = "What is Runoff Coefficient?",
            description = "Runoff coefficient indicates how much rainwater can actually be collected after losses."
        ),

        Tip(
            title = "How to Improve Rainwater Collection?",
            description = "Clean rooftops regularly, use proper gutters, install filters, and maintain storage tanks properly."
        ),

        Tip(
            title = "Can Rainwater be Used for Drinking?",
            description = "Yes, but proper filtration and purification are necessary before drinking."
        ),

        Tip(
            title = "Benefits of Rainwater Harvesting",
            description = "It reduces water wastage, supports groundwater recharge, lowers water bills, and promotes sustainable living."
        ),

        Tip(
            title = "Rooftop Rainwater Harvesting",
            description = "Rainwater collected from rooftops is directed through pipes into storage tanks for later use."
        ),

        Tip(
            title = "Recharge Pit Method",
            description = "Recharge pits allow rainwater to seep into the ground and help increase groundwater levels."
        ),

        Tip(
            title = "Percolation Tank Method",
            description = "Percolation tanks store rainwater temporarily and allow gradual infiltration into the soil."
        ),

        Tip(
            title = "Groundwater Recharge Wells",
            description = "Recharge wells transfer collected rainwater directly into underground aquifers."
        ),

        Tip(
            title = "Surface Runoff Harvesting",
            description = "Rainwater flowing on roads or open surfaces is collected and stored for future use."
        ),

        Tip(
            title = "Rain Barrels",
            description = "Rain barrels are small storage containers connected to rooftop gutters to collect rainwater."
        ),

        Tip(
            title = "Maintenance of Harvesting System",
            description = "Regular cleaning of filters, pipes, and storage tanks is important for efficient rainwater harvesting."
        )
    )

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Water Conservation Guide",
                        fontWeight = FontWeight.Bold
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

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .navigationBarsPadding(),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
            {

            item {

                Text(
                    text = "Rainwater Harvesting Knowledge Base",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Learn methods, benefits, and conservation techniques.",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(tips) { tip ->

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
                            text = tip.title,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF008080)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = tip.description,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(24.dp))

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Your Current Stats",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF008080)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Total Savings: ${viewModel.totalSavings().toInt()} L"
                        )

                        Text(
                            text = "Water Days: ${viewModel.waterDays()}"
                        )

                        Text(
                            text = "Tank Fill: ${(viewModel.tankLevel() * 100).toInt()}%"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

data class Tip(
    val title: String,
    val description: String
)