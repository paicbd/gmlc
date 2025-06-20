/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class DisplacementInfoListElement_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "DisplacementInfoListElement-r15";
   }

   protected DeltaTime_r15 deltaTimeStamp_r15;
   protected Displacement_r15 displacement_r15;  // optional
   protected Asn1OpenExt extElem1;

   public DisplacementInfoListElement_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public DisplacementInfoListElement_r15 (
      DeltaTime_r15 deltaTimeStamp_r15_,
      Displacement_r15 displacement_r15_
   ) {
      super();
      setDeltaTimeStamp_r15 (deltaTimeStamp_r15_);
      setDisplacement_r15 (displacement_r15_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public DisplacementInfoListElement_r15 (
      DeltaTime_r15 deltaTimeStamp_r15_
   ) {
      super();
      setDeltaTimeStamp_r15 (deltaTimeStamp_r15_);
   }

   public void init () {
      deltaTimeStamp_r15 = null;
      displacement_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof DisplacementInfoListElement_r15) ) return false;

      DisplacementInfoListElement_r15 rhs = (DisplacementInfoListElement_r15) obj;

      if (deltaTimeStamp_r15 == null) {
         if (rhs.deltaTimeStamp_r15 != null) return false;
      }
      else {
         if (!deltaTimeStamp_r15.equals(rhs.deltaTimeStamp_r15)) {
            return false;
         }
      }

      if (displacement_r15 == null) {
         if (rhs.displacement_r15 != null) return false;
      }
      else {
         if (!displacement_r15.equals(rhs.displacement_r15)) {
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

      if (deltaTimeStamp_r15 != null) __code = 31*__code + deltaTimeStamp_r15.hashCode();
      if (displacement_r15 != null) __code = 31*__code + displacement_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for DeltaTimeStamp_r15
    */
   public DeltaTime_r15 getDeltaTimeStamp_r15 () {
      return deltaTimeStamp_r15;
   }

   public void setDeltaTimeStamp_r15 (DeltaTime_r15 value) {
      this.deltaTimeStamp_r15 = value;
   }

   /**
    * Accessor/mutator methods for Displacement_r15
    */
   public Displacement_r15 getDisplacement_r15 () {
      return displacement_r15;
   }

   public void setDisplacement_r15 (Displacement_r15 value) {
      this.displacement_r15 = value;
   }

   public boolean hasDisplacement_r15 () {
      return (displacement_r15 != null);
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
         case 0: return deltaTimeStamp_r15;
         case 1: return displacement_r15;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "deltaTimeStamp-r15";
         case 1: return "displacement-r15";
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

      boolean displacement_r15Present = buffer.decodeBit ("displacement_r15Present");

      // decode deltaTimeStamp_r15

      buffer.getContext().eventDispatcher.startElement("deltaTimeStamp_r15", -1);

      deltaTimeStamp_r15 = new DeltaTime_r15();
      deltaTimeStamp_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("deltaTimeStamp_r15", -1);

      // decode displacement_r15

      if (displacement_r15Present) {
         buffer.getContext().eventDispatcher.startElement("displacement_r15", -1);

         displacement_r15 = new Displacement_r15();
         displacement_r15.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("displacement_r15", -1);
      }
      else {
         displacement_r15 = null;
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

      buffer.encodeBit ((displacement_r15 != null), null);

      // encode deltaTimeStamp_r15

      if (deltaTimeStamp_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("deltaTimeStamp_r15", -1);

         deltaTimeStamp_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("deltaTimeStamp_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("deltaTimeStamp_r15");

      // encode displacement_r15

      if (displacement_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("displacement_r15", -1);

         displacement_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("displacement_r15", -1);
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
