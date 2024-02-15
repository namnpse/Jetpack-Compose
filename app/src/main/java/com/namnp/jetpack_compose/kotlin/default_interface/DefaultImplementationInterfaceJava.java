package com.namnp.jetpack_compose.kotlin.default_interface;

public class DefaultImplementationInterfaceJava {
//    The default implementation is available for Java classes implementing the interface.
//    Java implementation
    public static class C3PO implements Robot {
        // don't need to override move() in interface
        // move() implementation from Robot is available implicitly

        /* to use this default feature, need to enable Xjvm-default=all,
        if not, compile-time error happens (IDE forces C3PO to implement move()),
        if using Kotlin(interface) and Kotlin(Implementation), don't need to enable Xjvm-default=all,
        only for Kotlin-Java client
        */
        @Override
        public void speak() {
            System.out.println("I'm Java Robot");
        }
    }

    public static void main(String[] args) {
        C3PO c3po = new C3PO();
        c3po.move(); // default implementation from the Robot interface
        c3po.speak();
    }
}
