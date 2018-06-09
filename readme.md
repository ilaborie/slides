
This repository contains a Kotlin DSL to create sildes, 
a NodeJS server to process things I don't want to do on JVM,
some slides of my talks.

This is not a framework to share and enhance (at the time).

How to build slides:

1. grab node dependencies with `yarn`
2. run `npm start` to run the server
3. create your slides in `src/slides` with a `main`
4. add a gradle task to run your main (see examples in `build.gradle`)
5. run the gradle task

TODO
---

* clean code
* document
* Thanks for resources
