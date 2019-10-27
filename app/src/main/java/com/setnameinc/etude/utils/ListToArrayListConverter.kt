package com.setnameinc.etude.utils

object ListToArrayListConverter {
    fun <T> convertToArrayList(list: List<T>) : ArrayList<T>{
        val arrayList = arrayListOf<T>()
        arrayList.addAll(list)
        return arrayList
    }
}