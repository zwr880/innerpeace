package com.example.compose.navigationbar.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.navigationbar.screen.AddScreen
import com.example.compose.navigationbar.screen.MBTIScreen
import com.example.compose.navigationbar.screen.MessageScreen
import com.example.compose.navigationbar.screen.MineScreen
import com.example.compose.navigationbar.screen.OceanScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController ,
        startDestination = BottomBarScreen.Add.route
    ){
        composable(route=BottomBarScreen.Add.route){
            AddScreen()
        }
        composable(route=BottomBarScreen.Ocean.route){
           OceanScreen()
        }
        composable(route=BottomBarScreen.Message.route){
           MessageScreen()
        }
        composable(route=BottomBarScreen.Test.route){
            MBTIScreen()
        }
        composable(route=BottomBarScreen.Mine.route){
            MineScreen()
        }

    }

}