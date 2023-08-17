package com.example.notes.notes.presentation.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.notes.domain.util.NoteOrder
import com.example.notes.notes.presentation.util.LocalConstants

@Composable
fun SearchBar(
    noteOrder: NoteOrder,
    onOrderChange: (NoteOrder, String) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: String = LocalConstants.EMPTY_STRING,
) {

    val localSearchRequesterState = remember {
        mutableStateOf(searchQuery)
    }

    TextField(
        value = localSearchRequesterState.value,
        onValueChange = { newSearchQuery ->
            localSearchRequesterState.value = newSearchQuery
            onOrderChange(
                noteOrder, newSearchQuery
            )
        },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.background,

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        placeholder = { Text(text = "Search", style = MaterialTheme.typography.bodySmall) },
        trailingIcon = {
            if (localSearchRequesterState.value == LocalConstants.EMPTY_STRING) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(30.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onOrderChange(
                                noteOrder, LocalConstants.EMPTY_STRING
                            )
                            localSearchRequesterState.value = LocalConstants.EMPTY_STRING
                        }
                )
            }
        }
    )
}