/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class Sensor_AssistanceDataList_r14_period_v1520 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SEQUENCE";
   }

   protected PressureValidityPeriod_v1520 pressureValidityPeriod_v1520;
   protected Asn1Integer referencePressureRate_v1520;  // optional
   protected Asn1OpenExt extElem1;

   public Sensor_AssistanceDataList_r14_period_v1520 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public Sensor_AssistanceDataList_r14_period_v1520 (
      PressureValidityPeriod_v1520 pressureValidityPeriod_v1520_,
      Asn1Integer referencePressureRate_v1520_
   ) throws Asn1Exception {
      super();
      setPressureValidityPeriod_v1520 (pressureValidityPeriod_v1520_);
      setReferencePressureRate_v1520 (referencePressureRate_v1520_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public Sensor_AssistanceDataList_r14_period_v1520 (
      PressureValidityPeriod_v1520 pressureValidityPeriod_v1520_
   ) throws Asn1Exception {
      super();
      setPressureValidityPeriod_v1520 (pressureValidityPeriod_v1520_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public Sensor_AssistanceDataList_r14_period_v1520 (PressureValidityPeriod_v1520 pressureValidityPeriod_v1520_,
      long referencePressureRate_v1520_
   ) throws Asn1Exception {
      super();
      setPressureValidityPeriod_v1520 (pressureValidityPeriod_v1520_);
      setReferencePressureRate_v1520 (referencePressureRate_v1520_);
   }

   public void init () {
      pressureValidityPeriod_v1520 = null;
      referencePressureRate_v1520 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof Sensor_AssistanceDataList_r14_period_v1520) ) return false;

      Sensor_AssistanceDataList_r14_period_v1520 rhs = (Sensor_AssistanceDataList_r14_period_v1520) obj;

      if (pressureValidityPeriod_v1520 == null) {
         if (rhs.pressureValidityPeriod_v1520 != null) return false;
      }
      else {
         if (!pressureValidityPeriod_v1520.equals(rhs.pressureValidityPeriod_v1520)) {
            return false;
         }
      }

      if (referencePressureRate_v1520 == null) {
         if (rhs.referencePressureRate_v1520 != null) return false;
      }
      else {
         if (!referencePressureRate_v1520.equals(rhs.referencePressureRate_v1520)) {
            return false;
         }
      }

      if (extElem1 == null) {
         if (rhs.extElem1 != null) return false;
      }
      else {
         if (!extElem1.equals(rhs.extElem1)) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      int __code = 1;

      if (pressureValidityPeriod_v1520 != null) __code = 31*__code + pressureValidityPeriod_v1520.hashCode();
      if (referencePressureRate_v1520 != null) __code = 31*__code + referencePressureRate_v1520.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for PressureValidityPeriod_v1520
    */
   public PressureValidityPeriod_v1520 getPressureValidityPeriod_v1520 () {
      return pressureValidityPeriod_v1520;
   }

   public void setPressureValidityPeriod_v1520 (PressureValidityPeriod_v1520 value) {
      this.pressureValidityPeriod_v1520 = value;
   }

   /**
    * Accessor/mutator methods for ReferencePressureRate_v1520
    */
   public Asn1Integer getReferencePressureRate_v1520 () {
      return referencePressureRate_v1520;
   }

   public void setReferencePressureRate_v1520 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -128 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.referencePressureRate_v1520 = value;
   }

   public void setReferencePressureRate_v1520 (long value) 
      throws Asn1Exception
   {
      setReferencePressureRate_v1520 (new Asn1Integer(value));
   }
   public boolean hasReferencePressureRate_v1520 () {
      return (referencePressureRate_v1520 != null);
   }

   /**
    * Accessor/mutator methods for ExtElem1
    */
   public Asn1OpenExt getExtElem1 () {
      return extElem1;
   }

   public void setExtElem1 (Asn1OpenExt value) {
      this.extElem1 = value;
   }

   public int getElementCount() { return 3; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return pressureValidityPeriod_v1520;
         case 1: return referencePressureRate_v1520;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "pressureValidityPeriod-v1520";
         case 1: return "referencePressureRate-v1520";
         case 2: return null;
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // extension bit

      boolean extbit = buffer.decodeBit ("extbit");

      // optional bits

      boolean referencePressureRate_v1520Present = buffer.decodeBit ("referencePressureRate_v1520Present");

      // decode pressureValidityPeriod_v1520

      buffer.getContext().eventDispatcher.startElement("pressureValidityPeriod_v1520", -1);

      pressureValidityPeriod_v1520 = new PressureValidityPeriod_v1520();
      pressureValidityPeriod_v1520.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("pressureValidityPeriod_v1520", -1);

      // decode referencePressureRate_v1520

      if (referencePressureRate_v1520Present) {
         buffer.getContext().eventDispatcher.startElement("referencePressureRate_v1520", -1);

         referencePressureRate_v1520 = new Asn1Integer();
         referencePressureRate_v1520.decode (buffer, -128, 127);
         buffer.getContext().eventDispatcher.endElement("referencePressureRate_v1520", -1);
      }
      else {
         referencePressureRate_v1520 = null;
      }

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode unknown extension elements

         if (i < bitcnt) {
            Asn1OpenType openType = null;
            extElem1 = new Asn1OpenExt();
            int j = 0;
            while (i < bitcnt) {
               if (bitmap[i]) {
                  buffer.getContext().eventDispatcher.startElement("...", -1);

                  openType = extElem1.decodeOpenType (buffer, true, j++);
               }
               else {
                  extElem1.setOpenType (null, j++);
               }
               i++;
            }
         }
         buffer.byteAlign ();

      }

   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // extension bit

      boolean extbit = (((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode optional elements bit mask

      buffer.encodeBit ((referencePressureRate_v1520 != null), null);

      // encode pressureValidityPeriod_v1520

      if (pressureValidityPeriod_v1520 != null) {
         buffer.getContext().eventDispatcher.startElement("pressureValidityPeriod_v1520", -1);

         pressureValidityPeriod_v1520.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("pressureValidityPeriod_v1520", -1);
      }
      else throw new Asn1MissingRequiredException ("pressureValidityPeriod_v1520");

      // encode referencePressureRate_v1520

      if (referencePressureRate_v1520 != null) {
         buffer.getContext().eventDispatcher.startElement("referencePressureRate_v1520", -1);

         referencePressureRate_v1520.encode (buffer, -128, 127);

         buffer.getContext().eventDispatcher.endElement("referencePressureRate_v1520", -1);
      }

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 0;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
