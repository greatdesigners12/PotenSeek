package com.example.potenseek.Utils

data class FirebaseWrapper<T, Boolean, Exception>(var data : T? = null, var loading : Boolean, var e : java.lang.Exception? = null)