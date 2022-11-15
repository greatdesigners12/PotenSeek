package com.example.potenseek.Model

import com.google.firebase.Timestamp

data class Payment(
    var time: String? = null,
    var madeby: String? = null,
    var to: String? = null,
    var amount: Int? = null
)
