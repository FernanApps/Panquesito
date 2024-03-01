import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import the.beginning.of.the.end.App
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("The Beginning of The End") {
            App()
        }
    }
}
