package org.example.project

 import androidx.compose.runtime.Composable
 import androidx.compose.ui.ExperimentalComposeUiApi
 import androidx.compose.ui.text.font.Font
 import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.window.ComposeViewport
 import kotlinproject.composeapp.generated.resources.NotoSansSC_Regular
 import kotlinproject.composeapp.generated.resources.Res
 import kotlinx.browser.document
import org.jetbrains.compose.resources.Resource

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }

//    Resources.
}

suspend fun loadCjkFont(): FontFamily  {

//    Resource()
//    val regular = resource("font/NotoSansCJKsc-Regular.ttf").readBytes()
//    val bold = resource("font/NotoSansCJKsc-Bold.ttf").readBytes()
//    val italic = resource("font/NotoSansCJKsc-Italic.ttf").readBytes()

    return FontFamily(
        Font(identity = "CJKRegular", data = ByteArray(1), weight = FontWeight.Normal),
    )
}

//@Composable
//fun font(
//    name: String,
//    res: String,
//    weight: FontWeight,
//    style: FontStyle
//): Font = Font("font/$res.ttf", weight, style)