package com.juliansauer.bktree.tree

class LevenshteinDistance {
    companion object {
        fun calculateDistance(word1: String, word2: String): Int {
            if (word1.isEmpty())
                return word2.length
            if (word2.isEmpty())
                return word1.length

            if (word1[0] == word2[0])
                return calculateDistance(word1.tail(), word2.tail())
            return 1 + minOf(
                    calculateDistance(word1.tail(), word2),
                    calculateDistance(word1, word2.tail()),
                    calculateDistance(word1.tail(), word2.tail())
            )
        }

        private fun String.tail(): String {
            return this.substring(1)
        }
    }
}
