package com.peterchege.compositionlocaldemo.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.peterchege.compositionlocaldemo.R
import com.peterchege.compositionlocaldemo.core.BottomNavItem
import com.peterchege.compositionlocaldemo.core.Screens
import com.peterchege.compositionlocaldemo.ui.screens.HomeScreen
import com.peterchege.compositionlocaldemo.ui.screens.SettingsScreen


@ExperimentalMaterial3Api
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.home),
                        route = Screens.HOME_SCREEN,
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.settings),
                        route = Screens.SETTINGS_SCREEN,
                        selectedIcon = Icons.Default.Settings,
                        unselectedIcon = Icons.Outlined.Settings
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            BottomNavigation(
                navController = navController,

            )
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,

    ) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onBackground,
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(text = item.name)
                },
                selected = isSelected,
                onClick = { onItemClick(item) }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun BottomNavigation(
    navController: NavHostController,
) {
    NavHost(

        navController = navController,
        startDestination = Screens.HOME_SCREEN
    ) {
        composable(
            route = Screens.HOME_SCREEN
        ) {
            HomeScreen()
        }
        composable(
            route = Screens.SETTINGS_SCREEN,
        ) {
            SettingsScreen()
        }


    }

}