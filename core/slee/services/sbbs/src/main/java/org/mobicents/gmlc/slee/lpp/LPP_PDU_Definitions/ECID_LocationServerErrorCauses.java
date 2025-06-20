/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class ECID_LocationServerErrorCauses extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ECID-LocationServerErrorCauses";
   }

   protected ECID_LocationServerErrorCauses_cause cause = null;
   protected Asn1OpenExt extElem1;

   public ECID_LocationServerErrorCauses () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public ECID_LocationServerErrorCauses (
      ECID_LocationServerErrorCauses_cause cause_
   ) {
      super();
      setCause (cause_);
   }

   public void init () {
      cause = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof ECID_LocationServerErrorCauses) ) return false;

      ECID_LocationServerErrorCauses rhs = (ECID_LocationServerErrorCauses) obj;

      if (cause == null) {
         if (rhs.cause != null) return false;
      }
      else {
         if (!cause.equals(rhs.cause)) {
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

      if (cause != null) __code = 31*__code + cause.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Cause
    */
   public ECID_LocationServerErrorCauses_cause getCause () {
      return cause;
   }

   public void setCause (ECID_LocationServerErrorCauses_cause value) {
      this.cause = value;
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
         case 0: return cause;
         case 1: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "cause";
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

      // decode cause

      buffer.getContext().eventDispatcher.startElement("cause", -1);

      {
         int tval = ECID_LocationServerErrorCauses_cause.decodeEnumValue (buffer);
         cause = ECID_LocationServerErrorCauses_cause.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("cause", -1);

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

      // encode cause

      if (cause != null) {
         buffer.getContext().eventDispatcher.startElement("cause", -1);

         cause.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("cause", -1);
      }
      else throw new Asn1MissingRequiredException ("cause");

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
