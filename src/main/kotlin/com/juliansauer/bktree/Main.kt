package com.juliansauer.bktree

import com.juliansauer.bktree.io.CommandTypes
import com.juliansauer.bktree.io.WordList
import com.juliansauer.bktree.tree.Node
import com.juliansauer.bktree.tree.Tree

val tree = Tree()

fun main() {
    println("> Available commands: ${CommandTypes.values().joinToString(", ")}")
    while (true) {
        val input = readln()
        val arguments = input.split(" ")
        val command = CommandTypes.valueOf(arguments[0].uppercase())
        if (arguments.size != command.expectedArguments) {
            println("> Invalid number of arguments for command $command, expected ${command.expectedArguments} but got ${arguments.size}")
            continue
        }

        when (command) {
            CommandTypes.LOAD -> load(arguments)

            CommandTypes.INSERT -> insert(arguments)

            CommandTypes.SEARCH -> search(arguments)

            CommandTypes.PRINT -> tree.print()
            CommandTypes.EXIT -> return
        }
    }
}

private fun load(arguments: List<String>) {
    val fileName = arguments[1]
    val size = arguments[2].toInt()
    val words = WordList.loadWords(fileName, size)
    tree.insert(words)
    println("> Inserted ${words.size} words into the tree")
}

private fun insert(arguments: List<String>) {
    val word = arguments[1]
    tree.insert(word)
    println("> Inserted $word into the tree")
}

private fun search(arguments: List<String>) {
    val wordToSearchFor = arguments[1]
    val maxDistance = arguments[2].toInt()
    val similarWords = tree.similarWords(wordToSearchFor, maxDistance)
    println("> $similarWords")
}

fun Tree.print() {
    if (root == null)
        return

    print(root!!, 0, 0)
}

private fun print(currentRoot: Node, indent: Int, distance: Int) {
    if (distance == 0)
        println(currentRoot.word)
    else
        println(" ".repeat(indent) + distance + " " + currentRoot.word)
    for (edge in currentRoot.edges) {
        print(edge.to, indent + 2, edge.distance)
    }
}