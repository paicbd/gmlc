/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class WLAN_LocationServerErrorCauses_r13 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "WLAN-LocationServerErrorCauses-r13";
   }

   protected WLAN_LocationServerErrorCauses_r13_cause_r13 cause_r13 = null;
   protected Asn1Null apLocationDataUnavailable_r14;  // optional
   protected Asn1OpenExt extElem1;

   public WLAN_LocationServerErrorCauses_r13 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public WLAN_LocationServerErrorCauses_r13 (
      WLAN_LocationServerErrorCauses_r13_cause_r13 cause_r13_,
      Asn1Null apLocationDataUnavailable_r14_
   ) {
      super();
      setCause_r13 (cause_r13_);
      setApLocationDataUnavailable_r14 (apLocationDataUnavailable_r14_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public WLAN_LocationServerErrorCauses_r13 (
      WLAN_LocationServerErrorCauses_r13_cause_r13 cause_r13_
   ) {
      super();
      setCause_r13 (cause_r13_);
   }

   public void init () {
      cause_r13 = null;
      apLocationDataUnavailable_r14 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof WLAN_LocationServerErrorCauses_r13) ) return false;

      WLAN_LocationServerErrorCauses_r13 rhs = (WLAN_LocationServerErrorCauses_r13) obj;

      if (cause_r13 == null) {
         if (rhs.cause_r13 != null) return false;
      }
      else {
         if (!cause_r13.equals(rhs.cause_r13)) {
            return false;
         }
      }

      if (apLocationDataUnavailable_r14 == null) {
         if (rhs.apLocationDataUnavailable_r14 != null) return false;
      }
      else {
         if (!apLocationDataUnavailable_r14.equals(rhs.apLocationDataUnavailable_r14)) {
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

      if (cause_r13 != null) __code = 31*__code + cause_r13.hashCode();
      if (apLocationDataUnavailable_r14 != null) __code = 31*__code + apLocationDataUnavailable_r14.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Cause_r13
    */
   public WLAN_LocationServerErrorCauses_r13_cause_r13 getCause_r13 () {
      return cause_r13;
   }

   public void setCause_r13 (WLAN_LocationServerErrorCauses_r13_cause_r13 value) {
      this.cause_r13 = value;
   }

   /**
    * Accessor/mutator methods for ApLocationDataUnavailable_r14
    */
   public Asn1Null getApLocationDataUnavailable_r14 () {
      return apLocationDataUnavailable_r14;
   }

   public void setApLocationDataUnavailable_r14 (Asn1Null value) {
      this.apLocationDataUnavailable_r14 = value;
   }

   public boolean hasApLocationDataUnavailable_r14 () {
      return (apLocationDataUnavailable_r14 != null);
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
         case 0: return cause_r13;
         case 1: return apLocationDataUnavailable_r14;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "cause-r13";
         case 1: return "apLocationDataUnavailable-r14";
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

      // decode cause_r13

      buffer.getContext().eventDispatcher.startElement("cause_r13", -1);

      {
         int tval = WLAN_LocationServerErrorCauses_r13_cause_r13.decodeEnumValue (buffer);
         cause_r13 = WLAN_LocationServerErrorCauses_r13_cause_r13.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("cause_r13", -1);

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("apLocationDataUnavailable_r14Present");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode apLocationDataUnavailable_r14

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("apLocationDataUnavailable_r14", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            apLocationDataUnavailable_r14 = new Asn1Null();
            apLocationDataUnavailable_r14.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("apLocationDataUnavailable_r14", -1);

         }
         else {
            apLocationDataUnavailable_r14 = null;
         }

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

      boolean extbit = ((apLocationDataUnavailable_r14 != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode cause_r13

      if (cause_r13 != null) {
         buffer.getContext().eventDispatcher.startElement("cause_r13", -1);

         cause_r13.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("cause_r13", -1);
      }
      else throw new Asn1MissingRequiredException ("cause_r13");

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 1;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((apLocationDataUnavailable_r14 != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // apLocationDataUnavailable_r14

         if (apLocationDataUnavailable_r14 != null) {
            buffer.reset();
            apLocationDataUnavailable_r14.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("apLocationDataUnavailable_r14", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("apLocationDataUnavailable_r14", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
