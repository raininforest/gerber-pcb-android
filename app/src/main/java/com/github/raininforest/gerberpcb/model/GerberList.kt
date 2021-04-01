package com.github.raininforest.gerberpcb.model

class GerberList() {
    private val list: MutableList<GerberItem> = mutableListOf()

    fun add(item: GerberItem) {
        list.add(item)
    }

    fun remove(item: GerberItem) {
        list.remove(item)
    }

    fun clear() {
        list.clear()
    }

    fun getList(): List<GerberItem> {
        return list
    }

    fun get(position: Int): GerberItem {
        return list[position]
    }

    fun getSize(): Int = list.size
}