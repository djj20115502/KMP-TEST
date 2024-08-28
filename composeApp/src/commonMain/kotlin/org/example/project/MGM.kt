package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.icon_tip
import kotlinproject.composeapp.generated.resources.icon_worky
import kotlinproject.composeapp.generated.resources.img_box
import kotlinproject.composeapp.generated.resources.img_money
import kotlinproject.composeapp.generated.resources.img_over
import kotlinproject.composeapp.generated.resources.img_title
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MGMScreen() {
    MGMScreenContent(false, false)
}


@Composable
fun MGMScreenContent(
    isOver: Boolean = false,
    published: Boolean = false,
) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFFFF9EA))) {
        MyImage(
            Res.drawable.img_money, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        if (isOver) {
            MGMScreenContentOver()
        } else if (published) {
            MGMScreenContentPublished()
        } else {
            MGMScreenContentNormal()
        }

    }
}
@Preview
@Composable
private fun MGMScreenContentNormal() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Div(165f)
        MyImage(Res.drawable.img_title)
        Div(33f)
        MyImage(Res.drawable.img_box, modifier = Modifier.size(170.dp))
        Div(34f)
        Box {
            Text(
                "邀請碼",
                color = Color(0xFF000000),
                fontSize = 24.sp,
                fontWeight = FontWeight(500)
            )
            Box(modifier = Modifier.height(2.dp).fillMaxWidth().background(Color(0xFFFF9F46)))
        }
        Text(
            "敬請期待\n" +
                "Worky未來不定時推出的活動",
            color = Color(0xFF2C2C2C),
            fontSize = 20.sp
        )
        Div(321f)
    }
}

@Preview
@Composable
private fun MGMScreenContentOver() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Div(165f)
        MyImage(Res.drawable.img_title)
        Div(33f)
        MyImage(Res.drawable.icon_worky, modifier = Modifier.size(155.dp))
        Div(34f)
        Text(
            "敬請期待\n" +
                "Worky未來不定時推出的活動",
            color = Color(0xFF2C2C2C),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Div(321f)
    }
}

@Preview
@Composable
private fun MGMScreenContentPublished() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Div(150f)
        MyImage(Res.drawable.img_title)
        Div(33f)
        MyImage(Res.drawable.icon_worky, modifier = Modifier.size(155.dp))
        Div(34f)
        Row(verticalAlignment = Alignment.CenterVertically) {
            MyImage(Res.drawable.icon_tip, modifier = Modifier.size(20.dp))
            Padding(5f)
            Text(
                "您已經發佈過工作不符合活動資格",
                color = Color(0xFFFF5050),
                fontSize = 16.sp
            )
        }
        Text(
            "敬請期待Worky未來不定期推出的活動喔!",
            color = Color(0xFF2C2C2C),
            fontSize = 20.sp
        )
        Div(321f)
    }
}