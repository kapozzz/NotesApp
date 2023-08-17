package com.example.notes.notes.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notes.R

@Composable
fun EmptyNotes(
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = if (searchQuery != "") R.drawable.ic_empty_search_result
            else R.drawable.ic_empty_notes),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = if (searchQuery != "") "Nothing found" else "No notes",
            style = MaterialTheme.typography.titleSmall
        )
    }
}