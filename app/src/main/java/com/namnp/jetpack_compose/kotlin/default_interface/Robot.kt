package com.namnp.jetpack_compose.kotlin.default_interface

@JvmDefaultWithCompatibility // add if it's a lib, and other apps using this lib without enabling Xjvm-default=true
interface Robot {
    fun move() { println("~I'm walking~") }  // will be default in the Java interface
    fun speak()
}

/* to see the Java code behind the scene Kotlin code: fun move() { println("~I'm walking~") }
find all these details by using the bytecode viewer that comes with the Kotlin plugin
(Tools -> Kotlin -> Show Kotlin Bytecode, and then the Decompile option).
*/
/* public interface Robot {
    void move();
    void speak();

    public static final class DefaultImpls {
        public static void move() {
            System.out.print("~I'm walking~");
        }
    }
}*/
