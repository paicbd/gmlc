/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class MeasurementReferenceTime_networkTime_nbIoT_r14 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SEQUENCE";
   }

   protected Asn1Integer nbPhysCellId_r14;
   protected ECGI nbCellGlobalId_r14;  // optional
   protected Asn1BitString sfn_r14;
   protected Asn1BitString hyperSFN_r14;  // optional
   protected Asn1OpenExt extElem1;

   public MeasurementReferenceTime_networkTime_nbIoT_r14 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public MeasurementReferenceTime_networkTime_nbIoT_r14 (
      Asn1Integer nbPhysCellId_r14_,
      ECGI nbCellGlobalId_r14_,
      Asn1BitString sfn_r14_,
      Asn1BitString hyperSFN_r14_
   ) throws Asn1Exception {
      super();
      setNbPhysCellId_r14 (nbPhysCellId_r14_);
      setNbCellGlobalId_r14 (nbCellGlobalId_r14_);
      setSfn_r14 (sfn_r14_);
      setHyperSFN_r14 (hyperSFN_r14_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public MeasurementReferenceTime_networkTime_nbIoT_r14 (
      Asn1Integer nbPhysCellId_r14_,
      Asn1BitString sfn_r14_
   ) throws Asn1Exception {
      super();
      setNbPhysCellId_r14 (nbPhysCellId_r14_);
      setSfn_r14 (sfn_r14_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public MeasurementReferenceTime_networkTime_nbIoT_r14 (long nbPhysCellId_r14_,
      ECGI nbCellGlobalId_r14_,
      Asn1BitString sfn_r14_,
      Asn1BitString hyperSFN_r14_
   ) throws Asn1Exception {
      super();
      setNbPhysCellId_r14 (nbPhysCellId_r14_);
      setNbCellGlobalId_r14 (nbCellGlobalId_r14_);
      setSfn_r14 (sfn_r14_);
      setHyperSFN_r14 (hyperSFN_r14_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public MeasurementReferenceTime_networkTime_nbIoT_r14 (
      long nbPhysCellId_r14_,
      Asn1BitString sfn_r14_
   ) throws Asn1Exception {
      super();
      setNbPhysCellId_r14 (nbPhysCellId_r14_);
      setSfn_r14 (sfn_r14_);
   }

   public void init () {
      nbPhysCellId_r14 = null;
      nbCellGlobalId_r14 = null;
      sfn_r14 = null;
      hyperSFN_r14 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof MeasurementReferenceTime_networkTime_nbIoT_r14) ) return false;

      MeasurementReferenceTime_networkTime_nbIoT_r14 rhs = (MeasurementReferenceTime_networkTime_nbIoT_r14) obj;

      if (nbPhysCellId_r14 == null) {
         if (rhs.nbPhysCellId_r14 != null) return false;
      }
      else {
         if (!nbPhysCellId_r14.equals(rhs.nbPhysCellId_r14)) {
            return false;
         }
      }

      if (nbCellGlobalId_r14 == null) {
         if (rhs.nbCellGlobalId_r14 != null) return false;
      }
      else {
         if (!nbCellGlobalId_r14.equals(rhs.nbCellGlobalId_r14)) {
            return false;
         }
      }

      if (sfn_r14 == null) {
         if (rhs.sfn_r14 != null) return false;
      }
      else {
         if (!sfn_r14.equals(rhs.sfn_r14)) {
            return false;
         }
      }

      if (hyperSFN_r14 == null) {
         if (rhs.hyperSFN_r14 != null) return false;
      }
      else {
         if (!hyperSFN_r14.equals(rhs.hyperSFN_r14)) {
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

      if (nbPhysCellId_r14 != null) __code = 31*__code + nbPhysCellId_r14.hashCode();
      if (nbCellGlobalId_r14 != null) __code = 31*__code + nbCellGlobalId_r14.hashCode();
      if (sfn_r14 != null) __code = 31*__code + sfn_r14.hashCode();
      if (hyperSFN_r14 != null) __code = 31*__code + hyperSFN_r14.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for NbPhysCellId_r14
    */
   public Asn1Integer getNbPhysCellId_r14 () {
      return nbPhysCellId_r14;
   }

   public void setNbPhysCellId_r14 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 503))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.nbPhysCellId_r14 = value;
   }

   public void setNbPhysCellId_r14 (long value) 
      throws Asn1Exception
   {
      setNbPhysCellId_r14 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for NbCellGlobalId_r14
    */
   public ECGI getNbCellGlobalId_r14 () {
      return nbCellGlobalId_r14;
   }

   public void setNbCellGlobalId_r14 (ECGI value) {
      this.nbCellGlobalId_r14 = value;
   }

   public boolean hasNbCellGlobalId_r14 () {
      return (nbCellGlobalId_r14 != null);
   }

   /**
    * Accessor/mutator methods for Sfn_r14
    */
   public Asn1BitString getSfn_r14 () {
      return sfn_r14;
   }

   public void setSfn_r14 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 10)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.sfn_r14 = value;
   }

   /**
    * Accessor/mutator methods for HyperSFN_r14
    */
   public Asn1BitString getHyperSFN_r14 () {
      return hyperSFN_r14;
   }

   public void setHyperSFN_r14 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 10)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.hyperSFN_r14 = value;
   }

   public boolean hasHyperSFN_r14 () {
      return (hyperSFN_r14 != null);
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

   public int getElementCount() { return 5; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return nbPhysCellId_r14;
         case 1: return nbCellGlobalId_r14;
         case 2: return sfn_r14;
         case 3: return hyperSFN_r14;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "nbPhysCellId-r14";
         case 1: return "nbCellGlobalId-r14";
         case 2: return "sfn-r14";
         case 3: return "hyperSFN-r14";
         case 4: return null;
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

      boolean nbCellGlobalId_r14Present = buffer.decodeBit ("nbCellGlobalId_r14Present");
      boolean hyperSFN_r14Present = buffer.decodeBit ("hyperSFN_r14Present");

      // decode nbPhysCellId_r14

      buffer.getContext().eventDispatcher.startElement("nbPhysCellId_r14", -1);

      nbPhysCellId_r14 = new Asn1Integer();
      nbPhysCellId_r14.decode (buffer, 0, 503);
      buffer.getContext().eventDispatcher.endElement("nbPhysCellId_r14", -1);

      // decode nbCellGlobalId_r14

      if (nbCellGlobalId_r14Present) {
         buffer.getContext().eventDispatcher.startElement("nbCellGlobalId_r14", -1);

         nbCellGlobalId_r14 = new ECGI();
         nbCellGlobalId_r14.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("nbCellGlobalId_r14", -1);
      }
      else {
         nbCellGlobalId_r14 = null;
      }

      // decode sfn_r14

      buffer.getContext().eventDispatcher.startElement("sfn_r14", -1);

      sfn_r14 = new Asn1BitString();
      sfn_r14.decode (buffer, 10, 10);
      buffer.getContext().eventDispatcher.endElement("sfn_r14", -1);

      // decode hyperSFN_r14

      if (hyperSFN_r14Present) {
         buffer.getContext().eventDispatcher.startElement("hyperSFN_r14", -1);

         hyperSFN_r14 = new Asn1BitString();
         hyperSFN_r14.decode (buffer, 10, 10);
         buffer.getContext().eventDispatcher.endElement("hyperSFN_r14", -1);
      }
      else {
         hyperSFN_r14 = null;
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

      buffer.encodeBit ((nbCellGlobalId_r14 != null), null);
      buffer.encodeBit ((hyperSFN_r14 != null), null);

      // encode nbPhysCellId_r14

      if (nbPhysCellId_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("nbPhysCellId_r14", -1);

         nbPhysCellId_r14.encode (buffer, 0, 503);

         buffer.getContext().eventDispatcher.endElement("nbPhysCellId_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("nbPhysCellId_r14");

      // encode nbCellGlobalId_r14

      if (nbCellGlobalId_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("nbCellGlobalId_r14", -1);

         nbCellGlobalId_r14.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("nbCellGlobalId_r14", -1);
      }

      // encode sfn_r14

      if (sfn_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("sfn_r14", -1);

         sfn_r14.encode (buffer, 10, 10);

         buffer.getContext().eventDispatcher.endElement("sfn_r14", -1);
      }
      else throw new Asn1MissingRequiredException ("sfn_r14");

      // encode hyperSFN_r14

      if (hyperSFN_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("hyperSFN_r14", -1);

         hyperSFN_r14.encode (buffer, 10, 10);

         buffer.getContext().eventDispatcher.endElement("hyperSFN_r14", -1);
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
