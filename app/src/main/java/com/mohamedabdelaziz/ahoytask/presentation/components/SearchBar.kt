package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    placeholder: String = "",
    showClearIcon: Boolean = false,
    isEnabled: Boolean = true,
    searchTerm: (String) -> Unit = {},
    onClick: () -> Unit = {},
    onCancel: (() -> Unit)? = null,
    isDark:Boolean = false,
    searchString: String = "",
    openKeyboard : Boolean = false
) {

    var searchTermValue by rememberSaveable(searchString) { mutableStateOf(searchString) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember{ FocusRequester() }
    val localFocusManger = LocalFocusManager.current

    LaunchedEffect(searchString){
        if (openKeyboard)
            focusRequester.requestFocus()

        if (searchString.isNotBlank()) {
            focusRequester.freeFocus()
            localFocusManger.clearFocus()
            keyboardController?.hide()
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 14.dp, bottom = 14.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        BasicTextField(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(40.dp)
                .weight(1f)
                .border(1.dp, Primary, RoundedCornerShape(8.dp))
                .background(
                    color = if (isDark) LightNeutral2 else Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.White),
                    onClick = { onClick() }
                )
                .focusRequester(focusRequester),
            value = searchTermValue,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                if (searchTermValue.isNotBlank()) {
                    searchTerm(searchTermValue.trim())
                    keyboardController?.hide()
                }
            },
            onValueChange = {
                searchTermValue = it
            },
            enabled = isEnabled,
            singleLine = true,
            cursorBrush = SolidColor(GreyText),
            textStyle = TextStyle(
                color = DarkNeutral3,
                fontSize = 12.sp,
                fontFamily = Avenir,
                lineHeight = 16.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        tint = GreyText,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 6.dp)
                            .size(14.dp)
                    )
                    Box(Modifier.weight(1f)) {
                        if (searchTermValue.isEmpty()) {
                            Text(
                                placeholder,
                                style = TextStyle(
                                    color = GreyText,
                                    fontSize = 12.sp,
                                    fontFamily = Avenir,
                                    lineHeight = 16.sp,
                                )
                            )
                        } else {
                            innerTextField()
                        }
                    }
                    if (showClearIcon && searchTermValue != "") {
                        IconButton(onClick = { searchTermValue = ""}) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cancel),
                                contentDescription = "Clear Search",
                                tint = Color.LightGray,
                                modifier = Modifier
                                    .size(14.dp)
                            )
                        }
                    }
                }
            }
        )
        if (onCancel != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.White),
                    onClick ={onCancel()}
                ),
                text = stringResource(R.string.lbl_cancel),
                textAlign = TextAlign.Center,
                color = Red,
                fontSize = 12.sp,
                fontFamily = Avenir,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(20.dp))
        }else{
            Spacer(modifier = Modifier.width(20.dp))
        }

    }

}