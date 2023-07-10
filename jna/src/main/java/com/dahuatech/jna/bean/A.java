package com.dahuatech.jna.bean;

import com.sun.jna.Structure;

@Structure.FieldOrder({"field", "b"})
public class A extends Structure {
    public static class ByReference extends A implements Structure.ByReference {
    }

    ;

    public static class ByValue extends A implements Structure.ByValue {
    }

    ;

    public byte[] field;
    public B.ByReference b;
}