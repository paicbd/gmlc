/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_ReferenceTimeSupport extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-ReferenceTimeSupport";
   }

   protected GNSS_ID_Bitmap gnss_SystemTime;
   protected AccessTypes fta_Support;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_ReferenceTimeSupport () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_ReferenceTimeSupport (
      GNSS_ID_Bitmap gnss_SystemTime_,
      AccessTypes fta_Support_
   ) {
      super();
      setGnss_SystemTime (gnss_SystemTime_);
      setFta_Support (fta_Support_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_ReferenceTimeSupport (
      GNSS_ID_Bitmap gnss_SystemTime_
   ) {
      super();
      setGnss_SystemTime (gnss_SystemTime_);
   }

   public void init () {
      gnss_SystemTime = null;
      fta_Support = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_ReferenceTimeSupport) ) return false;

      GNSS_ReferenceTimeSupport rhs = (GNSS_ReferenceTimeSupport) obj;

      if (gnss_SystemTime == null) {
         if (rhs.gnss_SystemTime != null) return false;
      }
      else {
         if (!gnss_SystemTime.equals(rhs.gnss_SystemTime)) {
            return false;
         }
      }

      if (fta_Support == null) {
         if (rhs.fta_Support != null) return false;
      }
      else {
         if (!fta_Support.equals(rhs.fta_Support)) {
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

      if (gnss_SystemTime != null) __code = 31*__code + gnss_SystemTime.hashCode();
      if (fta_Support != null) __code = 31*__code + fta_Support.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Gnss_SystemTime
    */
   public GNSS_ID_Bitmap getGnss_SystemTime () {
      return gnss_SystemTime;
   }

   public void setGnss_SystemTime (GNSS_ID_Bitmap value) {
      this.gnss_SystemTime = value;
   }

   /**
    * Accessor/mutator methods for Fta_Support
    */
   public AccessTypes getFta_Support () {
      return fta_Support;
   }

   public void setFta_Support (AccessTypes value) {
      this.fta_Support = value;
   }

   public boolean hasFta_Support () {
      return (fta_Support != null);
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
         case 0: return gnss_SystemTime;
         case 1: return fta_Support;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "gnss-SystemTime";
         case 1: return "fta-Support";
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

      boolean fta_SupportPresent = buffer.decodeBit ("fta_SupportPresent");

      // decode gnss_SystemTime

      buffer.getContext().eventDispatcher.startElement("gnss_SystemTime", -1);

      gnss_SystemTime = new GNSS_ID_Bitmap();
      gnss_SystemTime.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("gnss_SystemTime", -1);

      // decode fta_Support

      if (fta_SupportPresent) {
         buffer.getContext().eventDispatcher.startElement("fta_Support", -1);

         fta_Support = new AccessTypes();
         fta_Support.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("fta_Support", -1);
      }
      else {
         fta_Support = null;
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

      buffer.encodeBit ((fta_Support != null), null);

      // encode gnss_SystemTime

      if (gnss_SystemTime != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_SystemTime", -1);

         gnss_SystemTime.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("gnss_SystemTime", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_SystemTime");

      // encode fta_Support

      if (fta_Support != null) {
         buffer.getContext().eventDispatcher.startElement("fta_Support", -1);

         fta_Support.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("fta_Support", -1);
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
