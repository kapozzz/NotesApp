package com.example.notes.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.ui.BabyBlue
import com.example.notes.ui.LightGreen
import com.example.notes.ui.RedOrange
import com.example.notes.ui.RedPink
import com.example.notes.ui.Violet
import java.util.UUID

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, BabyBlue, Violet, RedPink)
    }
}
