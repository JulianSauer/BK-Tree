package com.juliansauer.bktree

import com.juliansauer.bktree.io.WordList
import com.juliansauer.bktree.tree.Node
import com.juliansauer.bktree.tree.Tree
import kotlin.system.measureTimeMillis

fun main() {
    val words = WordList.getWords("frequent-words.txt", 1000)

    val tree = Tree()
    println(measureTimeMillis { tree.insert(words) })

    var word = tree.spellCheck("Buch", 0)
    println("Word found: $word")

    word = tree.spellCheck("mal", 0)
    println("Word found: $word")

    word = tree.spellCheck("fest", 0)
    println("Word found: $word")

    word = tree.spellCheck("Publikum", 0)
    println("Word found: $word")

    word = tree.spellCheck("Bxch", 1)
    println("Word found: $word")

    word = tree.spellCheck("mxl", 1)
    println("Word found: $word")

    word = tree.spellCheck("fxst", 1)
    println("Word found: $word")

    word = tree.spellCheck("ublikum", 1)
    println("Word found: $word")
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