package com.example.notesdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val title: String? = null,
    val content: String? = null,
    val timestamp: String? = null
): Parcelable
