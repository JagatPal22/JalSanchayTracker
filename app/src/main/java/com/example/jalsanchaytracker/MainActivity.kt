package com.example.jalsanchaytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jalsanchaytracker.screens.SplashScreen
import com.example.jalsanchaytracker.ui.theme.JalSanchayTrackerTheme
import com.example.jalsanchaytracker.screens.LoginScreen
import com.example.jalsanchaytracker.screens.SignupScreen
import com.example.jalsanchaytracker.screens.SetupScreen
import com.example.jalsanchaytracker.screens.DashboardScreen
import com.example.jalsanchaytracker.screens.HistoryScreen
import com.example.jalsanchaytracker.screens.RainfallEntryScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.jalsanchaytracker.data.AppDatabase
import com.example.jalsanchaytracker.screens.TipsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            JalSanchayTrackerTheme {

                Surface {

                    val navController = rememberNavController()

                    val database = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "rainfall_db"
                    ).build()

                    val dao = database.rainfallDao()
                    val dataStoreManager = DataStoreManager(applicationContext)

                    val appViewModel: AppViewModel = viewModel(
                        factory = ViewModelFactory(
                            dao,
                            dataStoreManager
                        )
                    )

                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {

                        composable("splash") {
                            SplashScreen(navController)
                        }

                        composable("login") {
                            LoginScreen(navController)
                        }

                        composable("signup") {
                            SignupScreen(navController)
                        }

                        composable("setup") {
                            SetupScreen(navController, appViewModel)
                        }

                        composable("dashboard") {
                            DashboardScreen(navController, appViewModel)
                        }

                        composable("history") {
                            HistoryScreen(navController, appViewModel)
                        }
                        composable("tips") {

                            TipsScreen(
                                navController,
                                appViewModel
                            )
                        }
                        composable("entry") {
                            RainfallEntryScreen(navController, appViewModel)
                        }


                    }
                }
            }
        }
    }
}