package com.example.potenseek.helper

import com.example.potenseek.Model.ChildProfile
import com.example.potenseek.Model.ParentProfile
import com.example.potenseek.Model.TPSchedule

class GlobalVar {
    companion object {
        val scheduleList = ArrayList<TPSchedule>()
        val parentList = ArrayList<ParentProfile>()
        val childList = ArrayList<ChildProfile>()
    }
}