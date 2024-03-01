package the.beginning.of.the.end.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import the.beginning.of.the.end.presentation.common.CommonAction
import the.beginning.of.the.end.presentation.common.GoBackAction
import the.beginning.of.the.end.theme.Colors
import the.beginning.of.the.end.theme.Strings

interface HomeAction: GoBackAction {
    fun toPrequel()
}


@Composable
fun HomeScreen(action: HomeAction) {
    Box(modifier = Modifier.fillMaxSize().background(Colors.cream), contentAlignment = Alignment.Center) {
        Text("Home", color = Colors.brown)
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Button(onClick = {
                action.toPrequel()
            }){
                Text(Strings.prequel)
            }
        }
    }
}