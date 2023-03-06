package com.plcoding.cleanarchitecturenoteapp.domain.util

sealed class SortType {
    object Decending: SortType()
    object Ascending: SortType()
}