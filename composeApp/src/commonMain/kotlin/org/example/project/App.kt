package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(typography: Typography? = null) {
    MaterialTheme(
        typography = typography ?: MaterialTheme.typography
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                modifier = Modifier.weight(1f),
                startDestination = "MGM"
            ) {
                composable(route = "MGM-over") { MGMScreenContent(isOver = true) }
                composable(route = "MGM-published") { MGMScreenContent(published = true) }
                composable(route = "MGM") { MGMScreenContent() }
            }


            Row {
                listOf("MGM-over", "MGM-published", "MGM").forEach {
                    Button(
                        modifier = Modifier.height(30.myDp).weight(1f),
                        onClick = {
                            if (it != navController.currentDestination?.route) {
                                navController.navigate(it)
                            }
                        }) {
                        Text(it)
                    }
                }
            }
//            var showContent by remember { mutableStateOf(false) }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    MyImage(Res.drawable.compose_multiplatform)
//                    Text("Compose: $greeting")
//                    Text("Compose: $greeting")
//                    Text("Compose: $greeting")
//                }
//            }
        }
    }
}


@Composable
@Preview
fun MyImage(
    dr: DrawableResource, modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    Image(
        painterResource(dr), contentDescription, modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}


//https://blog.csdn.net/downanddusk/article/details/136037007
//https://github.com/JetBrains/compose-multiplatform/issues/3967
//https://github.com/life888888/cjk-fonts-ttf
//https://github.com/ISNing/XWareManage/commit/7c3d63ce93efa6bc169f2c33c5c8be3511f59bb4