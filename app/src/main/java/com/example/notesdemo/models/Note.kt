package com.example.notesdemo.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var title: String? = null,
    @ColumnInfo var content: String? = null,
    @ColumnInfo var timestamp: String? = null
): Parcelable
