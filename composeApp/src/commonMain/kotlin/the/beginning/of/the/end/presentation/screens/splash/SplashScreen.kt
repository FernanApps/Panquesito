package the.beginning.of.the.end.presentation.screens.splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import the.beginning.of.the.end.presentation.common.CommonAction
import the.beginning.of.the.end.theme.Colors
import the.beginning.of.the.end.theme.Strings
import the.beginning.of.the.end.theme.loadDrawableResource
import the.beginning.of.the.end.utils.TimeExt.minus
import the.beginning.of.the.end.utils.TimeExt.now
import the.beginning.of.the.end.utils.TimeExt.plus

data class TimeReaming(val days: String, val hours: String, val minutes: String, val seconds: String){
    constructor(): this("","","","")

    companion object {
        fun from(days: Long, hours: Int, minutes: Int, seconds: Int): List<Pair<String, String>> {
            val sDays = formatValueWithUnit(days.toInt(), "day")
            val sHours = formatValueWithUnit(hours, "hour")
            val sMinutes = formatValueWithUnit(minutes, "minute")
            val sSeconds = formatValueWithUnit(seconds, "second")
            return listOf(sDays, sHours, sMinutes, sSeconds)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(action: CommonAction) {

    var drawableNumber by remember { mutableStateOf(0) }

    var drawables = (1..4).map {
        loadDrawableResource("cat_${it}.jpeg")
    }


    /*
    val loader = rememberPainterLoader()
    val paletteState = rememberPaletteState(loader = loader)
    LaunchedEffect(painterPoster) {
        paletteState.generate(painterPoster)
    }
    var currentSwatch: Palette.Swatch? by remember { mutableStateOf(null) }

     */
    val primaryColor = Colors.brown
    var currentColor: Color by remember { mutableStateOf(primaryColor) }

    //LaunchedEffect(paletteState.palette) {
    //    currentSwatch = paletteState.palette?.dominantSwatch
    //    currentColor = currentSwatch?.color ?: primaryColor
    //}


    val now by remember { mutableStateOf(LocalDateTime.now()) }
    val remainingTime = LocalDateTime(2024, 3, 5, 22, 54, 54)

    var currentTime: LocalDateTime by remember { mutableStateOf(now) }
    var remainingInWhole by remember { mutableStateOf(emptyList<Pair<String, String>>()) }



    Napier.base(DebugAntilog())



    LaunchedEffect(true) {
        while (true) {

            currentTime = currentTime.plus(1, DateTimeUnit.SECOND)
            remainingInWhole = remainingTime.minus(currentTime).toComponents { days, hours, minutes, seconds, nanoseconds ->
                TimeReaming.from(days, hours, minutes, seconds)
            }

            delay(1000)
        }
    }

    LaunchedEffect(true) {
        while (true) {
            delay(2000)

            if (drawableNumber < drawables.lastIndex) {
                drawableNumber++
            } else {
                drawableNumber = 0
            }
        }
    }







    Box(
        modifier = Modifier.fillMaxSize().background(Colors.cream),
        contentAlignment = Alignment.Center
    ) {

        //Text(text, color = Colors.brown)
        Image(
            painter = drawables[drawableNumber],
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.background(Colors.orange.copy(alpha = 0.8f))
                        .padding(20.dp), contentAlignment = Alignment.Center
                ) {
                    CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleMedium.copy(color = Colors.brown)){

                        val numberTransitionSpec: AnimatedContentTransitionScope<String>.() -> ContentTransform = {
                            slideInVertically(
                                initialOffsetY = { it }
                            ) + fadeIn() with slideOutVertically(
                                targetOffsetY = { -it }
                            ) + fadeOut() using SizeTransform(
                                false
                            )
                        }

                        Column {
                            Text("In Construction")

                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                remainingInWhole.forEach {
                                    val (days, unit) = it
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        AnimatedContent(targetState = days, transitionSpec = numberTransitionSpec) {
                                            Text(days, style = MaterialTheme.typography.displayLarge.copy(Colors.brown2))
                                        }
                                        Text(unit)


                                    }
                                    Spacer(modifier = Modifier.size(7.5.dp))
                                }

                            }
                        }



                    }
                }

            }

        }
    }


    return

    var text: String by remember { mutableStateOf(Strings.loading + " ") }
    val timeMax = 4
    var timeRemaining by remember { mutableStateOf(0) }

    LaunchedEffect(true) {
        while (true) {
            delay(1000)
            text += Strings.dot
            if (timeRemaining == timeMax) {
                action.navigate()
            } else {
                timeRemaining++
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize().background(Colors.cream),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Colors.brown)
    }
}

fun Any.formatWithLeadingZeros(digits: Int): String {
    return this.toString().padStart(digits, '0')
}
fun formatValueWithUnit(value: Int, unit: String): Pair<String, String> {
    val formattedValue = if (value < 10) {
        "0$value"
    } else {
        value.toString()
    }
    val unitString = "$unit${if (value > 1) "s" else ""}"
    return Pair(formattedValue, unitString)
}

@Composable
fun CountDownTimer() {

}












