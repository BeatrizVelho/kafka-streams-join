/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.marionete.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class VegetableOrder extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5078095201875122421L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"VegetableOrder\",\"namespace\":\"com.marionete.model\",\"fields\":[{\"name\":\"order_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"vegetable_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"user_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<VegetableOrder> ENCODER =
      new BinaryMessageEncoder<VegetableOrder>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<VegetableOrder> DECODER =
      new BinaryMessageDecoder<VegetableOrder>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<VegetableOrder> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<VegetableOrder> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<VegetableOrder> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<VegetableOrder>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this VegetableOrder to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a VegetableOrder from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a VegetableOrder instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static VegetableOrder fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String order_id;
  private java.lang.String vegetable_id;
  private java.lang.String user_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public VegetableOrder() {}

  /**
   * All-args constructor.
   * @param order_id The new value for order_id
   * @param vegetable_id The new value for vegetable_id
   * @param user_id The new value for user_id
   */
  public VegetableOrder(java.lang.String order_id, java.lang.String vegetable_id, java.lang.String user_id) {
    this.order_id = order_id;
    this.vegetable_id = vegetable_id;
    this.user_id = user_id;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return order_id;
    case 1: return vegetable_id;
    case 2: return user_id;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: order_id = value$ != null ? value$.toString() : null; break;
    case 1: vegetable_id = value$ != null ? value$.toString() : null; break;
    case 2: user_id = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'order_id' field.
   * @return The value of the 'order_id' field.
   */
  public java.lang.String getOrderId() {
    return order_id;
  }


  /**
   * Sets the value of the 'order_id' field.
   * @param value the value to set.
   */
  public void setOrderId(java.lang.String value) {
    this.order_id = value;
  }

  /**
   * Gets the value of the 'vegetable_id' field.
   * @return The value of the 'vegetable_id' field.
   */
  public java.lang.String getVegetableId() {
    return vegetable_id;
  }


  /**
   * Sets the value of the 'vegetable_id' field.
   * @param value the value to set.
   */
  public void setVegetableId(java.lang.String value) {
    this.vegetable_id = value;
  }

  /**
   * Gets the value of the 'user_id' field.
   * @return The value of the 'user_id' field.
   */
  public java.lang.String getUserId() {
    return user_id;
  }


  /**
   * Sets the value of the 'user_id' field.
   * @param value the value to set.
   */
  public void setUserId(java.lang.String value) {
    this.user_id = value;
  }

  /**
   * Creates a new VegetableOrder RecordBuilder.
   * @return A new VegetableOrder RecordBuilder
   */
  public static com.marionete.model.VegetableOrder.Builder newBuilder() {
    return new com.marionete.model.VegetableOrder.Builder();
  }

  /**
   * Creates a new VegetableOrder RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new VegetableOrder RecordBuilder
   */
  public static com.marionete.model.VegetableOrder.Builder newBuilder(com.marionete.model.VegetableOrder.Builder other) {
    if (other == null) {
      return new com.marionete.model.VegetableOrder.Builder();
    } else {
      return new com.marionete.model.VegetableOrder.Builder(other);
    }
  }

  /**
   * Creates a new VegetableOrder RecordBuilder by copying an existing VegetableOrder instance.
   * @param other The existing instance to copy.
   * @return A new VegetableOrder RecordBuilder
   */
  public static com.marionete.model.VegetableOrder.Builder newBuilder(com.marionete.model.VegetableOrder other) {
    if (other == null) {
      return new com.marionete.model.VegetableOrder.Builder();
    } else {
      return new com.marionete.model.VegetableOrder.Builder(other);
    }
  }

  /**
   * RecordBuilder for VegetableOrder instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<VegetableOrder>
    implements org.apache.avro.data.RecordBuilder<VegetableOrder> {

    private java.lang.String order_id;
    private java.lang.String vegetable_id;
    private java.lang.String user_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.marionete.model.VegetableOrder.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.order_id)) {
        this.order_id = data().deepCopy(fields()[0].schema(), other.order_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.vegetable_id)) {
        this.vegetable_id = data().deepCopy(fields()[1].schema(), other.vegetable_id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.user_id)) {
        this.user_id = data().deepCopy(fields()[2].schema(), other.user_id);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing VegetableOrder instance
     * @param other The existing instance to copy.
     */
    private Builder(com.marionete.model.VegetableOrder other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.order_id)) {
        this.order_id = data().deepCopy(fields()[0].schema(), other.order_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.vegetable_id)) {
        this.vegetable_id = data().deepCopy(fields()[1].schema(), other.vegetable_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.user_id)) {
        this.user_id = data().deepCopy(fields()[2].schema(), other.user_id);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'order_id' field.
      * @return The value.
      */
    public java.lang.String getOrderId() {
      return order_id;
    }


    /**
      * Sets the value of the 'order_id' field.
      * @param value The value of 'order_id'.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder setOrderId(java.lang.String value) {
      validate(fields()[0], value);
      this.order_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'order_id' field has been set.
      * @return True if the 'order_id' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'order_id' field.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder clearOrderId() {
      order_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'vegetable_id' field.
      * @return The value.
      */
    public java.lang.String getVegetableId() {
      return vegetable_id;
    }


    /**
      * Sets the value of the 'vegetable_id' field.
      * @param value The value of 'vegetable_id'.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder setVegetableId(java.lang.String value) {
      validate(fields()[1], value);
      this.vegetable_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'vegetable_id' field has been set.
      * @return True if the 'vegetable_id' field has been set, false otherwise.
      */
    public boolean hasVegetableId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'vegetable_id' field.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder clearVegetableId() {
      vegetable_id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'user_id' field.
      * @return The value.
      */
    public java.lang.String getUserId() {
      return user_id;
    }


    /**
      * Sets the value of the 'user_id' field.
      * @param value The value of 'user_id'.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder setUserId(java.lang.String value) {
      validate(fields()[2], value);
      this.user_id = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'user_id' field has been set.
      * @return True if the 'user_id' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'user_id' field.
      * @return This builder.
      */
    public com.marionete.model.VegetableOrder.Builder clearUserId() {
      user_id = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public VegetableOrder build() {
      try {
        VegetableOrder record = new VegetableOrder();
        record.order_id = fieldSetFlags()[0] ? this.order_id : (java.lang.String) defaultValue(fields()[0]);
        record.vegetable_id = fieldSetFlags()[1] ? this.vegetable_id : (java.lang.String) defaultValue(fields()[1]);
        record.user_id = fieldSetFlags()[2] ? this.user_id : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<VegetableOrder>
    WRITER$ = (org.apache.avro.io.DatumWriter<VegetableOrder>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<VegetableOrder>
    READER$ = (org.apache.avro.io.DatumReader<VegetableOrder>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.order_id);

    out.writeString(this.vegetable_id);

    out.writeString(this.user_id);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.order_id = in.readString();

      this.vegetable_id = in.readString();

      this.user_id = in.readString();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.order_id = in.readString();
          break;

        case 1:
          this.vegetable_id = in.readString();
          break;

        case 2:
          this.user_id = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










