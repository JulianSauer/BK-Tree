package com.juliansauer.bktree.io

import java.io.File

class WordList {
    companion object {
        fun getWords(fileName: String, size: Int) = File(fileName).useLines { it.toList().subList(0, size) }
    }

}
