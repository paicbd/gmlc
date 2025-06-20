/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class MBS_AlmanacAssistance_r14 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "MBS-AlmanacAssistance-r14";
   }

   protected Asn1Integer transmitterID_r14;
   protected Asn1BitString transmitterLatitude_r14;
   protected Asn1BitString transmitterLongitude_r14;
   protected Asn1BitString transmitterAltitude_r14;
   protected Asn1Integer timeCorrection_r14;  // optional
   protected Asn1OpenExt extElem1;

   public MBS_AlmanacAssistance_r14 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public MBS_AlmanacAssistance_r14 (
      Asn1Integer transmitterID_r14_,
      Asn1BitString transmitterLatitude_r14_,
      Asn1BitString transmitterLongitude_r14_,
      Asn1BitString transmitterAltitude_r14_,
      Asn1Integer timeCorrection_r14_
   ) throws Asn1Exception {
      super();
      setTransmitterID_r14 (transmitterID_r14_);
      setTransmitterLatitude_r14 (transmitterLatitude_r14_);
      setTransmitterLongitude_r14 (transmitterLongitude_r14_);
      setTransmitterAltitude_r14 (transmitterAltitude_r14_);
      setTimeCorrection_r14 (timeCorrection_r14_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public MBS_AlmanacAssistance_r14 (
      Asn1Integer transmitterID_r14_,
      Asn1BitString transmitterLatitude_r14_,
      Asn1BitString transmitterLongitude_r14_,
      Asn1BitString transmitterAltitude_r14_
   ) throws Asn1Exception {
      super();
      setTransmitterID_r14 (transmitterID_r14_);
      setTransmitterLatitude_r14 (transmitterLatitude_r14_);
      setTransmitterLongitude_r14 (transmitterLongitude_r14_);
      setTransmitterAltitude_r14 (transmitterAltitude_r14_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public MBS_AlmanacAssistance_r14 (long transmitterID_r14_,
      Asn1BitString transmitterLatitude_r14_,
      Asn1BitString transmitterLongitude_r14_,
      Asn1BitString transmitterAltitude_r14_,
      long timeCorrection_r14_
   ) throws Asn1Exception {
      super();
      setTransmitterID_r14 (transmitterID_r14_);
      setTransmitterLatitude_r14 (transmitterLatitude_r14_);
      setTransmitterLongitude_r14 (transmitterLongitude_r14_);
      setTransmitterAltitude_r14 (transmitterAltitude_r14_);
      setTimeCorrection_r14 (timeCorrection_r14_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public MBS_AlmanacAssistance_r14 (
      long transmitterID_r14_,
      Asn1BitString transmitterLatitude_r14_,
      Asn1BitString transmitterLongitude_r14_,
      Asn1BitString transmitterAltitude_r14_
   ) throws Asn1Exception {
      super();
      setTransmitterID_r14 (transmitterID_r14_);
      setTransmitterLatitude_r14 (transmitterLatitude_r14_);
      setTransmitterLongitude_r14 (transmitterLongitude_r14_);
      setTransmitterAltitude_r14 (transmitterAltitude_r14_);
   }

   public void init () {
      transmitterID_r14 = null;
      transmitterLatitude_r14 = null;
      transmitterLongitude_r14 = null;
      transmitterAltitude_r14 = null;
      timeCorrection_r14 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof MBS_AlmanacAssistance_r14) ) return false;

      MBS_AlmanacAssistance_r14 rhs = (MBS_AlmanacAssistance_r14) obj;

      if (transmitterID_r14 == null) {
         if (rhs.transmitterID_r14 != null) return false;
      }
      else {
         if (!transmitterID_r14.equals(rhs.transmitterID_r14)) {
            return false;
         }
      }

      if (transmitterLatitude_r14 == null) {
         if (rhs.transmitterLatitude_r14 != null) return false;
      }
      else {
         if (!transmitterLatitude_r14.equals(rhs.transmitterLatitude_r14)) {
            return false;
         }
      }

      if (transmitterLongitude_r14 == null) {
         if (rhs.transmitterLongitude_r14 != null) return false;
      }
      else {
         if (!transmitterLongitude_r14.equals(rhs.transmitterLongitude_r14)) {
            return false;
         }
      }

      if (transmitterAltitude_r14 == null) {
         if (rhs.transmitterAltitude_r14 != null) return false;
      }
      else {
         if (!transmitterAltitude_r14.equals(rhs.transmitterAltitude_r14)) {
            return false;
         }
      }

      if (timeCorrection_r14 == null) {
         if (rhs.timeCorrection_r14 != null) return false;
      }
      else {
         if (!timeCorrection_r14.equals(rhs.timeCorrection_r14)) {
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

      if (transmitterID_r14 != null) __code = 31*__code + transmitterID_r14.hashCode();
      if (transmitterLatitude_r14 != null) __code = 31*__code + transmitterLatitude_r14.hashCode();
      if (transmitterLongitude_r14 != null) __code = 31*__code + transmitterLongitude_r14.hashCode();
      if (transmitterAltitude_r14 != null) __code = 31*__code + transmitterAltitude_r14.hashCode();
      if (timeCorrection_r14 != null) __code = 31*__code + timeCorrection_r14.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for TransmitterID_r14
    */
   public Asn1Integer getTransmitterID_r14 () {
      return transmitterID_r14;
   }

   public void setTransmitterID_r14 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.transmitterID_r14 = value;
   }

   public void setTransmitterID_r14 (long value) 
      throws Asn1Exception
   {
      setTransmitterID_r14 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for TransmitterLatitude_r14
    */
   public Asn1BitString getTransmitterLatitude_r14 () {
      return transmitterLatitude_r14;
   }

   public void setTransmitterLatitude_r14 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 26)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.transmitterLatitude_r14 = value;
   }

   /**
    * Accessor/mutator methods for TransmitterLongitude_r14
    */
   public Asn1BitString getTransmitterLongitude_r14 () {
      return transmitterLongitude_r14;
   }

   public void setTransmitterLongitude_r14 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 27)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.transmitterLongitude_r14 = value;
   }

   /**
    * Accessor/mutator methods for TransmitterAltitude_r14
    */
   public Asn1BitString getTransmitterAltitude_r14 () {
      return transmitterAltitude_r14;
   }

   public void setTransmitterAltitude_r14 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 15)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.transmitterAltitude_r14 = value;
   }

   /**
    * Accessor/mutator methods for TimeCorrection_r14
    */
   public Asn1Integer getTimeCorrection_r14 () {
      return timeCorrection_r14;
   }

   public void setTimeCorrection_r14 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 25))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.timeCorrection_r14 = value;
   }

   public void setTimeCorrection_r14 (long value) 
      throws Asn1Exception
   {
      setTimeCorrection_r14 (new Asn1Integer(value));
   }
   public boolean hasTimeCorrection_r14 () {
      return (timeCorrection_r14 != null);
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

   public int getElementCount() { return 6; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return transmitterID_r14;
         case 1: return transmitterLatitude_r14;
         case 2: return transmitterLongitude_r14;
         case 3: return transmitterAltitude_r14;
         case 4: return timeCorrection_r14;
         case 5: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "transmitterID-r14";
         case 1: return "transmitterLatitude-r14";
         case 2: return "transmitterLongitude-r14";
         case 3: return "transmitterAltitude-r14";
         case 4: return "timeCorrection-r14";
         case 5: return null;
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

      boolean timeCorrection_r14Present = buffer.decodeBit ("timeCorrection_r14Present");

      // decode transmitterID_r14

      buffer.getContext().eventDispatcher.startElement("transmitterID_r14", -1);

      transmitterID_r14 = new Asn1Integer();
      transmitterID_r14.decode (buffer, 0, 32767);
      buffer.getContext().eventDispatcher.endElement("transmitterID_r14", -1);

      // decode transmitterLatitude_r14

      buffer.getContext().eventDispatcher.startElement("transmitterLatitude_r14", -1);

      transmitterLatitude_r14 = new Asn1BitString();
      transmitterLatitude_r14.decode (buffer, 26, 26);
      buffer.getContext().eventDispatcher.endElement("transmitterLatitude_r14", -1);

      // decode transmitterLongitude_r14

      buffer.getContext().eventDispatcher.startElement("transmitterLongitude_r14", -1);

      transmitterLongitude_r14 = new Asn1BitString();
      transmitterLongitude_r14.decode (buffer, 27, 27);
      buffer.getContext().eventDispatcher.endElement("transmitterLongitude_r14", -1);

      // decode transmitterAltitude_r14

      buffer.getContext().eventDispatcher.startElement("transmitterAltitude_r14", -1);

      transmitterAltitude_r14 = new Asn1BitString();
      transmitterAltitude_r14.decode (buffer, 15, 15);
      buffer.getContext().eventDispatcher.endElement("transmitterAltitude_r14", -1);

      // decode timeCorrection_r14

      if (timeCorrection_r14Present) {
         buffer.getContext().eventDispatcher.startElement("timeCorrection_r14", -1);

         timeCorrection_r14 = new Asn1Integer();
         timeCorrection_r14.decode (buffer, 0, 25);
         buffer.getContext().eventDispatcher.endElement("timeCorrection_r14", -1);
      }
      else {
         timeCorrection_r14 = null;
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

      buffer.encodeBit ((timeCorrection_r14 != null), null);

      // encode transmitterID_r14

      if (transmitterID_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("transmitterID_r14", -1);

         transmitterID_r14.encode (buffer, 0, 32767);

         buffer.getContext().eventDispatcher.endElement("transmitterID_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("transmitterID_r14");

      // encode transmitterLatitude_r14

      if (transmitterLatitude_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("transmitterLatitude_r14", -1);

         transmitterLatitude_r14.encode (buffer, 26, 26);

         buffer.getContext().eventDispatcher.endElement("transmitterLatitude_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("transmitterLatitude_r14");

      // encode transmitterLongitude_r14

      if (transmitterLongitude_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("transmitterLongitude_r14", -1);

         transmitterLongitude_r14.encode (buffer, 27, 27);

         buffer.getContext().eventDispatcher.endElement("transmitterLongitude_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("transmitterLongitude_r14");

      // encode transmitterAltitude_r14

      if (transmitterAltitude_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("transmitterAltitude_r14", -1);

         transmitterAltitude_r14.encode (buffer, 15, 15);

         buffer.getContext().eventDispatcher.endElement("transmitterAltitude_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("transmitterAltitude_r14");

      // encode timeCorrection_r14

      if (timeCorrection_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("timeCorrection_r14", -1);

         timeCorrection_r14.encode (buffer, 0, 25);

         buffer.getContext().eventDispatcher.endElement("timeCorrection_r14", -1);
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
