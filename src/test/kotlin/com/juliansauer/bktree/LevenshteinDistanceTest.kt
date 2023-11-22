package com.juliansauer.bktree

import com.juliansauer.bktree.tree.LevenshteinDistance
import org.junit.jupiter.api.Test

class LevenshteinDistanceTest {
    @Test
    fun distance() {
        assert(LevenshteinDistance.calculateDistance("foo", "foo") == 0)
        assert(LevenshteinDistance.calculateDistance("foo", "boo") == 1)
        assert(LevenshteinDistance.calculateDistance("foo", "fooo") == 1)
        assert(LevenshteinDistance.calculateDistance("foo", "ofoo") == 1)
        assert(LevenshteinDistance.calculateDistance("foo", "oo") == 1)
        assert(LevenshteinDistance.calculateDistance("foo", "fo") == 1)
        assert(LevenshteinDistance.calculateDistance("foo", "bar") == 3)
    }
}