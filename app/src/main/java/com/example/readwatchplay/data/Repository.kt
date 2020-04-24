package com.example.readwatchplay.data

import com.example.readwatchplay.model.Item

class Repository {

    fun getItems(): List<Item> {
        return listOf(
            Item("dupa"),
            Item("chuj"),
            Item("chuj"),
            Item("chuj"),
            Item("chuj")
        )
    }
}