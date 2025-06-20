/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_PeriodicControlParam_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-PeriodicControlParam-r15";
   }

   protected Asn1Integer deliveryAmount_r15;
   protected Asn1Integer deliveryInterval_r15;
   protected Asn1OpenExt extElem1;

   public GNSS_PeriodicControlParam_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_PeriodicControlParam_r15 (
      Asn1Integer deliveryAmount_r15_,
      Asn1Integer deliveryInterval_r15_
   ) throws Asn1Exception {
      super();
      setDeliveryAmount_r15 (deliveryAmount_r15_);
      setDeliveryInterval_r15 (deliveryInterval_r15_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_PeriodicControlParam_r15 (long deliveryAmount_r15_,
      long deliveryInterval_r15_
   ) throws Asn1Exception {
      super();
      setDeliveryAmount_r15 (deliveryAmount_r15_);
      setDeliveryInterval_r15 (deliveryInterval_r15_);
   }

   public void init () {
      deliveryAmount_r15 = null;
      deliveryInterval_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_PeriodicControlParam_r15) ) return false;

      GNSS_PeriodicControlParam_r15 rhs = (GNSS_PeriodicControlParam_r15) obj;

      if (deliveryAmount_r15 == null) {
         if (rhs.deliveryAmount_r15 != null) return false;
      }
      else {
         if (!deliveryAmount_r15.equals(rhs.deliveryAmount_r15)) {
            return false;
         }
      }

      if (deliveryInterval_r15 == null) {
         if (rhs.deliveryInterval_r15 != null) return false;
      }
      else {
         if (!deliveryInterval_r15.equals(rhs.deliveryInterval_r15)) {
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

      if (deliveryAmount_r15 != null) __code = 31*__code + deliveryAmount_r15.hashCode();
      if (deliveryInterval_r15 != null) __code = 31*__code + deliveryInterval_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for DeliveryAmount_r15
    */
   public Asn1Integer getDeliveryAmount_r15 () {
      return deliveryAmount_r15;
   }

   public void setDeliveryAmount_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 32))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.deliveryAmount_r15 = value;
   }

   public void setDeliveryAmount_r15 (long value) 
      throws Asn1Exception
   {
      setDeliveryAmount_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for DeliveryInterval_r15
    */
   public Asn1Integer getDeliveryInterval_r15 () {
      return deliveryInterval_r15;
   }

   public void setDeliveryInterval_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 64))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.deliveryInterval_r15 = value;
   }

   public void setDeliveryInterval_r15 (long value) 
      throws Asn1Exception
   {
      setDeliveryInterval_r15 (new Asn1Integer(value));
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
         case 0: return deliveryAmount_r15;
         case 1: return deliveryInterval_r15;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "deliveryAmount-r15";
         case 1: return "deliveryInterval-r15";
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

      // decode deliveryAmount_r15

      buffer.getContext().eventDispatcher.startElement("deliveryAmount_r15", -1);

      deliveryAmount_r15 = new Asn1Integer();
      deliveryAmount_r15.decode (buffer, 1, 32);
      buffer.getContext().eventDispatcher.endElement("deliveryAmount_r15", -1);

      // decode deliveryInterval_r15

      buffer.getContext().eventDispatcher.startElement("deliveryInterval_r15", -1);

      deliveryInterval_r15 = new Asn1Integer();
      deliveryInterval_r15.decode (buffer, 1, 64);
      buffer.getContext().eventDispatcher.endElement("deliveryInterval_r15", -1);

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

      // encode deliveryAmount_r15

      if (deliveryAmount_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("deliveryAmount_r15", -1);

         deliveryAmount_r15.encode (buffer, 1, 32);

         buffer.getContext().eventDispatcher.endElement("deliveryAmount_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("deliveryAmount_r15");

      // encode deliveryInterval_r15

      if (deliveryInterval_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("deliveryInterval_r15", -1);

         deliveryInterval_r15.encode (buffer, 1, 64);

         buffer.getContext().eventDispatcher.endElement("deliveryInterval_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("deliveryInterval_r15");

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
