package com.dahuatech.jna.bean;

import com.sun.jna.Structure;


@Structure.FieldOrder({"field"})
public class B extends Structure {
    public static class ByReference extends B implements Structure.ByReference {
    };

    public static class ByValue extends B implements Structure.ByValue {
    };

    public int field;
}
