package com.juliansauer.bktree.io

enum class CommandTypes(val expectedArguments: Int) {
    LOAD(3),
    INSERT(2),
    SEARCH(3),
    PRINT(1),
    EXIT(1)
}