package com.namnp.jetpack_compose.kotlin.default_interface

class DefaultImplementationInterfaceKotlin {

    class C3PO : Robot {
        // move() implementation from Robot is available implicitly
        // only have to override speak()
        override fun speak() {
            println("I'm a Kotlin Robot")
        }
    }

    fun main() {
        val c3po = C3PO()
        c3po.move() // default implementation from the Robot interface
        c3po.speak()
    }
}

