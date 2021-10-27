package com.example.nooverdraft.storage.utility

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

abstract class FileStorage<T>(private val context: Context, name: String, extension: String) :
    Storage<T> {

    companion object {
        const val PREFIX: String = "storage_"
    }

    private val fileName: String = PREFIX + name + extension
    private var data: HashMap<Int, T> = HashMap()
    private var nextId: Int = 1

    init {
        read()
    }

    // return Contact(id, obj.firstname, obj.lastName ... ) dans fun create dans ContactJsonFileStorage

    protected abstract fun create(id: Int, obj: T): T
    protected abstract fun dataToString(data: HashMap<Int, T>): String
    protected abstract fun stringToData(value: String): HashMap<Int, T>

    override fun read() {
        try {
            val input = context.openFileInput(fileName)
            println(context.filesDir)
            Log.i("fich", context.filesDir.toString())
            if (input != null) {
                val builder = StringBuilder()
                val bufferedReader = BufferedReader(InputStreamReader(input))
                var temp: String? = bufferedReader.readLine()
                while (temp != null) {
                    builder.append(temp)
                    temp = bufferedReader.readLine()
                }
                input.close()
                data = stringToData(builder.toString())
                nextId = if (data.keys.maxOrNull() == null) 1 else data.keys.maxOrNull()!! + 1
            }
        } catch (e: FileNotFoundException) {
            write()
        }
    }

    override fun write() {
        val output = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val writer = OutputStreamWriter(output)
        writer.write(dataToString(data))
        writer.close()
    }
    // insérer la donnée dans la forme de stockage
    override fun insert(obj: T) {
        data.put(nextId, create(nextId, obj))
        nextId++
        write()
    }

    override fun size(): Int {
        return data.size
    }

    override fun find(id: Int): T? {
        return data[id]
    }

    override fun findAll(): List<T> {
        return data.toList().map { it.second }
    }

    override fun update(id: Int, obj: T) {
        data.put(id, obj)
        write()
    }

    override fun delete(id: Int) {
        data.remove(id)
        write()
    }
}