/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package the.beginning.of.the.end.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
//import kmp.kutils.KUtils.decodeSafe64
//import kmp.kutils.KUtils.encodeSafe64
import kotlinx.serialization.json.Json




enum class Screens(
    private val args: List<String>? = null,
    val title: String? = "",
    val isTabItem: Boolean = false,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
) {
    Splash,
    Login,
    Home(title = "home", isTabItem = true, selectedIcon = Icons.Default.Home, unselectedIcon = Icons.Outlined.Home),
    Prequel
    ;



    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }

    fun withArgs(vararg args: Any): String {
        val destination = StringBuilder()
        args.forEach { arg ->
            destination.append("/$arg")
        }
        return name + destination
    }
    //inline fun <reified T: Any> withObject(model: T) = withArgs(_pass<T>(model))
    //inline fun <reified T: Any> withObject(key: String, model: T) = withArgs(key, _pass<T>(model))


    //inline fun <reified T> getObject(key: String, pathMap: Map<String, String>) =
    //    pathMap.getValue(key).decodeSafe64.toModel<T>()


    //inline fun <reified T> _pass(object1: T) = object1.toJson().encodeSafe64




    companion object {

    }
}

