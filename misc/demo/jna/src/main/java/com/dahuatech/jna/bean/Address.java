package com.dahuatech.jna.bean;

import com.sun.jna.Structure;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jna.bean</p>
 * <p>className: Address</p>
 * <p>date: 2023/5/1</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Structure.FieldOrder({"nation", "province", "city"})
public class Address extends Structure {
    public static class ByReference extends Address implements Structure.ByReference {}
    public static class ByValue extends Address implements Structure.ByValue {}

    public String nation;
    public String province;
    public String city;
}
