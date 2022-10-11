package com.example.bitfitt

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "dayTable")
data class DayEntity(
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "comments") val comments: String?,
    @ColumnInfo(name = "hours") val hours: Int?,
    @ColumnInfo(name = "rating") val rating: Int?
)