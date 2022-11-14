package com.example.potenseek.Model

import com.google.firebase.Timestamp

data class Chat(
    var time: String? = null,
    var message: String? = null,
    var to: String? = null,
    var from: String? = null,
    var read: Boolean? = false
)
