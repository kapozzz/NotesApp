package com.example.notes.notes.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun DateForItems(
    date: Pair<Int, Int>,
    modifier: Modifier = Modifier
) {
    val day = date.first.toString()
    val month = when (date.second) {
        1 -> {
            "January"
        }

        2 -> {
            "February"
        }

        3 -> {
            "March"
        }

        4 -> {
            "April"
        }

        5 -> {
            "May"
        }

        6 -> {
            "June"
        }

        7 -> {
            "July"
        }

        8 -> {
            "August"
        }

        9 -> {
            "September"
        }

        10 -> {
            "October"
        }

        11 -> {
            "November"
        }

        12 -> {
            "December"
        }

        else -> {
            "Invalid date"
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .shadow(elevation = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = month + " " + day,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}