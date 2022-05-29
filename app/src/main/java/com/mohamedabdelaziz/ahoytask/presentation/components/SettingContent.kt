package com.mohamedabdelaziz.ahoytask.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohamedabdelaziz.ahoytask.R
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Avenir
import com.mohamedabdelaziz.ahoytask.core.ui.theme.DarkNeutral1
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingContent(viewModel: WeatherViewModel = hiltViewModel()) {
    val radioOptions = listOf(
        stringResource(id = R.string.lbl_celsius),
        stringResource(id = R.string.lbl_fahrenheit),
    )
    val scope = rememberCoroutineScope()
    val temp = remember { mutableStateOf("") }
    temp.value = viewModel.temperature
    if (temp.value == "")
        temp.value = radioOptions[0]

    if (temp.value == "metric")
        temp.value = stringResource(id = R.string.lbl_celsius)
    else if (temp.value == "imperial")
        temp.value = stringResource(id = R.string.lbl_fahrenheit)

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(temp.value) }

    scope.launch {
        if (selectedOption == radioOptions[0])
            viewModel.setTempType("metric")
        else if (selectedOption == radioOptions[1])
            viewModel.setTempType("imperial")
    }

    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)


    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.lbl_select_temp_type),
                fontFamily = Avenir,
                fontWeight = FontWeight.W900,
                fontSize = 14.sp,
                color = DarkNeutral1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            modifier = Modifier.padding(start = 16.dp),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 12.dp),
                            textAlign = TextAlign.Start,
                            text = text,
                            fontFamily = Avenir,
                            fontWeight = FontWeight.W900,
                            fontSize = 14.sp,
                            color = DarkNeutral1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

    }
}