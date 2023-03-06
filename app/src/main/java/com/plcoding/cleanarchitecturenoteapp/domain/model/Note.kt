package com.plcoding.cleanarchitecturenoteapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var title:String,
    var content:String,
    var timestamp:String,
    var color:String,
    @PrimaryKey val id: Int? = null
) {
}