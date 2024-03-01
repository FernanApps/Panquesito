package the.beginning.of.the.end

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import the.beginning.of.the.end.presentation.common.CommonAction
import the.beginning.of.the.end.presentation.common.GoBackAction
import the.beginning.of.the.end.presentation.screens.Screens
import the.beginning.of.the.end.presentation.screens.home.HomeAction
import the.beginning.of.the.end.presentation.screens.home.HomeScreen
import the.beginning.of.the.end.presentation.screens.prequel.PrequelScreen
import the.beginning.of.the.end.presentation.screens.splash.SplashScreen
import the.beginning.of.the.end.theme.AppTheme
import the.beginning.of.the.end.theme.LocalThemeIsDark

@Composable
internal fun App() = AppTheme {

    PreComposeApp {
        val navigator = rememberNavigator()
        NavHost(
            // Assign the navigator to the NavHost
            navigator = navigator,
            // Navigation transition for the scenes in this NavHost, this is optional
            navTransition = NavTransition(),
            // The start destination
            initialRoute = Screens.Splash(),
        ) {
            // Define a scene to the navigation graph

            val goBack = object : GoBackAction {
                override fun goBack() {
                    navigator.popBackStack()
                }
            }

            val splashNavigator = object : CommonAction, GoBackAction by goBack {
                override fun navigate() {
                    navigator.navigateAndDeleteOldScreen(Screens.Home(), Screens.Splash())
                }
            }
            val homeNavigator = object : HomeAction, GoBackAction by goBack {
                override fun toPrequel() {
                    navigator.navigate(Screens.Prequel())
                }

            }


            scene(
                route = Screens.Splash(),
                navTransition = NavTransition(),
            ) {
                SplashScreen(splashNavigator)
            }

            scene(
                route = Screens.Home(),
                navTransition = NavTransition(),
            ) {
                HomeScreen(homeNavigator)
            }

            scene(
                route = Screens.Prequel(),
                navTransition = NavTransition(),
            ) {
                PrequelScreen(goBack)
            }


        }
    }

}


fun Navigator.navigateAndDeleteOldScreen(route: String, oldRoute: String) {
    this.navigate(
        route, options = NavOptions(
            popUpTo = PopUpTo(
                // The destination of popUpTo
                route = oldRoute,
                // Whether the popUpTo destination should be popped from the back stack.
                inclusive = true,
            )
        )
    )
}



internal expect fun openUrl(url: String?)
