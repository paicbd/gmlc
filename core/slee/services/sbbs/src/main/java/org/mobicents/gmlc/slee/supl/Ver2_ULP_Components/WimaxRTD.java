/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class WimaxRTD extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "WimaxRTD";
   }

   protected Asn1Integer rtd;
   protected Asn1Integer rTDstd;  // optional
   protected Asn1OpenExt extElem1;

   public WimaxRTD () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public WimaxRTD (
      Asn1Integer rtd_,
      Asn1Integer rTDstd_
   ) throws Asn1Exception {
      super();
      setRtd (rtd_);
      setRTDstd (rTDstd_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public WimaxRTD (
      Asn1Integer rtd_
   ) throws Asn1Exception {
      super();
      setRtd (rtd_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public WimaxRTD (long rtd_,
      long rTDstd_
   ) throws Asn1Exception {
      super();
      setRtd (rtd_);
      setRTDstd (rTDstd_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public WimaxRTD (
      long rtd_
   ) throws Asn1Exception {
      super();
      setRtd (rtd_);
   }

   public void init () {
      rtd = null;
      rTDstd = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof WimaxRTD) ) return false;

      WimaxRTD rhs = (WimaxRTD) obj;

      if (rtd == null) {
         if (rhs.rtd != null) return false;
      }
      else {
         if (!rtd.equals(rhs.rtd)) {
            return false;
         }
      }

      if (rTDstd == null) {
         if (rhs.rTDstd != null) return false;
      }
      else {
         if (!rTDstd.equals(rhs.rTDstd)) {
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

      if (rtd != null) __code = 31*__code + rtd.hashCode();
      if (rTDstd != null) __code = 31*__code + rTDstd.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Rtd
    */
   public Asn1Integer getRtd () {
      return rtd;
   }

   public void setRtd (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 65535))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.rtd = value;
   }

   public void setRtd (long value) 
      throws Asn1Exception
   {
      setRtd (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for RTDstd
    */
   public Asn1Integer getRTDstd () {
      return rTDstd;
   }

   public void setRTDstd (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.rTDstd = value;
   }

   public void setRTDstd (long value) 
      throws Asn1Exception
   {
      setRTDstd (new Asn1Integer(value));
   }
   public boolean hasRTDstd () {
      return (rTDstd != null);
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
         case 0: return rtd;
         case 1: return rTDstd;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "rtd";
         case 1: return "rTDstd";
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

      boolean rTDstdPresent = buffer.decodeBit ("rTDstdPresent");

      // decode rtd

      buffer.getContext().eventDispatcher.startElement("rtd", -1);

      rtd = new Asn1Integer();
      rtd.decode (buffer, 0, 65535);
      buffer.getContext().eventDispatcher.endElement("rtd", -1);

      // decode rTDstd

      if (rTDstdPresent) {
         buffer.getContext().eventDispatcher.startElement("rTDstd", -1);

         rTDstd = new Asn1Integer();
         rTDstd.decode (buffer, 0, 1023);
         buffer.getContext().eventDispatcher.endElement("rTDstd", -1);
      }
      else {
         rTDstd = null;
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

      buffer.encodeBit ((rTDstd != null), null);

      // encode rtd

      if (rtd != null) {
         buffer.getContext().eventDispatcher.startElement("rtd", -1);

         rtd.encode (buffer, 0, 65535);

         buffer.getContext().eventDispatcher.endElement("rtd", -1);
      }
      else throw new Asn1MissingRequiredException ("rtd");

      // encode rTDstd

      if (rTDstd != null) {
         buffer.getContext().eventDispatcher.startElement("rTDstd", -1);

         rTDstd.encode (buffer, 0, 1023);

         buffer.getContext().eventDispatcher.endElement("rTDstd", -1);
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
