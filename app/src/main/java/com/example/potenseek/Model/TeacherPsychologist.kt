package com.example.potenseek.Model

data class TeacherPsychologist(
    var id: String,
    var name: String,
    var totalRating: Double,
    var totalParentsRated: Int,
    var casesSolved: Int,
    var about: String,
    var role: TeacherPsychologistRole
)
