package com.moobaalabs.omnifilms.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moobaalabs.omnifilms.presentation.theme.SecondaryPink

@Composable
fun CategoryChip(text: String, isSelected: Boolean = false) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) SecondaryPink else Color(0xFF2C2C2E))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text.trim(),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChipPreview() {
    CategoryChip(text = "Ação")
}