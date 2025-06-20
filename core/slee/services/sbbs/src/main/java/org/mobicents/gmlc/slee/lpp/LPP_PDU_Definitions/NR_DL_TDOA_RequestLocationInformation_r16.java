/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_DL_TDOA_RequestLocationInformation_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NR-DL-TDOA-RequestLocationInformation-r16";
   }

   protected NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16 nr_DL_PRS_RstdMeasurementInfoRequest_r16 = null;  // optional
   protected NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 nr_RequestedMeasurements_r16;
   protected Asn1Boolean nr_AssistanceAvailability_r16;
   protected NR_DL_TDOA_ReportConfig_r16 nr_DL_TDOA_ReportConfig_r16;  // optional
   protected NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16 additionalPaths_r16 = null;  // optional
   protected Asn1OpenExt extElem1;

   public NR_DL_TDOA_RequestLocationInformation_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NR_DL_TDOA_RequestLocationInformation_r16 (
      NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16 nr_DL_PRS_RstdMeasurementInfoRequest_r16_,
      NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 nr_RequestedMeasurements_r16_,
      Asn1Boolean nr_AssistanceAvailability_r16_,
      NR_DL_TDOA_ReportConfig_r16 nr_DL_TDOA_ReportConfig_r16_,
      NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16 additionalPaths_r16_
   ) throws Asn1Exception {
      super();
      setNr_DL_PRS_RstdMeasurementInfoRequest_r16 (nr_DL_PRS_RstdMeasurementInfoRequest_r16_);
      setNr_RequestedMeasurements_r16 (nr_RequestedMeasurements_r16_);
      setNr_AssistanceAvailability_r16 (nr_AssistanceAvailability_r16_);
      setNr_DL_TDOA_ReportConfig_r16 (nr_DL_TDOA_ReportConfig_r16_);
      setAdditionalPaths_r16 (additionalPaths_r16_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public NR_DL_TDOA_RequestLocationInformation_r16 (
      NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 nr_RequestedMeasurements_r16_,
      Asn1Boolean nr_AssistanceAvailability_r16_
   ) throws Asn1Exception {
      super();
      setNr_RequestedMeasurements_r16 (nr_RequestedMeasurements_r16_);
      setNr_AssistanceAvailability_r16 (nr_AssistanceAvailability_r16_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NR_DL_TDOA_RequestLocationInformation_r16 (NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16 nr_DL_PRS_RstdMeasurementInfoRequest_r16_,
      NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 nr_RequestedMeasurements_r16_,
      boolean nr_AssistanceAvailability_r16_,
      NR_DL_TDOA_ReportConfig_r16 nr_DL_TDOA_ReportConfig_r16_,
      NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16 additionalPaths_r16_
   ) throws Asn1Exception {
      super();
      setNr_DL_PRS_RstdMeasurementInfoRequest_r16 (nr_DL_PRS_RstdMeasurementInfoRequest_r16_);
      setNr_RequestedMeasurements_r16 (nr_RequestedMeasurements_r16_);
      setNr_AssistanceAvailability_r16 (nr_AssistanceAvailability_r16_);
      setNr_DL_TDOA_ReportConfig_r16 (nr_DL_TDOA_ReportConfig_r16_);
      setAdditionalPaths_r16 (additionalPaths_r16_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public NR_DL_TDOA_RequestLocationInformation_r16 (
      NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 nr_RequestedMeasurements_r16_,
      boolean nr_AssistanceAvailability_r16_
   ) throws Asn1Exception {
      super();
      setNr_RequestedMeasurements_r16 (nr_RequestedMeasurements_r16_);
      setNr_AssistanceAvailability_r16 (nr_AssistanceAvailability_r16_);
   }

   public void init () {
      nr_DL_PRS_RstdMeasurementInfoRequest_r16 = null;
      nr_RequestedMeasurements_r16 = null;
      nr_AssistanceAvailability_r16 = null;
      nr_DL_TDOA_ReportConfig_r16 = null;
      additionalPaths_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NR_DL_TDOA_RequestLocationInformation_r16) ) return false;

      NR_DL_TDOA_RequestLocationInformation_r16 rhs = (NR_DL_TDOA_RequestLocationInformation_r16) obj;

      if (nr_DL_PRS_RstdMeasurementInfoRequest_r16 == null) {
         if (rhs.nr_DL_PRS_RstdMeasurementInfoRequest_r16 != null) return false;
      }
      else {
         if (!nr_DL_PRS_RstdMeasurementInfoRequest_r16.equals(rhs.nr_DL_PRS_RstdMeasurementInfoRequest_r16)) {
            return false;
         }
      }

      if (nr_RequestedMeasurements_r16 == null) {
         if (rhs.nr_RequestedMeasurements_r16 != null) return false;
      }
      else {
         if (!nr_RequestedMeasurements_r16.equals(rhs.nr_RequestedMeasurements_r16)) {
            return false;
         }
      }

      if (nr_AssistanceAvailability_r16 == null) {
         if (rhs.nr_AssistanceAvailability_r16 != null) return false;
      }
      else {
         if (!nr_AssistanceAvailability_r16.equals(rhs.nr_AssistanceAvailability_r16)) {
            return false;
         }
      }

      if (nr_DL_TDOA_ReportConfig_r16 == null) {
         if (rhs.nr_DL_TDOA_ReportConfig_r16 != null) return false;
      }
      else {
         if (!nr_DL_TDOA_ReportConfig_r16.equals(rhs.nr_DL_TDOA_ReportConfig_r16)) {
            return false;
         }
      }

      if (additionalPaths_r16 == null) {
         if (rhs.additionalPaths_r16 != null) return false;
      }
      else {
         if (!additionalPaths_r16.equals(rhs.additionalPaths_r16)) {
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

      if (nr_DL_PRS_RstdMeasurementInfoRequest_r16 != null) __code = 31*__code + nr_DL_PRS_RstdMeasurementInfoRequest_r16.hashCode();
      if (nr_RequestedMeasurements_r16 != null) __code = 31*__code + nr_RequestedMeasurements_r16.hashCode();
      if (nr_AssistanceAvailability_r16 != null) __code = 31*__code + nr_AssistanceAvailability_r16.hashCode();
      if (nr_DL_TDOA_ReportConfig_r16 != null) __code = 31*__code + nr_DL_TDOA_ReportConfig_r16.hashCode();
      if (additionalPaths_r16 != null) __code = 31*__code + additionalPaths_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Nr_DL_PRS_RstdMeasurementInfoRequest_r16
    */
   public NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16 getNr_DL_PRS_RstdMeasurementInfoRequest_r16 () {
      return nr_DL_PRS_RstdMeasurementInfoRequest_r16;
   }

   public void setNr_DL_PRS_RstdMeasurementInfoRequest_r16 (NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16 value) {
      this.nr_DL_PRS_RstdMeasurementInfoRequest_r16 = value;
   }

   public boolean hasNr_DL_PRS_RstdMeasurementInfoRequest_r16 () {
      return (nr_DL_PRS_RstdMeasurementInfoRequest_r16 != null);
   }

   /**
    * Accessor/mutator methods for Nr_RequestedMeasurements_r16
    */
   public NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 getNr_RequestedMeasurements_r16 () {
      return nr_RequestedMeasurements_r16;
   }

   public void setNr_RequestedMeasurements_r16 (NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16 value) 
      throws Asn1Exception
   {
      if (!((value.getLength() >= 1 && value.getLength() <= 8))) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.nr_RequestedMeasurements_r16 = value;
   }

   /**
    * Accessor/mutator methods for Nr_AssistanceAvailability_r16
    */
   public Asn1Boolean getNr_AssistanceAvailability_r16 () {
      return nr_AssistanceAvailability_r16;
   }

   public void setNr_AssistanceAvailability_r16 (Asn1Boolean value) {
      this.nr_AssistanceAvailability_r16 = value;
   }

   public void setNr_AssistanceAvailability_r16 (boolean value) {
      setNr_AssistanceAvailability_r16 (new Asn1Boolean(value));
   }

   /**
    * Accessor/mutator methods for Nr_DL_TDOA_ReportConfig_r16
    */
   public NR_DL_TDOA_ReportConfig_r16 getNr_DL_TDOA_ReportConfig_r16 () {
      return nr_DL_TDOA_ReportConfig_r16;
   }

   public void setNr_DL_TDOA_ReportConfig_r16 (NR_DL_TDOA_ReportConfig_r16 value) {
      this.nr_DL_TDOA_ReportConfig_r16 = value;
   }

   public boolean hasNr_DL_TDOA_ReportConfig_r16 () {
      return (nr_DL_TDOA_ReportConfig_r16 != null);
   }

   /**
    * Accessor/mutator methods for AdditionalPaths_r16
    */
   public NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16 getAdditionalPaths_r16 () {
      return additionalPaths_r16;
   }

   public void setAdditionalPaths_r16 (NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16 value) {
      this.additionalPaths_r16 = value;
   }

   public boolean hasAdditionalPaths_r16 () {
      return (additionalPaths_r16 != null);
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

   public int getElementCount() { return 6; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return nr_DL_PRS_RstdMeasurementInfoRequest_r16;
         case 1: return nr_RequestedMeasurements_r16;
         case 2: return nr_AssistanceAvailability_r16;
         case 3: return nr_DL_TDOA_ReportConfig_r16;
         case 4: return additionalPaths_r16;
         case 5: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "nr-DL-PRS-RstdMeasurementInfoRequest-r16";
         case 1: return "nr-RequestedMeasurements-r16";
         case 2: return "nr-AssistanceAvailability-r16";
         case 3: return "nr-DL-TDOA-ReportConfig-r16";
         case 4: return "additionalPaths-r16";
         case 5: return null;
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

      boolean nr_DL_PRS_RstdMeasurementInfoRequest_r16Present = buffer.decodeBit ("nr_DL_PRS_RstdMeasurementInfoRequest_r16Present");
      boolean nr_DL_TDOA_ReportConfig_r16Present = buffer.decodeBit ("nr_DL_TDOA_ReportConfig_r16Present");
      boolean additionalPaths_r16Present = buffer.decodeBit ("additionalPaths_r16Present");

      // decode nr_DL_PRS_RstdMeasurementInfoRequest_r16

      if (nr_DL_PRS_RstdMeasurementInfoRequest_r16Present) {
         buffer.getContext().eventDispatcher.startElement("nr_DL_PRS_RstdMeasurementInfoRequest_r16", -1);

         int tval = NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16.decodeEnumValue (buffer);
         nr_DL_PRS_RstdMeasurementInfoRequest_r16 = NR_DL_TDOA_RequestLocationInformation_r16_nr_DL_PRS_RstdMeasurementInfoRequest_r16.valueOf (tval);
         buffer.getContext().eventDispatcher.endElement("nr_DL_PRS_RstdMeasurementInfoRequest_r16", -1);
      }
      else {
         nr_DL_PRS_RstdMeasurementInfoRequest_r16 = null;
      }

      // decode nr_RequestedMeasurements_r16

      buffer.getContext().eventDispatcher.startElement("nr_RequestedMeasurements_r16", -1);

      nr_RequestedMeasurements_r16 = new NR_DL_TDOA_RequestLocationInformation_r16_nr_RequestedMeasurements_r16();
      nr_RequestedMeasurements_r16.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("nr_RequestedMeasurements_r16", -1);

      // decode nr_AssistanceAvailability_r16

      buffer.getContext().eventDispatcher.startElement("nr_AssistanceAvailability_r16", -1);

      nr_AssistanceAvailability_r16 = new Asn1Boolean();
      nr_AssistanceAvailability_r16.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("nr_AssistanceAvailability_r16", -1);

      // decode nr_DL_TDOA_ReportConfig_r16

      if (nr_DL_TDOA_ReportConfig_r16Present) {
         buffer.getContext().eventDispatcher.startElement("nr_DL_TDOA_ReportConfig_r16", -1);

         nr_DL_TDOA_ReportConfig_r16 = new NR_DL_TDOA_ReportConfig_r16();
         nr_DL_TDOA_ReportConfig_r16.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("nr_DL_TDOA_ReportConfig_r16", -1);
      }
      else {
         nr_DL_TDOA_ReportConfig_r16 = null;
      }

      // decode additionalPaths_r16

      if (additionalPaths_r16Present) {
         buffer.getContext().eventDispatcher.startElement("additionalPaths_r16", -1);

         int tval = NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16.decodeEnumValue (buffer);
         additionalPaths_r16 = NR_DL_TDOA_RequestLocationInformation_r16_additionalPaths_r16.valueOf (tval);
         buffer.getContext().eventDispatcher.endElement("additionalPaths_r16", -1);
      }
      else {
         additionalPaths_r16 = null;
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

      buffer.encodeBit ((nr_DL_PRS_RstdMeasurementInfoRequest_r16 != null), null);
      buffer.encodeBit ((nr_DL_TDOA_ReportConfig_r16 != null), null);
      buffer.encodeBit ((additionalPaths_r16 != null), null);

      // encode nr_DL_PRS_RstdMeasurementInfoRequest_r16

      if (nr_DL_PRS_RstdMeasurementInfoRequest_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("nr_DL_PRS_RstdMeasurementInfoRequest_r16", -1);

         nr_DL_PRS_RstdMeasurementInfoRequest_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("nr_DL_PRS_RstdMeasurementInfoRequest_r16", -1);
      }

      // encode nr_RequestedMeasurements_r16

      if (nr_RequestedMeasurements_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("nr_RequestedMeasurements_r16", -1);

         nr_RequestedMeasurements_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("nr_RequestedMeasurements_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("nr_RequestedMeasurements_r16");

      // encode nr_AssistanceAvailability_r16

      if (nr_AssistanceAvailability_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("nr_AssistanceAvailability_r16", -1);

         nr_AssistanceAvailability_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("nr_AssistanceAvailability_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("nr_AssistanceAvailability_r16");

      // encode nr_DL_TDOA_ReportConfig_r16

      if (nr_DL_TDOA_ReportConfig_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("nr_DL_TDOA_ReportConfig_r16", -1);

         nr_DL_TDOA_ReportConfig_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("nr_DL_TDOA_ReportConfig_r16", -1);
      }

      // encode additionalPaths_r16

      if (additionalPaths_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("additionalPaths_r16", -1);

         additionalPaths_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("additionalPaths_r16", -1);
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
