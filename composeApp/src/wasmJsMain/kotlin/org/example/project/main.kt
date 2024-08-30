package org.example.project

import androidx.compose.material.Typography
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.ComposeViewport
import kotlinproject.composeapp.generated.resources.Res
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        var typography by remember { mutableStateOf<Typography?>(null) }
        LaunchedEffect(Unit) {
            val font = loadCjkFont()
            typography = Typography(defaultFontFamily = font)
        }
        App(typography = typography)
    }
}

@OptIn(ExperimentalResourceApi::class)
suspend fun loadCjkFont(): FontFamily {
    val regular = Res.readBytes("font/NotoSansSC-Regular.ttf")
    return FontFamily(
        Font(identity = "NotoRegular", data = regular, weight = FontWeight.Normal),
    )
}
