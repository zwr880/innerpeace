package com.example.compose.navigationbar.bottomnav

import com.example.compose.R

sealed class BottomBarScreen (
    val route:String,
    val title:String,
    val icon:Int,
    val icon_focused:Int
){
    //for ocean
    object Ocean:BottomBarScreen(
        route = "ocean",
        title = "Ocean",
        icon = R.drawable.ocean,
        icon_focused = R.drawable.ocean_focused
    )

    //for message
    object Message:BottomBarScreen(
        route = "message",
        title = "Friendship",
        icon = R.drawable.message,
        icon_focused = R.drawable.message_focused
    )

    //for add
    object Add:BottomBarScreen(
        route = "add",
        title = "Record",
        icon = R.drawable.add,
        icon_focused = R.drawable.add_focused
    )

    //for test
    object Test:BottomBarScreen(
        route = "test",
        title = "MB-TI",
        icon = R.drawable.test,
        icon_focused = R.drawable.test_focused
    )

    //for mine
    object Mine:BottomBarScreen(
        route = "mine",
        title = "Mine",
        icon = R.drawable.mine,
        icon_focused = R.drawable.mine_focuse
    )


}