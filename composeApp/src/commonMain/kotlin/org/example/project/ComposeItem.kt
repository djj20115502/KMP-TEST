package org.example.project


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.datetime.Clock

/**
 * author   : dongjunjie.mail@qq.com
 * time     : 2022/3/31
 * change   :
 * describe :
 */


@Composable
fun MyPreView(preview: @Composable () -> Unit) {
    if (LocalInspectionMode.current) {
        preview()
    }
}

@Composable
fun Padding(
    size: Dp = 1.dp,
    color: Color? = null
) {
    Box(
        modifier = Modifier
            .size(size)
            .ifmNotNull(color) {
                background(it)
            })
}

@Composable
fun Padding(
    size: Float = 1f,
    color: Color? = null
) {
    Padding(size.dp, color)
}

fun <T> Modifier.ifmNotNull(any: T?, block: Modifier.(T) -> Modifier): Modifier {
    any?.apply {
        return this@ifmNotNull.block(this)
    }
    return this
}

@Composable
fun RowScope.Div(
    weight: Float = 1f
) {
    Box(modifier = Modifier.weight(weight)) {}
}


@Composable
fun ColumnScope.Div(
    weight: Float = 1f
) {
    Box(modifier = Modifier.weight(weight)) {}
}


@Composable
fun MyDottedLine(
    modifier: Modifier = Modifier,
    dottedColor: Color = Color(0xFFBEBEBE),
    height: Dp = 1.dp,
    bgColor: Color = Color.Transparent,
    elements: List<Float> = listOf(5f, 5f)
) {
    Canvas(
        modifier = modifier
            .zIndex(11f)
            .fillMaxWidth()
            .height(height)
            .background(bgColor)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            dottedColor,
            Offset(x = 0f, y = 0f),
            Offset(x = canvasWidth, y = 0f),
            strokeWidth = canvasHeight,
            pathEffect = PathEffect.dashPathEffect(
                elements.toFloatArray()
            )
        )
    }
}

@Stable
inline val Int.myDp: Dp
    get() = this.dp


@Stable
inline val Float.myDp: Dp
    get() = this.dp

@Stable
inline val Float.mySp: TextUnit
    get() = this.sp

@Stable
inline val Int.mySp: TextUnit
    get() = this.sp
val myPhone10: (old: String, new: String) -> String = maxInput(10)
fun maxInput(size: Int): (old: String, new: String) -> String = { old, new ->
    if (new.length > size) old else new
}

@Composable
fun MyInputView3(
    modifier: Modifier = Modifier,
    ///data 是原始的输入数据   valueIsOK 检查当前数据是否合格   showTip 检查自身以及其他控制后，是否需要显示提示图标
    placeholder: String = "",//占位提示
    value: String? = "",//默认值
    valueKey: String = "", //避免value不改变
    isPhone: Boolean = false,
    keyboardOptions: KeyboardOptions = if (isPhone) KeyboardOptions(keyboardType = KeyboardType.Phone) else KeyboardOptions.Default,
    autoFocus: Boolean = false,
    singleLine: Boolean = true,
    lineHeightStyle: LineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    ),
    textStyle: TextStyle = TextStyle(
        textAlign = TextAlign.Start, fontSize = 14.sp,
        lineHeight = 22.mySp,
        lineHeightStyle = lineHeightStyle,
    ),
    disableTextStyle: TextStyle = TextStyle(
        MyColor.G_FFA8A8A8,
        textAlign = TextAlign.Start,
        fontSize = 14.mySp,
        lineHeight = 22.mySp,
        lineHeightStyle = lineHeightStyle
    ),
    height: Dp = 32.myDp,
    contentAlignment: Alignment = Alignment.CenterStart,
    horizontalPadding: Dp = 14.myDp,
    verticalPadding: Dp = 5.myDp,
    enable: Boolean = true,
    enabledEmoji: Boolean = false,
    focusRequester: FocusRequester = remember {
        FocusRequester()
    },
    background: Color = Color.White,
    placeholderView: @Composable (String) -> Unit =
        @Composable {
            Text(
                text = placeholder,
                color = MyColor.G_FFA8A8A8,
                textAlign = TextAlign.Start,
                fontSize = 14.mySp
            )
        },
    maxInput: Int = Int.MAX_VALUE,
    showCount: Boolean = false,
    showCountView: @Composable (Int, Int) -> Unit =
        @Composable { count, max ->
            Text(
                text = if (max == Int.MAX_VALUE) "($count)" else "($count/$max)",
                color = MyColor.G_FFA8A8A8,
                textAlign = TextAlign.Start,
                fontSize = 12.mySp
            )
        },
    beforeChangeDo: (old: String, new: String) -> String = if (isPhone) myPhone10 else maxInput(
        maxInput
    ),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField -> innerTextField() },
    textModifier: Modifier = Modifier,
    focusRequesterCallBack: (data: FocusRequester) -> Unit = {},
    valueChange: (data: String) -> Unit = { s: String -> myLog(s) },
) {
    focusRequesterCallBack(focusRequester)
    var inputContent by rememberSaveable(value + valueKey) {
        value?.apply(valueChange)
        mutableStateOf(value ?: "")
    }
    if (autoFocus) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
    var focus by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .myClick {
                focusRequester.requestFocus()
            }
            .height(height)
            .background(background)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding))
    {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = contentAlignment) {
            if (inputContent.isEmpty() && !placeholder.isEmpty())
                placeholderView(placeholder)
            BasicTextField(
                modifier = textModifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        focus = it.hasFocus
                    },
                value = inputContent,
                textStyle = if (enable) textStyle else disableTextStyle,
                onValueChange = {
                    if (!enabledEmoji && filterEmoji(it)) {
                        return@BasicTextField
                    }
                    inputContent = beforeChangeDo(inputContent, it)
                    valueChange(inputContent)
                },
                enabled = enable,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                decorationBox = decorationBox
            )
        }
        if (showCount) {
            showCountView(inputContent.length, maxInput)
        }
    }
}


fun filterEmoji(input: String?): Boolean {
    if (input != null) {
        val find =
            input.matches(Regex("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]"))
        if (find) {
            myLog("输入字符串:" + input + "   含有表情包")
        }
        return find
    }
    return false
}


var clickTime = 0 to 0L

/**
 * 使用DisabledInteractionSource 去除默认的黑色点击效果
 * */
fun Modifier.myClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    coolingTime: Long = 500L,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = {
            val place = Throwable().cause?.stackTraceToString().hashCode()
            val now = Clock.System.now().epochSeconds
            //這裏動態修改時間可能使得 後面的點擊還在前
            if (clickTime.first != place || clickTime.second > now) {
                clickTime = 0 to 0L
            }
            if (now - clickTime.second > coolingTime) {
                clickTime = place to now
                onClick.invoke()
            }
        },
        role = role,
    )
}

 