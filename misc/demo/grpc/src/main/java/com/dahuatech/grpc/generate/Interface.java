// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: interface.proto

package com.dahuatech.grpc.generate;

public final class Interface {
  private Interface() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_dahuatech_grpc_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_dahuatech_grpc_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_dahuatech_grpc_Request_MapEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_dahuatech_grpc_Request_MapEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_dahuatech_grpc_Student_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_dahuatech_grpc_Student_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_dahuatech_grpc_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_dahuatech_grpc_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017interface.proto\022\022com.dahuatech.grpc\"\203\001" +
      "\n\007Request\022\014\n\004name\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\0221\n\003" +
      "map\030\003 \003(\0132$.com.dahuatech.grpc.Request.M" +
      "apEntry\032*\n\010MapEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005valu" +
      "e\030\002 \001(\t:\0028\001\"5\n\007Student\022\014\n\004name\030\001 \001(\t\022\013\n\003" +
      "age\030\002 \001(\005\022\017\n\007address\030\003 \001(\t\"8\n\010Response\022," +
      "\n\007student\030\001 \001(\0132\033.com.dahuatech.grpc.Stu" +
      "dent2T\n\020InterfaceService\022@\n\003get\022\033.com.da" +
      "huatech.grpc.Request\032\034.com.dahuatech.grp" +
      "c.ResponseB\037\n\033com.dahuatech.grpc.generat",
      "eP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_dahuatech_grpc_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_dahuatech_grpc_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_dahuatech_grpc_Request_descriptor,
        new java.lang.String[] { "Name", "Age", "Map", });
    internal_static_com_dahuatech_grpc_Request_MapEntry_descriptor =
      internal_static_com_dahuatech_grpc_Request_descriptor.getNestedTypes().get(0);
    internal_static_com_dahuatech_grpc_Request_MapEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_dahuatech_grpc_Request_MapEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_com_dahuatech_grpc_Student_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_dahuatech_grpc_Student_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_dahuatech_grpc_Student_descriptor,
        new java.lang.String[] { "Name", "Age", "Address", });
    internal_static_com_dahuatech_grpc_Response_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_dahuatech_grpc_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_dahuatech_grpc_Response_descriptor,
        new java.lang.String[] { "Student", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}