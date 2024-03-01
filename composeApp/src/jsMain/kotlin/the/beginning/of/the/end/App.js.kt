package the.beginning.of.the.end

import kotlinx.browser.window

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}