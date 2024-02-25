# Burkhard Keller Trees

Simple recursive implementation of a Burkhard Keller Tree in Kotlin which can be used for spellchecking or fuzzy string search.

Here's my German blog post explaining Burkhard Keller Trees: https://blog.julian-sauer.com/posts/bk-trees/

## Usage
After running `./gradlew clean build` you can first insert single words into the tree or load words from a file.
This repository contains a list of frequent German words that can be used for testing.
Note that loading the entire list might take a while since the implementation is exemplary and not optimized for performance.

After inserting words you can search/spellcheck for words.
If you use a distance of 0, either the input word will be returned if it exists in the tree or an empty list.
If you use a distance > 0, for example 1, all words within the tree that match the input except for 1 character will be returned.

### Example with loading single words
```shell
java -jar build/libs/bk-tree.bk
> Available commands: LOAD, INSERT, SEARCH, PRINT, EXIT
insert book
> Inserted book into the tree
insert cook
> Inserted cook into the tree
search book 0
> [book]
search bork 1
> [book]
search book 1
> [book, cook]
search unknown 1
> []
```

### Example with loading words from a file
```shell
java -jar build/libs/bk-tree.bk
> Available commands: LOAD, INSERT, SEARCH, PRINT, EXIT
load frequent-words.txt 300
> Inserted 300 words into the tree
search das 0
> [das]
search das 1
> [des, das, daß, da, Das, was, Was, dass]
search unknown 1
> []
```