package com.jose.gigimovies.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jose.gigimovies.R
import com.jose.gigimovies.ui.favourites.FavouriteList
import com.jose.gigimovies.ui.popular.MovieList

@Composable
fun MainView(){
  val navController = rememberNavController()
  Scaffold (
    bottomBar = {
      BottomBar(
        navController
      )
    }
  )
  {
    BottomBarMain(navController)
    //MovieList()
  }
}

@Composable
fun BottomBarMain(navController : NavHostController) {
  NavHost(navController, startDestination = Screen.Popular.route) {
    composable(Screen.Popular.route) {
      MovieList()
    }
    composable(Screen.Favourite.route) {
      FavouriteList()
    }
  }
}

@Composable
fun BottomBar(navController: NavController){

  val items = listOf(
    Screen.Popular,
    Screen.Favourite
  )

  BottomNavigation(
    backgroundColor = Color.White,
    contentColor = colorResource(id = R.color.colorPrimary),
    elevation = 5.dp
  ){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.map {
      BottomNavigationItem(
        icon= {
          Icon(
            painter = painterResource(id = it.icon),
            contentDescription = it.title
          )
        },
        label= {
          Text(
            text = it.title
          )
        },
        selected = currentRoute == it.route,
        selectedContentColor= colorResource(id = R.color.colorPrimary),
        unselectedContentColor= colorResource(id = R.color.colorPrimary).copy(alpha = 0.4f),
        onClick = {
          navController.navigate(it.route)
        }
      )
    }

  }
}