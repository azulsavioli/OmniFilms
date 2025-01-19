package com.moobaalabs.omnifilms.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moobaalabs.omnifilms.presentation.theme.TerciaryBlue

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onSearchConfirmed: () -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue(query)) }

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                onSearch(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "Busque por título",
                    color = Color.LightGray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Busca",
                    tint = Color.LightGray
                )
            },
            trailingIcon = {
                if (textState.value.text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Limpar busca",
                        tint = Color.LightGray,
                        modifier = Modifier.clickable {
                            textState.value = TextFieldValue("")
                            onSearch("")
                        }
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchConfirmed()
                    textState.value = TextFieldValue("")
                    onSearch("")
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = TerciaryBlue,
                unfocusedContainerColor = TerciaryBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
