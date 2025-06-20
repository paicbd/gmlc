/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_SSR_ArrayOfCorrectionPoints_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-SSR-ArrayOfCorrectionPoints-r16";
   }

   protected Asn1Integer referencePointLatitude_r16;
   protected Asn1Integer referencePointLongitude_r16;
   protected Asn1Integer numberOfStepsLatitude_r16;
   protected Asn1Integer numberOfStepsLongitude_r16;
   protected Asn1Integer stepOfLatitude_r16;
   protected Asn1Integer stepOfLongitude_r16;
   protected Asn1BitString bitmaskOfGrids_r16;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_SSR_ArrayOfCorrectionPoints_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_SSR_ArrayOfCorrectionPoints_r16 (
      Asn1Integer referencePointLatitude_r16_,
      Asn1Integer referencePointLongitude_r16_,
      Asn1Integer numberOfStepsLatitude_r16_,
      Asn1Integer numberOfStepsLongitude_r16_,
      Asn1Integer stepOfLatitude_r16_,
      Asn1Integer stepOfLongitude_r16_,
      Asn1BitString bitmaskOfGrids_r16_
   ) throws Asn1Exception {
      super();
      setReferencePointLatitude_r16 (referencePointLatitude_r16_);
      setReferencePointLongitude_r16 (referencePointLongitude_r16_);
      setNumberOfStepsLatitude_r16 (numberOfStepsLatitude_r16_);
      setNumberOfStepsLongitude_r16 (numberOfStepsLongitude_r16_);
      setStepOfLatitude_r16 (stepOfLatitude_r16_);
      setStepOfLongitude_r16 (stepOfLongitude_r16_);
      setBitmaskOfGrids_r16 (bitmaskOfGrids_r16_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_SSR_ArrayOfCorrectionPoints_r16 (
      Asn1Integer referencePointLatitude_r16_,
      Asn1Integer referencePointLongitude_r16_,
      Asn1Integer numberOfStepsLatitude_r16_,
      Asn1Integer numberOfStepsLongitude_r16_,
      Asn1Integer stepOfLatitude_r16_,
      Asn1Integer stepOfLongitude_r16_
   ) throws Asn1Exception {
      super();
      setReferencePointLatitude_r16 (referencePointLatitude_r16_);
      setReferencePointLongitude_r16 (referencePointLongitude_r16_);
      setNumberOfStepsLatitude_r16 (numberOfStepsLatitude_r16_);
      setNumberOfStepsLongitude_r16 (numberOfStepsLongitude_r16_);
      setStepOfLatitude_r16 (stepOfLatitude_r16_);
      setStepOfLongitude_r16 (stepOfLongitude_r16_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_SSR_ArrayOfCorrectionPoints_r16 (long referencePointLatitude_r16_,
      long referencePointLongitude_r16_,
      long numberOfStepsLatitude_r16_,
      long numberOfStepsLongitude_r16_,
      long stepOfLatitude_r16_,
      long stepOfLongitude_r16_,
      Asn1BitString bitmaskOfGrids_r16_
   ) throws Asn1Exception {
      super();
      setReferencePointLatitude_r16 (referencePointLatitude_r16_);
      setReferencePointLongitude_r16 (referencePointLongitude_r16_);
      setNumberOfStepsLatitude_r16 (numberOfStepsLatitude_r16_);
      setNumberOfStepsLongitude_r16 (numberOfStepsLongitude_r16_);
      setStepOfLatitude_r16 (stepOfLatitude_r16_);
      setStepOfLongitude_r16 (stepOfLongitude_r16_);
      setBitmaskOfGrids_r16 (bitmaskOfGrids_r16_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public GNSS_SSR_ArrayOfCorrectionPoints_r16 (
      long referencePointLatitude_r16_,
      long referencePointLongitude_r16_,
      long numberOfStepsLatitude_r16_,
      long numberOfStepsLongitude_r16_,
      long stepOfLatitude_r16_,
      long stepOfLongitude_r16_
   ) throws Asn1Exception {
      super();
      setReferencePointLatitude_r16 (referencePointLatitude_r16_);
      setReferencePointLongitude_r16 (referencePointLongitude_r16_);
      setNumberOfStepsLatitude_r16 (numberOfStepsLatitude_r16_);
      setNumberOfStepsLongitude_r16 (numberOfStepsLongitude_r16_);
      setStepOfLatitude_r16 (stepOfLatitude_r16_);
      setStepOfLongitude_r16 (stepOfLongitude_r16_);
   }

   public void init () {
      referencePointLatitude_r16 = null;
      referencePointLongitude_r16 = null;
      numberOfStepsLatitude_r16 = null;
      numberOfStepsLongitude_r16 = null;
      stepOfLatitude_r16 = null;
      stepOfLongitude_r16 = null;
      bitmaskOfGrids_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_SSR_ArrayOfCorrectionPoints_r16) ) return false;

      GNSS_SSR_ArrayOfCorrectionPoints_r16 rhs = (GNSS_SSR_ArrayOfCorrectionPoints_r16) obj;

      if (referencePointLatitude_r16 == null) {
         if (rhs.referencePointLatitude_r16 != null) return false;
      }
      else {
         if (!referencePointLatitude_r16.equals(rhs.referencePointLatitude_r16)) {
            return false;
         }
      }

      if (referencePointLongitude_r16 == null) {
         if (rhs.referencePointLongitude_r16 != null) return false;
      }
      else {
         if (!referencePointLongitude_r16.equals(rhs.referencePointLongitude_r16)) {
            return false;
         }
      }

      if (numberOfStepsLatitude_r16 == null) {
         if (rhs.numberOfStepsLatitude_r16 != null) return false;
      }
      else {
         if (!numberOfStepsLatitude_r16.equals(rhs.numberOfStepsLatitude_r16)) {
            return false;
         }
      }

      if (numberOfStepsLongitude_r16 == null) {
         if (rhs.numberOfStepsLongitude_r16 != null) return false;
      }
      else {
         if (!numberOfStepsLongitude_r16.equals(rhs.numberOfStepsLongitude_r16)) {
            return false;
         }
      }

      if (stepOfLatitude_r16 == null) {
         if (rhs.stepOfLatitude_r16 != null) return false;
      }
      else {
         if (!stepOfLatitude_r16.equals(rhs.stepOfLatitude_r16)) {
            return false;
         }
      }

      if (stepOfLongitude_r16 == null) {
         if (rhs.stepOfLongitude_r16 != null) return false;
      }
      else {
         if (!stepOfLongitude_r16.equals(rhs.stepOfLongitude_r16)) {
            return false;
         }
      }

      if (bitmaskOfGrids_r16 == null) {
         if (rhs.bitmaskOfGrids_r16 != null) return false;
      }
      else {
         if (!bitmaskOfGrids_r16.equals(rhs.bitmaskOfGrids_r16)) {
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

      if (referencePointLatitude_r16 != null) __code = 31*__code + referencePointLatitude_r16.hashCode();
      if (referencePointLongitude_r16 != null) __code = 31*__code + referencePointLongitude_r16.hashCode();
      if (numberOfStepsLatitude_r16 != null) __code = 31*__code + numberOfStepsLatitude_r16.hashCode();
      if (numberOfStepsLongitude_r16 != null) __code = 31*__code + numberOfStepsLongitude_r16.hashCode();
      if (stepOfLatitude_r16 != null) __code = 31*__code + stepOfLatitude_r16.hashCode();
      if (stepOfLongitude_r16 != null) __code = 31*__code + stepOfLongitude_r16.hashCode();
      if (bitmaskOfGrids_r16 != null) __code = 31*__code + bitmaskOfGrids_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for ReferencePointLatitude_r16
    */
   public Asn1Integer getReferencePointLatitude_r16 () {
      return referencePointLatitude_r16;
   }

   public void setReferencePointLatitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -16384 && value.value <= 16383))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.referencePointLatitude_r16 = value;
   }

   public void setReferencePointLatitude_r16 (long value) 
      throws Asn1Exception
   {
      setReferencePointLatitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for ReferencePointLongitude_r16
    */
   public Asn1Integer getReferencePointLongitude_r16 () {
      return referencePointLongitude_r16;
   }

   public void setReferencePointLongitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.referencePointLongitude_r16 = value;
   }

   public void setReferencePointLongitude_r16 (long value) 
      throws Asn1Exception
   {
      setReferencePointLongitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for NumberOfStepsLatitude_r16
    */
   public Asn1Integer getNumberOfStepsLatitude_r16 () {
      return numberOfStepsLatitude_r16;
   }

   public void setNumberOfStepsLatitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 63))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.numberOfStepsLatitude_r16 = value;
   }

   public void setNumberOfStepsLatitude_r16 (long value) 
      throws Asn1Exception
   {
      setNumberOfStepsLatitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for NumberOfStepsLongitude_r16
    */
   public Asn1Integer getNumberOfStepsLongitude_r16 () {
      return numberOfStepsLongitude_r16;
   }

   public void setNumberOfStepsLongitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 63))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.numberOfStepsLongitude_r16 = value;
   }

   public void setNumberOfStepsLongitude_r16 (long value) 
      throws Asn1Exception
   {
      setNumberOfStepsLongitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for StepOfLatitude_r16
    */
   public Asn1Integer getStepOfLatitude_r16 () {
      return stepOfLatitude_r16;
   }

   public void setStepOfLatitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 511))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.stepOfLatitude_r16 = value;
   }

   public void setStepOfLatitude_r16 (long value) 
      throws Asn1Exception
   {
      setStepOfLatitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for StepOfLongitude_r16
    */
   public Asn1Integer getStepOfLongitude_r16 () {
      return stepOfLongitude_r16;
   }

   public void setStepOfLongitude_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.stepOfLongitude_r16 = value;
   }

   public void setStepOfLongitude_r16 (long value) 
      throws Asn1Exception
   {
      setStepOfLongitude_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BitmaskOfGrids_r16
    */
   public Asn1BitString getBitmaskOfGrids_r16 () {
      return bitmaskOfGrids_r16;
   }

   public void setBitmaskOfGrids_r16 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 64)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.bitmaskOfGrids_r16 = value;
   }

   public boolean hasBitmaskOfGrids_r16 () {
      return (bitmaskOfGrids_r16 != null);
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

   public int getElementCount() { return 8; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return referencePointLatitude_r16;
         case 1: return referencePointLongitude_r16;
         case 2: return numberOfStepsLatitude_r16;
         case 3: return numberOfStepsLongitude_r16;
         case 4: return stepOfLatitude_r16;
         case 5: return stepOfLongitude_r16;
         case 6: return bitmaskOfGrids_r16;
         case 7: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "referencePointLatitude-r16";
         case 1: return "referencePointLongitude-r16";
         case 2: return "numberOfStepsLatitude-r16";
         case 3: return "numberOfStepsLongitude-r16";
         case 4: return "stepOfLatitude-r16";
         case 5: return "stepOfLongitude-r16";
         case 6: return "bitmaskOfGrids-r16";
         case 7: return null;
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

      boolean bitmaskOfGrids_r16Present = buffer.decodeBit ("bitmaskOfGrids_r16Present");

      // decode referencePointLatitude_r16

      buffer.getContext().eventDispatcher.startElement("referencePointLatitude_r16", -1);

      referencePointLatitude_r16 = new Asn1Integer();
      referencePointLatitude_r16.decode (buffer, -16384, 16383);
      buffer.getContext().eventDispatcher.endElement("referencePointLatitude_r16", -1);

      // decode referencePointLongitude_r16

      buffer.getContext().eventDispatcher.startElement("referencePointLongitude_r16", -1);

      referencePointLongitude_r16 = new Asn1Integer();
      referencePointLongitude_r16.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("referencePointLongitude_r16", -1);

      // decode numberOfStepsLatitude_r16

      buffer.getContext().eventDispatcher.startElement("numberOfStepsLatitude_r16", -1);

      numberOfStepsLatitude_r16 = new Asn1Integer();
      numberOfStepsLatitude_r16.decode (buffer, 0, 63);
      buffer.getContext().eventDispatcher.endElement("numberOfStepsLatitude_r16", -1);

      // decode numberOfStepsLongitude_r16

      buffer.getContext().eventDispatcher.startElement("numberOfStepsLongitude_r16", -1);

      numberOfStepsLongitude_r16 = new Asn1Integer();
      numberOfStepsLongitude_r16.decode (buffer, 0, 63);
      buffer.getContext().eventDispatcher.endElement("numberOfStepsLongitude_r16", -1);

      // decode stepOfLatitude_r16

      buffer.getContext().eventDispatcher.startElement("stepOfLatitude_r16", -1);

      stepOfLatitude_r16 = new Asn1Integer();
      stepOfLatitude_r16.decode (buffer, 1, 511);
      buffer.getContext().eventDispatcher.endElement("stepOfLatitude_r16", -1);

      // decode stepOfLongitude_r16

      buffer.getContext().eventDispatcher.startElement("stepOfLongitude_r16", -1);

      stepOfLongitude_r16 = new Asn1Integer();
      stepOfLongitude_r16.decode (buffer, 1, 1023);
      buffer.getContext().eventDispatcher.endElement("stepOfLongitude_r16", -1);

      // decode bitmaskOfGrids_r16

      if (bitmaskOfGrids_r16Present) {
         buffer.getContext().eventDispatcher.startElement("bitmaskOfGrids_r16", -1);

         bitmaskOfGrids_r16 = new Asn1BitString();
         bitmaskOfGrids_r16.decode (buffer, 64, 64);
         buffer.getContext().eventDispatcher.endElement("bitmaskOfGrids_r16", -1);
      }
      else {
         bitmaskOfGrids_r16 = null;
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

      buffer.encodeBit ((bitmaskOfGrids_r16 != null), null);

      // encode referencePointLatitude_r16

      if (referencePointLatitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("referencePointLatitude_r16", -1);

         referencePointLatitude_r16.encode (buffer, -16384, 16383);

         buffer.getContext().eventDispatcher.endElement("referencePointLatitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("referencePointLatitude_r16");

      // encode referencePointLongitude_r16

      if (referencePointLongitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("referencePointLongitude_r16", -1);

         referencePointLongitude_r16.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("referencePointLongitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("referencePointLongitude_r16");

      // encode numberOfStepsLatitude_r16

      if (numberOfStepsLatitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("numberOfStepsLatitude_r16", -1);

         numberOfStepsLatitude_r16.encode (buffer, 0, 63);

         buffer.getContext().eventDispatcher.endElement("numberOfStepsLatitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("numberOfStepsLatitude_r16");

      // encode numberOfStepsLongitude_r16

      if (numberOfStepsLongitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("numberOfStepsLongitude_r16", -1);

         numberOfStepsLongitude_r16.encode (buffer, 0, 63);

         buffer.getContext().eventDispatcher.endElement("numberOfStepsLongitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("numberOfStepsLongitude_r16");

      // encode stepOfLatitude_r16

      if (stepOfLatitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("stepOfLatitude_r16", -1);

         stepOfLatitude_r16.encode (buffer, 1, 511);

         buffer.getContext().eventDispatcher.endElement("stepOfLatitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("stepOfLatitude_r16");

      // encode stepOfLongitude_r16

      if (stepOfLongitude_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("stepOfLongitude_r16", -1);

         stepOfLongitude_r16.encode (buffer, 1, 1023);

         buffer.getContext().eventDispatcher.endElement("stepOfLongitude_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("stepOfLongitude_r16");

      // encode bitmaskOfGrids_r16

      if (bitmaskOfGrids_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("bitmaskOfGrids_r16", -1);

         bitmaskOfGrids_r16.encode (buffer, 64, 64);

         buffer.getContext().eventDispatcher.endElement("bitmaskOfGrids_r16", -1);
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
