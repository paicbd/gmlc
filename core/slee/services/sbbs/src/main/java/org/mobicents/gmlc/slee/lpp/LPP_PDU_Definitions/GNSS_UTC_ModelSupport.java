/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_UTC_ModelSupport extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-UTC-ModelSupport";
   }

   protected GNSS_UTC_ModelSupport_utc_Model utc_Model;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_UTC_ModelSupport () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_UTC_ModelSupport (
      GNSS_UTC_ModelSupport_utc_Model utc_Model_
   ) throws Asn1Exception {
      super();
      setUtc_Model (utc_Model_);
   }

   public void init () {
      utc_Model = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_UTC_ModelSupport) ) return false;

      GNSS_UTC_ModelSupport rhs = (GNSS_UTC_ModelSupport) obj;

      if (utc_Model == null) {
         if (rhs.utc_Model != null) return false;
      }
      else {
         if (!utc_Model.equals(rhs.utc_Model)) {
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

      if (utc_Model != null) __code = 31*__code + utc_Model.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Utc_Model
    */
   public GNSS_UTC_ModelSupport_utc_Model getUtc_Model () {
      return utc_Model;
   }

   public void setUtc_Model (GNSS_UTC_ModelSupport_utc_Model value) 
      throws Asn1Exception
   {
      if (!((value.getLength() >= 1 && value.getLength() <= 8))) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.utc_Model = value;
   }

   public boolean hasUtc_Model () {
      return (utc_Model != null);
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

   public int getElementCount() { return 2; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return utc_Model;
         case 1: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "utc-Model";
         case 1: return null;
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

      boolean utc_ModelPresent = buffer.decodeBit ("utc_ModelPresent");

      // decode utc_Model

      if (utc_ModelPresent) {
         buffer.getContext().eventDispatcher.startElement("utc_Model", -1);

         utc_Model = new GNSS_UTC_ModelSupport_utc_Model();
         utc_Model.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("utc_Model", -1);
      }
      else {
         utc_Model = null;
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

      buffer.encodeBit ((utc_Model != null), null);

      // encode utc_Model

      if (utc_Model != null) {
         buffer.getContext().eventDispatcher.startElement("utc_Model", -1);

         utc_Model.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("utc_Model", -1);
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
