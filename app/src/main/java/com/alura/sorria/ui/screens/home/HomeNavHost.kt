package com.alura.sorria.ui.screens.home


import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alura.sorria.ui.camera.AnalyzeScreen
import com.alura.sorria.ui.components.HomeAppBar
import com.alura.sorria.ui.components.HomeBottomBar
import com.alura.sorria.ui.components.HomeCustomSnackBar
import com.alura.sorria.ui.components.screenItems
import com.alura.sorria.ui.screens.feed.ListPosts
import com.alura.sorria.ui.screens.profile.ProfileScreen
import com.alura.sorria.ui.screens.search.SearchScreen
import com.alura.sorria.ui.screens.video.VideosScreen
import com.alura.sorria.ui.theme.SorriaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val state by homeViewModel.uiState.collectAsState()

    LaunchedEffect(state.navigateTo) {
        state.navigateTo?.let {
            val index =
                screenItems.indexOfFirst { item -> item.first.lowercase() == it.lowercase() }

            homeViewModel.setCurrentIndex(index)
            navController.navigateDirect(it)
        }
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            HomeCustomSnackBar(state)
        },
        topBar = {
            Crossfade(targetState = state.navigateTo, label = "showAppBar") { route ->
                if (route != "Vídeos") {
                    HomeAppBar(scrollBehavior) {
                        homeViewModel.toggleShowCameraPreview()
                    }
                }
            }
        },
        bottomBar = {
            HomeBottomBar(
                externalIndex = state.currentRouteIndex,
                onItemSelected = { route ->
                    homeViewModel.setCurrentRoute(route)
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = screenItems[0].first,
                modifier = modifier,
                enterTransition = { fadeIn(tween(200)) },
                exitTransition = { fadeOut(tween(200)) },
            ) {
                composable(screenItems[0].first) {
                    ListPosts(
                        posts = state.posts,
                        pulseBigHeart = state.pulseBigHeart,
                        onPulseBigHeart = { homeViewModel.pulseBigHeart(it) },
                    )
                }

                composable(screenItems[1].first) {
                    SearchScreen()
                }

                composable(screenItems[2].first) {
                    VideosScreen()
                }

                composable(screenItems[3].first) {
                    ProfileScreen()
                }
            }

            Box(
                modifier = Modifier
                    .alpha(if (state.showCameraPreview) 1f else 0f)
            ) {
                AnalyzeScreen(
                    onSmiledDetected = {
                        homeViewModel.pulseBigHeart(true)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SorriaTheme {
        HomeNavHost()
    }
}

fun NavHostController.navigateDirect(rota: String) = this.navigate(rota) {
    popUpTo(this@navigateDirect.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
