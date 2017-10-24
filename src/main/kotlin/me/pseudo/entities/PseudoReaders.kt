package me.pseudo.entities

import me.pseudo.io.StorageHandle
import me.pseudo.io.StorageHandle.parsedPseudoExtension
import me.pseudo.io.StorageHandle.pseudoExtension
import java.io.File

abstract class Reader {
    abstract fun read(file: File): LineFeed
    abstract fun read(name: String): LineFeed
    abstract fun write(file: File, value: Any): Boolean
    abstract fun write(pathAppend: String, value: Any): Boolean

    fun exists(file: File) = file.exists()
    fun exists(pathAppend: String, extension: String) = File(File(StorageHandle.sdir.path, pathAppend), extension).exists()
}

class PseudoReader() : Reader() {
    override fun read(file: File): LineFeed {
        return StorageHandle.mapper.readValue(file, LineFeed::class.java)
    }

    override fun read(name: String): LineFeed {
        return StorageHandle.mapper.readValue(appendFileDir(name, pseudoExtension), LineFeed::class.java)
    }

    override fun write(file: File, value: Any): Boolean {
        if (!file.exists()) file.createNewFile()
        StorageHandle.mapper.writeValue(file, value)
        return true
    }

    override fun write(pathAppend: String, value: Any): Boolean {
        val file = File(pathAppend)
        if (!file.exists()) file.createNewFile()
        StorageHandle.mapper.writeValue(file, value)
        return true
    }
}

class ParsedPseudoReader() : Reader() {
    override fun read(file: File): LineFeed {
        return StorageHandle.mapper.readValue(file, LineFeed::class.java)
    }

    override fun read(name: String): LineFeed {
        return StorageHandle.mapper.readValue(appendFileDir(name, parsedPseudoExtension), LineFeed::class.java)
    }

    override fun write(file: File, value: Any): Boolean {
        if (!file.exists()) file.createNewFile()
        StorageHandle.mapper.writeValue(file, value)
        return true
    }

    override fun write(pathAppend: String, value: Any): Boolean {
        val file = File(pathAppend)
        if (!file.exists()) file.createNewFile()
        StorageHandle.mapper.writeValue(file, value)
        return true
    }
}

private fun appendFileDir(path: String) = File(StorageHandle.sdir.path, path).path
private fun appendFileDir(path: String, extension: String) = File(StorageHandle.sdir, (path+extension)).path