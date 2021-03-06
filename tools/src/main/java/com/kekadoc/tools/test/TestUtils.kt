package com.kekadoc.tools.test

object TestUtils {

    @JvmStatic
    fun testTerminal(name: String, action: () -> Unit) {
        val t = System.currentTimeMillis()
        val tt = System.nanoTime()
        action.invoke()
        println(name + ": " + "ms: " + (System.currentTimeMillis() - t) + " " + "ns: " + (System.nanoTime() - tt))
    }

    @JvmStatic
    fun <T> testTerminal(name: String, action: () -> T): T {
        val t = System.currentTimeMillis()
        val tt = System.nanoTime()
        val a: T = action.invoke()
        println(name + ": " + "ms: " + (System.currentTimeMillis() - t) + " " + "ns: " + (System.nanoTime() - tt))
        return a
    }

}