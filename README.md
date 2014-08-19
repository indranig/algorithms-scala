algorithms-scala
================

This attempts to port the Java code from [Sedgewick's algorithms textbook](http://algs4.cs.princeton.edu/home/)
to Scala. The original textbook code is in this [repo](https://github.com/alanktwong/AlgorithmsSedgewick).
See also [Scalacaster](https://github.com/vkostyukov/scalacaster) for a functional approach

Why do this?
============

1. For fun
2. A lot of the textbook code exists because Java does not have a built-in REPL like Scala does.
3. A lot of the textbook code exists to interface with concerns outside of algorithms such as a
Swing GUI, I/O, etc. Rather than reinvent the wheel, wrappers using external libraries will be
used so that we can just get down to business.
4. All of the textbook code is in the default package, which is bad coding practice. Since Scala
has a REPL, partitioning the code into different packages won't change the easy of using the code.
5. The textbook code uses the main method for unit tests, rather than unit test framework
like JUnit or ScalaTest.
6. Since Scala has a very rich collections library, it would be easy to "cheat" using the library,
just as it would be easy to "cheat" using the Java collections framework. I
will avoid this as far as possible and use scala.Array for data structures as it is the natural
analog of a Java array. For interfaces, a scala.collection.Seq[A] will be favored.


Excluded data:
==============

The following data files are missing from this repository, due to their large size:


|File           |Size      | Used by
|---------------|----------|---------------------
|leipzig1M.txt  |129.6 MB  | searching
|largeUF.txt    | 25.8 MB  | fundamentals

An archive that contains these data files (as well as all the other ones used in the book) can be found
[here](http://algs4.cs.princeton.edu/code/algs4-data.zip). Place them in the `resources` directory,
preferably under `test` rather than `main`