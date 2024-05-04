package com.alura.sorria.ui.components



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextCustom(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight? = FontWeight.Bold,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier.padding(8.dp),
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}