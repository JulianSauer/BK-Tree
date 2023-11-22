package com.juliansauer.bktree.tree

class Tree {
    var root: Node? = null

    fun insert(words: List<String>) {
        words.forEach { word -> insert(word) }
    }

    fun insert(word: String) {
        val node = Node(word)
        if (root == null) {
            root = node
            return
        }

        insert(root!!, node)
    }

    private fun insert(currentRoot: Node, newNode: Node) {
        val distance = LevenshteinDistance.calculateDistance(currentRoot.word, newNode.word)

        if (currentRoot.hasChild(distance))
            insert(currentRoot.getChild(distance)!!, newNode)
        else
            currentRoot.addChild(newNode, distance)
    }

    fun similarWords(word: String, maxDistance: Int): List<String> {
        if (root == null)
            return listOf()

        return similarWords(root!!, word, maxDistance)
    }

    private fun similarWords(currentRoot: Node, word: String, maxDistance: Int): MutableList<String> {
        val similarWords = mutableListOf<String>()

        val distance = LevenshteinDistance.calculateDistance(currentRoot.word, word)
        if (distance <= maxDistance)
            similarWords.add(currentRoot.word)

        val lowerSearchLimit = distance - maxDistance
        val upperSearchLimit = distance + maxDistance

        for (i in lowerSearchLimit..upperSearchLimit) {
            if (currentRoot.hasChild(i)) {
                val similarWordsInChild = similarWords(currentRoot.getChild(i)!!, word, maxDistance)
                similarWords.addAll(similarWordsInChild)
            }
        }

        return similarWords
    }

    fun spellCheck(word: String, maxDistance: Int): String {
        if (root == null)
            return word

        for (i in 0..maxDistance) {
            val similarWords = similarWords(word, i)
            if (similarWords.isNotEmpty())
                return similarWords.first()
        }

        return word
    }

}
