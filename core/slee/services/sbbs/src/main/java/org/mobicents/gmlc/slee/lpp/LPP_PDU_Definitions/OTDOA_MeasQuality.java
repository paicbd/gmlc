/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class OTDOA_MeasQuality extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "OTDOA-MeasQuality";
   }

   protected Asn1BitString error_Resolution;
   protected Asn1BitString error_Value;
   protected Asn1BitString error_NumSamples;  // optional
   protected Asn1OpenExt extElem1;

   public OTDOA_MeasQuality () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public OTDOA_MeasQuality (
      Asn1BitString error_Resolution_,
      Asn1BitString error_Value_,
      Asn1BitString error_NumSamples_
   ) throws Asn1Exception {
      super();
      setError_Resolution (error_Resolution_);
      setError_Value (error_Value_);
      setError_NumSamples (error_NumSamples_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public OTDOA_MeasQuality (
      Asn1BitString error_Resolution_,
      Asn1BitString error_Value_
   ) throws Asn1Exception {
      super();
      setError_Resolution (error_Resolution_);
      setError_Value (error_Value_);
   }

   public void init () {
      error_Resolution = null;
      error_Value = null;
      error_NumSamples = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof OTDOA_MeasQuality) ) return false;

      OTDOA_MeasQuality rhs = (OTDOA_MeasQuality) obj;

      if (error_Resolution == null) {
         if (rhs.error_Resolution != null) return false;
      }
      else {
         if (!error_Resolution.equals(rhs.error_Resolution)) {
            return false;
         }
      }

      if (error_Value == null) {
         if (rhs.error_Value != null) return false;
      }
      else {
         if (!error_Value.equals(rhs.error_Value)) {
            return false;
         }
      }

      if (error_NumSamples == null) {
         if (rhs.error_NumSamples != null) return false;
      }
      else {
         if (!error_NumSamples.equals(rhs.error_NumSamples)) {
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

      if (error_Resolution != null) __code = 31*__code + error_Resolution.hashCode();
      if (error_Value != null) __code = 31*__code + error_Value.hashCode();
      if (error_NumSamples != null) __code = 31*__code + error_NumSamples.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Error_Resolution
    */
   public Asn1BitString getError_Resolution () {
      return error_Resolution;
   }

   public void setError_Resolution (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 2)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.error_Resolution = value;
   }

   /**
    * Accessor/mutator methods for Error_Value
    */
   public Asn1BitString getError_Value () {
      return error_Value;
   }

   public void setError_Value (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 5)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.error_Value = value;
   }

   /**
    * Accessor/mutator methods for Error_NumSamples
    */
   public Asn1BitString getError_NumSamples () {
      return error_NumSamples;
   }

   public void setError_NumSamples (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 3)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.error_NumSamples = value;
   }

   public boolean hasError_NumSamples () {
      return (error_NumSamples != null);
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

   public int getElementCount() { return 4; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return error_Resolution;
         case 1: return error_Value;
         case 2: return error_NumSamples;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "error-Resolution";
         case 1: return "error-Value";
         case 2: return "error-NumSamples";
         case 3: return null;
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

      boolean error_NumSamplesPresent = buffer.decodeBit ("error_NumSamplesPresent");

      // decode error_Resolution

      buffer.getContext().eventDispatcher.startElement("error_Resolution", -1);

      error_Resolution = new Asn1BitString();
      error_Resolution.decode (buffer, 2, 2);
      buffer.getContext().eventDispatcher.endElement("error_Resolution", -1);

      // decode error_Value

      buffer.getContext().eventDispatcher.startElement("error_Value", -1);

      error_Value = new Asn1BitString();
      error_Value.decode (buffer, 5, 5);
      buffer.getContext().eventDispatcher.endElement("error_Value", -1);

      // decode error_NumSamples

      if (error_NumSamplesPresent) {
         buffer.getContext().eventDispatcher.startElement("error_NumSamples", -1);

         error_NumSamples = new Asn1BitString();
         error_NumSamples.decode (buffer, 3, 3);
         buffer.getContext().eventDispatcher.endElement("error_NumSamples", -1);
      }
      else {
         error_NumSamples = null;
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

      buffer.encodeBit ((error_NumSamples != null), null);

      // encode error_Resolution

      if (error_Resolution != null) {
         buffer.getContext().eventDispatcher.startElement("error_Resolution", -1);

         error_Resolution.encode (buffer, 2, 2);

         buffer.getContext().eventDispatcher.endElement("error_Resolution", -1);
      }
      else throw new Asn1MissingRequiredException ("error_Resolution");

      // encode error_Value

      if (error_Value != null) {
         buffer.getContext().eventDispatcher.startElement("error_Value", -1);

         error_Value.encode (buffer, 5, 5);

         buffer.getContext().eventDispatcher.endElement("error_Value", -1);
      }
      else throw new Asn1MissingRequiredException ("error_Value");

      // encode error_NumSamples

      if (error_NumSamples != null) {
         buffer.getContext().eventDispatcher.startElement("error_NumSamples", -1);

         error_NumSamples.encode (buffer, 3, 3);

         buffer.getContext().eventDispatcher.endElement("error_NumSamples", -1);
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
