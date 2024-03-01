package the.beginning.of.the.end.presentation.screens.prequel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import the.beginning.of.the.end.presentation.common.GoBackAction
import the.beginning.of.the.end.theme.Colors

@Composable
fun PrequelScreen(action: GoBackAction) {
    Box(modifier = Modifier.fillMaxSize().background(Colors.cream), contentAlignment = Alignment.Center) {
        Text("PrequelScreen", color = Colors.brown)
    }
}