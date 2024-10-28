package com.example.starwars.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReadMoreInfo(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Row(modifier) {
        Text(text = text, modifier = Modifier.align(Alignment.CenterVertically))
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = contentDescription
            )
        }
    }
}
