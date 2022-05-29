package com.mohamedabdelaziz.ahoytask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedabdelaziz.ahoytask.core.ui.theme.Avenir
import com.mohamedabdelaziz.ahoytask.core.ui.theme.DarkNeutral1
import com.mohamedabdelaziz.ahoytask.core.ui.theme.LightNeutral3

@Composable
fun WeatherRaw(image: Int, text:String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp, bottom = 5.dp),
         ) {
            if (image!=0)
                 Image(
                modifier = Modifier
                      .width(24.dp)
                    .height(24.dp)
                     ,
                painter = painterResource(image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
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
        Divider(color = LightNeutral3)


    }

}