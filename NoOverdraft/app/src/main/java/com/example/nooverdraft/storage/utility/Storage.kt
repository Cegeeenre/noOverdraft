package com.example.nooverdraft.storage.utility

interface Storage<T> {
    fun read()

    fun write()

    fun insert(obj: T)

    fun size(): Int

    fun find(id: Int): T?

    fun findAll(): List<T>

    fun update(id: Int, obj: T)

    fun delete(id: Int)
}