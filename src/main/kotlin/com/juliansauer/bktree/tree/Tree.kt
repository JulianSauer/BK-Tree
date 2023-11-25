package com.juliansauer.bktree.tree

class Tree {
    var root: Node? = null

    fun insert(words: List<String>) {
        words.forEach(::insert)
    }

    fun insert(word: String) {
        val node = Node(word)
        if (root == null) root = node
        else insert(root!!, node)
    }

    private fun insert(currentRoot: Node, newNode: Node) {
        val distance = LevenshteinDistance.calculateDistance(currentRoot.word, newNode.word)

        if (currentRoot.hasChild(distance)) insert(currentRoot.getChild(distance)!!, newNode)
        else currentRoot.addChild(newNode, distance)
    }

    fun similarWords(word: String, maxDistance: Int): List<String> {
        if (root == null) return listOf()

        val result = similarWords(root!!, word, maxDistance, 0)
        println("Searched nodes: ${result.second}")
        return result.first
    }

    private fun similarWords(
        currentRoot: Node, word: String, maxDistance: Int, nodeCount: Int
    ): Pair<MutableList<String>, Int> {
        var localNodeCount = 0
        val similarWords = mutableListOf<String>()

        val distance = LevenshteinDistance.calculateDistance(currentRoot.word, word)
        if (distance <= maxDistance) similarWords.add(currentRoot.word)

        val lowerSearchLimit = distance - maxDistance
        val upperSearchLimit = distance + maxDistance

        for (i in lowerSearchLimit..upperSearchLimit) {
            if (currentRoot.hasChild(i)) {
                val result = similarWords(currentRoot.getChild(i)!!, word, maxDistance, nodeCount)
                val similarWordsInChild = result.first
                localNodeCount += result.second
                similarWords.addAll(similarWordsInChild)
            }
        }

        return Pair(similarWords, nodeCount + localNodeCount + 1)
    }

    fun spellCheck(word: String, maxDistance: Int): String {
        if (root == null) return word

        for (i in 0..maxDistance) {
            val similarWords = similarWords(word, i)
            if (similarWords.isNotEmpty()) return similarWords.first()
        }

        return ""
    }

}
