package com.juliansauer.bktree.tree

class Node(val word: String) {
    val edges = mutableListOf<Edge>()

    fun getChild(distance: Int): Node? {
        return edges.find { edge -> edge.distance == distance }?.to
    }

    fun hasChild(distance: Int): Boolean {
        return edges.any { edge -> edge.distance == distance }
    }

    fun addChild(node: Node, distance: Int) {
        edges.add(Edge(this, node, distance))
    }
}