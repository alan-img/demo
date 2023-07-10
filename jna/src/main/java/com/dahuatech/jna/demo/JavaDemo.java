package com.dahuatech.jna.demo;

import com.dahuatech.jna.bean.A;
import com.dahuatech.jna.bean.B;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import lombok.extern.slf4j.Slf4j;
import scala.Array;
import scala.collection.JavaConversions;
import scala.collection.JavaConverters;
import scala.collection.mutable.ArrayBuffer;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/15</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
@Slf4j
public class JavaDemo {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // int length = 1024 * 1024 * Integer.parseInt(args[0]);
        //
        // A a = new A();
        // a.field = new byte[length];
        // a.write();
        //
        // scanner.nextInt();

        // ArrayBuffer<Integer> arrayBuffer = new ArrayBuffer<>();
        // List<Integer> list = JavaConversions.bufferAsJavaList(arrayBuffer);
        // list.add(1);
        // List<Integer> list3 = JavaConverters.bufferAsJavaListConverter(arrayBuffer).asJava();
        // list3.add(3);
        //
        // System.out.println("arrayBuffer = " + arrayBuffer);

        // int a = 10;
        //
        // ExecutorService threadPool = Executors.newFixedThreadPool(10);
        //
        // FutureTask<Integer> ft = new FutureTask<Integer>(() -> {
        //     if (a > 0) {
        //         throw new RuntimeException("alan");
        //     }
        //     return null;
        // });
        //
        // threadPool.submit(ft);
        // ft.get();
        //
        // threadPool.shutdown();
        // System.out.println("alanb");

        // System.out.println("a.getPointer() = " + a.getPointer());
        // byte[] byteArray = a.getPointer().getByteArray(0, 1024);
        // System.out.println("byteArray = " + Arrays.toString(byteArray));
        // // a.autoWrite();
        // System.out.println("a.getPointer() = " + a.getPointer());
        // byteArray = a.getPointer().getByteArray(0, 1024);
        // System.out.println("byteArray = " + Arrays.toString(byteArray));
        //
        // scanner.nextInt();

        // B b = new B();
        // b.byteSize = 2;
        // b.type = 1;

        // byte[] bytes = new byte[1];
        // for (int i = 0; i < length; i++) {
        //     b.pointer.write(i, bytes, 0, 1);
        // }

        // System.out.println(b.getPointer());
        // b.write();
        //
        // Pointer pointer = b.getPointer();
        // byte[] byteArray = pointer.getByteArray(0, b.size());
        // System.out.println(Arrays.toString(byteArray));
        // System.out.println("pointer = " + pointer);

        // scanner.nextInt();

        // System.out.println("b = " + b);

        // Memory m = new Memory(10);
        //
        // Pointer pointer = m.getPointer(0);
        // System.out.println("pointer = " + Pointer.nativeValue(pointer));
        // System.out.println("m = " + Pointer.nativeValue(m));

        // Memory memory = new Memory(10);
        // memory = null;
        // System.gc();
        // byte[] bytes = new byte[1024];
        // for (int i = 0; i < 1024 * Integer.parseInt(args[0]); i++) {
        //     allocate.put(bytes);
        // }

        // A a = new A();
        // System.out.println(a.size());
        // A a1 = new A.ByReference();
        // System.out.println(a1.size());
        // A a2 = new A.ByValue();
        // System.out.println(a2.size());

        // Pointer pointer = new Memory(1024);
        // System.out.println(Pointer.nativeValue(pointer));
        //
        // Pointer pointer1 = new Memory(2048);
        // System.out.println(Pointer.nativeValue(pointer1));
        //
        // PointerByReference pointerByReference = new PointerByReference(pointer);
        // System.out.println(Pointer.nativeValue(pointerByReference.getValue()));
        // pointerByReference.setValue(pointer1);
        // System.out.println(Pointer.nativeValue(pointerByReference.getValue()));
        //
        // Native.free(Pointer.nativeValue(pointer));
        // Pointer.nativeValue(pointer, 0L);
        // System.out.println(Pointer.nativeValue(pointer));
        //
        // IntByReference intByReference = new IntByReference(40);
        // System.out.println(intByReference.getValue());

        // A a = new A();
        // A[] ar = (A[]) a.toArray(1);
        // for (A a1 : ar) {
        //     System.out.println(a1);
        // }
        //
        // System.out.println();
        // A[] arr = (A[]) a.toArray(2);
        // for (A a1 : arr) {
        //     System.out.println(a1);
        // }

        // A.ByReference a1 = new A.ByReference();
        // A[] arr1 = (A[])a1.toArray(2);
        // for (A a2 : arr1) {
        //     System.out.println(a2);
        // }

        // A.ByReference a = new A.ByReference();
        // A[] arr = (A[]) a.toArray(2);
        //
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i].b);
        // }
        //
        // System.out.println("*******************");
        // for (int i = 0; i < arr.length; i++) {
        //     B.ByReference b = new B.ByReference();
        //     arr[i].b = b;
        // }
        //
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i].b);
        // }
        // System.out.println("*******************");
        //
        // Pointer pointer = Pointer.NULL;
        // System.out.println(pointer);
        // System.out.println(Pointer.nativeValue(pointer));
        //
        // System.out.println("*******************");
        //
        // Person[] p = new Person[10];
        // p[0] = new Person("alan", 2);
        // Person p1 = p[0];
        // p1.setName("jack");
        // System.out.println(Arrays.toString(p));

    }
}
