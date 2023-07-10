package com.dahuatech.jna.bean;

import com.sun.jna.Structure;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jna.bean</p>
 * <p>className: Person</p>
 * <p>date: 2023/5/1</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Structure.FieldOrder({"age", "score", "address"})
public class Person extends Structure {
    public static class ByReference extends Person implements Structure.ByReference {}
    public static class ByValue extends Person implements Structure.ByValue {}

    public byte[] age;
    public float score;
    public Address address;
}
