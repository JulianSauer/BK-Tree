package com.juliansauer.bktree

import com.juliansauer.bktree.tree.Tree
import org.junit.jupiter.api.Test

class TreeTest {
    @Test
    fun similarWords() {
        val tree = Tree()
        tree.insert(listOf("foo", "fooo", "oo"))
        assert(tree.similarWords("foo", 0) == listOf("foo"))
        assert(tree.similarWords("foo", 1) == listOf("foo", "fooo", "oo"))
        assert(tree.similarWords("bar", 0) == listOf<String>())
    }

    @Test
    fun similarWordsWithMultipleInserts() {
        val tree = Tree()
        tree.insert(listOf("foo", "fooo"))
        tree.insert("oo")
        assert(tree.similarWords("foo", 0) == listOf("foo"))
        assert(tree.similarWords("foo", 1) == listOf("foo", "fooo", "oo"))
        assert(tree.similarWords("bar", 0) == listOf<String>())
    }

    @Test
    fun fixSpelling() {
        val tree = Tree()
        tree.insert(listOf("foo", "fooo", "oo"))
        assert(tree.spellCheck("foo", 2) == "foo")
        assert(tree.spellCheck("boo", 2) == "foo")
        assert(tree.spellCheck("bor", 2) == "foo")
        assert(tree.spellCheck("boo", 0) == "")
    }
}
