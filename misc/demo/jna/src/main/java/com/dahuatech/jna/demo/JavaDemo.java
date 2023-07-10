package com.dahuatech.jna.demo;

import com.dahuatech.jna.bean.Address;
import com.dahuatech.jna.bean.Person;
import com.dahuatech.jna.service.TestService;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import lombok.experimental.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/3/17</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    private static final Logger logger = LoggerFactory.getLogger(JavaDemo.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        Person person = new Person.ByValue();
        person.age = new byte[1024 * 1024 * 50];
        // System.out.println("person = " + person);
        person.write();
        // Pointer pointer = person.getPointer();

        // Person personByReference = new Person();
        //
        scanner.nextInt();
        //
        // Person[] personArray = (Person[]) personByReference.toArray(Integer.parseInt(args[0]));
        // for (Person person : personArray) {
        //     person.age = 10;
        //     person.score = 99.9F;
        //     Address address = new Address();
        //     address.province = "anhui";
        //     address.city = "fuyang";
        //     address.nation = "china";
        //     person.address = address;
        // }
        //
        // scanner.nextInt();


        // Memory memory = new Memory(1024);
        // System.out.println("Memory.nativeValue(memory) = " + Memory.nativeValue(memory));
        //
        // PointerByReference pointer = new PointerByReference(memory);
        // Pointer memory1 = pointer.getValue();
        // System.out.println("Memory.nativeValue(memory1) = " + Memory.nativeValue(memory1));
        //
        // Memory memory3 = new Memory(2048);
        // System.out.println("Memory.nativeValue(memory3) = " + Memory.nativeValue(memory3));
        // pointer.getPointer().setPointer(0, memory3);
        // Pointer memory2 = pointer.getValue();
        // System.out.println("Memory.nativeValue(memory2) = " + Memory.nativeValue(memory2));
        // Native.free(Memory.nativeValue(memory));
        // Memory.nativeValue(memory, 0);


        // IntByReference intByReference = new IntByReference(10);
        // System.out.println("intByReference.getPointer().getInt(0) = " + intByReference.getPointer().getInt(0));
        // intByReference.setValue(40);
        // System.out.println("intByReference = " + intByReference);
        // System.out.println("intByReference.getValue() = " + intByReference.getValue());

        // System.out.println(Native.POINTER_SIZE);

        // int num = 1024 * 1024 * Integer.parseInt(args[0]);
        // Memory memory = new Memory(num);
        // memory.write(0, new byte[num], 0, num);
        // scanner.nextInt();
        // Native.free(Pointer.nativeValue(memory));
        // Pointer.nativeValue(memory, 0L);
        // scanner.nextInt();

        // TestService interfaceTest = Native.load("./cmake-build-release-hadoop104/libclib.so", TestService.class);
        // System.out.println(interfaceTest);
        // Person p = new Person();
        // Address[] parr = (Address[]) p.person.toArray(2);
        // for (int i = 0; i < parr.length; i++) {
        //     System.out.println(parr[i]);
        // }
        // p.name = "alan";
        // p.age = 18;
        // p.score = 12.9f;
        // Address.ByReference addr = new Address.ByReference();
        // Address[] addrArray = (Address[])addr.toArray(2);
        // for (int i = 0; i < addrArray.length; i++) {
        //     addrArray[i].nation = "alan";
        //     addrArray[i].province = "anhui";
        //     addrArray[i].city = "hangzhou";
        // }
        // p.addr = addr;
        // int ret = interfaceTest.hello(p);
        // System.out.println(ret);

        // Pointer pointer = Pointer.NULL;
        // System.out.println(pointer);
        // System.out.println(Pointer.nativeValue(pointer));
        //
        // Memory memory = new Memory(4);
        // memory.setInt(0, 10);
        // pointer = memory;
        //
        // System.out.println("***************************");
        // System.out.println(pointer);
        // System.out.println(pointer.getInt(0));
        // System.out.println(Pointer.nativeValue(pointer));
        // System.out.println(Native.POINTER_SIZE);
        // Pointer.nativeValue(pointer, 0);
        //
        // System.out.println("***************************");
        // System.out.println(pointer);
        // // System.out.println(pointer.getInt(0));
        // System.out.println(Pointer.nativeValue(pointer));
        //
        // System.out.println("***************************");
        // IntByReference intByReference = new IntByReference(10);
        // System.out.println(intByReference);
        // System.out.println(intByReference.getPointer());
    }
}
