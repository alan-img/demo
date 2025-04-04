// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rpc.proto

package com.dahuatech.grpc.generate;

/**
 * Protobuf type {@code com.dahuatech.grpc.Response}
 */
public  final class Response extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.dahuatech.grpc.Response)
    ResponseOrBuilder {
  // Use Response.newBuilder() to construct.
  private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Response() {
    student_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Response(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              student_ = new java.util.ArrayList<com.dahuatech.grpc.generate.Student>();
              mutable_bitField0_ |= 0x00000001;
            }
            student_.add(
                input.readMessage(com.dahuatech.grpc.generate.Student.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        student_ = java.util.Collections.unmodifiableList(student_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.dahuatech.grpc.generate.Rpc.internal_static_com_dahuatech_grpc_Response_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.dahuatech.grpc.generate.Rpc.internal_static_com_dahuatech_grpc_Response_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.dahuatech.grpc.generate.Response.class, com.dahuatech.grpc.generate.Response.Builder.class);
  }

  public static final int STUDENT_FIELD_NUMBER = 1;
  private java.util.List<com.dahuatech.grpc.generate.Student> student_;
  /**
   * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
   */
  public java.util.List<com.dahuatech.grpc.generate.Student> getStudentList() {
    return student_;
  }
  /**
   * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
   */
  public java.util.List<? extends com.dahuatech.grpc.generate.StudentOrBuilder> 
      getStudentOrBuilderList() {
    return student_;
  }
  /**
   * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
   */
  public int getStudentCount() {
    return student_.size();
  }
  /**
   * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
   */
  public com.dahuatech.grpc.generate.Student getStudent(int index) {
    return student_.get(index);
  }
  /**
   * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
   */
  public com.dahuatech.grpc.generate.StudentOrBuilder getStudentOrBuilder(
      int index) {
    return student_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < student_.size(); i++) {
      output.writeMessage(1, student_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < student_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, student_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.dahuatech.grpc.generate.Response)) {
      return super.equals(obj);
    }
    com.dahuatech.grpc.generate.Response other = (com.dahuatech.grpc.generate.Response) obj;

    boolean result = true;
    result = result && getStudentList()
        .equals(other.getStudentList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (getStudentCount() > 0) {
      hash = (37 * hash) + STUDENT_FIELD_NUMBER;
      hash = (53 * hash) + getStudentList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.dahuatech.grpc.generate.Response parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.dahuatech.grpc.generate.Response parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.dahuatech.grpc.generate.Response parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.dahuatech.grpc.generate.Response parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.dahuatech.grpc.generate.Response prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.dahuatech.grpc.Response}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.dahuatech.grpc.Response)
      com.dahuatech.grpc.generate.ResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.dahuatech.grpc.generate.Rpc.internal_static_com_dahuatech_grpc_Response_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.dahuatech.grpc.generate.Rpc.internal_static_com_dahuatech_grpc_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.dahuatech.grpc.generate.Response.class, com.dahuatech.grpc.generate.Response.Builder.class);
    }

    // Construct using com.dahuatech.grpc.generate.Response.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getStudentFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (studentBuilder_ == null) {
        student_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        studentBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.dahuatech.grpc.generate.Rpc.internal_static_com_dahuatech_grpc_Response_descriptor;
    }

    public com.dahuatech.grpc.generate.Response getDefaultInstanceForType() {
      return com.dahuatech.grpc.generate.Response.getDefaultInstance();
    }

    public com.dahuatech.grpc.generate.Response build() {
      com.dahuatech.grpc.generate.Response result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.dahuatech.grpc.generate.Response buildPartial() {
      com.dahuatech.grpc.generate.Response result = new com.dahuatech.grpc.generate.Response(this);
      int from_bitField0_ = bitField0_;
      if (studentBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          student_ = java.util.Collections.unmodifiableList(student_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.student_ = student_;
      } else {
        result.student_ = studentBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.dahuatech.grpc.generate.Response) {
        return mergeFrom((com.dahuatech.grpc.generate.Response)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.dahuatech.grpc.generate.Response other) {
      if (other == com.dahuatech.grpc.generate.Response.getDefaultInstance()) return this;
      if (studentBuilder_ == null) {
        if (!other.student_.isEmpty()) {
          if (student_.isEmpty()) {
            student_ = other.student_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureStudentIsMutable();
            student_.addAll(other.student_);
          }
          onChanged();
        }
      } else {
        if (!other.student_.isEmpty()) {
          if (studentBuilder_.isEmpty()) {
            studentBuilder_.dispose();
            studentBuilder_ = null;
            student_ = other.student_;
            bitField0_ = (bitField0_ & ~0x00000001);
            studentBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getStudentFieldBuilder() : null;
          } else {
            studentBuilder_.addAllMessages(other.student_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.dahuatech.grpc.generate.Response parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.dahuatech.grpc.generate.Response) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.dahuatech.grpc.generate.Student> student_ =
      java.util.Collections.emptyList();
    private void ensureStudentIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        student_ = new java.util.ArrayList<com.dahuatech.grpc.generate.Student>(student_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.dahuatech.grpc.generate.Student, com.dahuatech.grpc.generate.Student.Builder, com.dahuatech.grpc.generate.StudentOrBuilder> studentBuilder_;

    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public java.util.List<com.dahuatech.grpc.generate.Student> getStudentList() {
      if (studentBuilder_ == null) {
        return java.util.Collections.unmodifiableList(student_);
      } else {
        return studentBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public int getStudentCount() {
      if (studentBuilder_ == null) {
        return student_.size();
      } else {
        return studentBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public com.dahuatech.grpc.generate.Student getStudent(int index) {
      if (studentBuilder_ == null) {
        return student_.get(index);
      } else {
        return studentBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder setStudent(
        int index, com.dahuatech.grpc.generate.Student value) {
      if (studentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStudentIsMutable();
        student_.set(index, value);
        onChanged();
      } else {
        studentBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder setStudent(
        int index, com.dahuatech.grpc.generate.Student.Builder builderForValue) {
      if (studentBuilder_ == null) {
        ensureStudentIsMutable();
        student_.set(index, builderForValue.build());
        onChanged();
      } else {
        studentBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder addStudent(com.dahuatech.grpc.generate.Student value) {
      if (studentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStudentIsMutable();
        student_.add(value);
        onChanged();
      } else {
        studentBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder addStudent(
        int index, com.dahuatech.grpc.generate.Student value) {
      if (studentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStudentIsMutable();
        student_.add(index, value);
        onChanged();
      } else {
        studentBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder addStudent(
        com.dahuatech.grpc.generate.Student.Builder builderForValue) {
      if (studentBuilder_ == null) {
        ensureStudentIsMutable();
        student_.add(builderForValue.build());
        onChanged();
      } else {
        studentBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder addStudent(
        int index, com.dahuatech.grpc.generate.Student.Builder builderForValue) {
      if (studentBuilder_ == null) {
        ensureStudentIsMutable();
        student_.add(index, builderForValue.build());
        onChanged();
      } else {
        studentBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder addAllStudent(
        java.lang.Iterable<? extends com.dahuatech.grpc.generate.Student> values) {
      if (studentBuilder_ == null) {
        ensureStudentIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, student_);
        onChanged();
      } else {
        studentBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder clearStudent() {
      if (studentBuilder_ == null) {
        student_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        studentBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public Builder removeStudent(int index) {
      if (studentBuilder_ == null) {
        ensureStudentIsMutable();
        student_.remove(index);
        onChanged();
      } else {
        studentBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public com.dahuatech.grpc.generate.Student.Builder getStudentBuilder(
        int index) {
      return getStudentFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public com.dahuatech.grpc.generate.StudentOrBuilder getStudentOrBuilder(
        int index) {
      if (studentBuilder_ == null) {
        return student_.get(index);  } else {
        return studentBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public java.util.List<? extends com.dahuatech.grpc.generate.StudentOrBuilder> 
         getStudentOrBuilderList() {
      if (studentBuilder_ != null) {
        return studentBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(student_);
      }
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public com.dahuatech.grpc.generate.Student.Builder addStudentBuilder() {
      return getStudentFieldBuilder().addBuilder(
          com.dahuatech.grpc.generate.Student.getDefaultInstance());
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public com.dahuatech.grpc.generate.Student.Builder addStudentBuilder(
        int index) {
      return getStudentFieldBuilder().addBuilder(
          index, com.dahuatech.grpc.generate.Student.getDefaultInstance());
    }
    /**
     * <code>repeated .com.dahuatech.grpc.Student student = 1;</code>
     */
    public java.util.List<com.dahuatech.grpc.generate.Student.Builder> 
         getStudentBuilderList() {
      return getStudentFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.dahuatech.grpc.generate.Student, com.dahuatech.grpc.generate.Student.Builder, com.dahuatech.grpc.generate.StudentOrBuilder> 
        getStudentFieldBuilder() {
      if (studentBuilder_ == null) {
        studentBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.dahuatech.grpc.generate.Student, com.dahuatech.grpc.generate.Student.Builder, com.dahuatech.grpc.generate.StudentOrBuilder>(
                student_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        student_ = null;
      }
      return studentBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.dahuatech.grpc.Response)
  }

  // @@protoc_insertion_point(class_scope:com.dahuatech.grpc.Response)
  private static final com.dahuatech.grpc.generate.Response DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.dahuatech.grpc.generate.Response();
  }

  public static com.dahuatech.grpc.generate.Response getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Response>
      PARSER = new com.google.protobuf.AbstractParser<Response>() {
    public Response parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Response> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Response> getParserForType() {
    return PARSER;
  }

  public com.dahuatech.grpc.generate.Response getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

