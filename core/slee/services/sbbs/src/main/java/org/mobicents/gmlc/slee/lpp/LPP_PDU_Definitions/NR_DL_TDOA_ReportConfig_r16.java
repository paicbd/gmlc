/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_DL_TDOA_ReportConfig_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NR-DL-TDOA-ReportConfig-r16";
   }

   protected Asn1Integer maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16;  // optional
   protected Asn1Integer timingReportingGranularityFactor_r16;  // optional
   protected Asn1OpenExt extElem1;

   public NR_DL_TDOA_ReportConfig_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NR_DL_TDOA_ReportConfig_r16 (
      Asn1Integer maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16_,
      Asn1Integer timingReportingGranularityFactor_r16_
   ) throws Asn1Exception {
      super();
      setMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16_);
      setTimingReportingGranularityFactor_r16 (timingReportingGranularityFactor_r16_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NR_DL_TDOA_ReportConfig_r16 (long maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16_,
      long timingReportingGranularityFactor_r16_
   ) throws Asn1Exception {
      super();
      setMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16_);
      setTimingReportingGranularityFactor_r16 (timingReportingGranularityFactor_r16_);
   }

   public void init () {
      maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 = null;
      timingReportingGranularityFactor_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NR_DL_TDOA_ReportConfig_r16) ) return false;

      NR_DL_TDOA_ReportConfig_r16 rhs = (NR_DL_TDOA_ReportConfig_r16) obj;

      if (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 == null) {
         if (rhs.maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 != null) return false;
      }
      else {
         if (!maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16.equals(rhs.maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16)) {
            return false;
         }
      }

      if (timingReportingGranularityFactor_r16 == null) {
         if (rhs.timingReportingGranularityFactor_r16 != null) return false;
      }
      else {
         if (!timingReportingGranularityFactor_r16.equals(rhs.timingReportingGranularityFactor_r16)) {
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

      if (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 != null) __code = 31*__code + maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16.hashCode();
      if (timingReportingGranularityFactor_r16 != null) __code = 31*__code + timingReportingGranularityFactor_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for MaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16
    */
   public Asn1Integer getMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 () {
      return maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16;
   }

   public void setMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 4))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 = value;
   }

   public void setMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 (long value) 
      throws Asn1Exception
   {
      setMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 (new Asn1Integer(value));
   }
   public boolean hasMaxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 () {
      return (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 != null);
   }

   /**
    * Accessor/mutator methods for TimingReportingGranularityFactor_r16
    */
   public Asn1Integer getTimingReportingGranularityFactor_r16 () {
      return timingReportingGranularityFactor_r16;
   }

   public void setTimingReportingGranularityFactor_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 5))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.timingReportingGranularityFactor_r16 = value;
   }

   public void setTimingReportingGranularityFactor_r16 (long value) 
      throws Asn1Exception
   {
      setTimingReportingGranularityFactor_r16 (new Asn1Integer(value));
   }
   public boolean hasTimingReportingGranularityFactor_r16 () {
      return (timingReportingGranularityFactor_r16 != null);
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
         case 0: return maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16;
         case 1: return timingReportingGranularityFactor_r16;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "maxDL-PRS-RSTD-MeasurementsPerTRPPair-r16";
         case 1: return "timingReportingGranularityFactor-r16";
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

      boolean maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16Present = buffer.decodeBit ("maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16Present");
      boolean timingReportingGranularityFactor_r16Present = buffer.decodeBit ("timingReportingGranularityFactor_r16Present");

      // decode maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16

      if (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16Present) {
         buffer.getContext().eventDispatcher.startElement("maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16", -1);

         maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 = new Asn1Integer();
         maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16.decode (buffer, 1, 4);
         buffer.getContext().eventDispatcher.endElement("maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16", -1);
      }
      else {
         maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 = null;
      }

      // decode timingReportingGranularityFactor_r16

      if (timingReportingGranularityFactor_r16Present) {
         buffer.getContext().eventDispatcher.startElement("timingReportingGranularityFactor_r16", -1);

         timingReportingGranularityFactor_r16 = new Asn1Integer();
         timingReportingGranularityFactor_r16.decode (buffer, 0, 5);
         buffer.getContext().eventDispatcher.endElement("timingReportingGranularityFactor_r16", -1);
      }
      else {
         timingReportingGranularityFactor_r16 = null;
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

      buffer.encodeBit ((maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 != null), null);
      buffer.encodeBit ((timingReportingGranularityFactor_r16 != null), null);

      // encode maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16

      if (maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16", -1);

         maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16.encode (buffer, 1, 4);

         buffer.getContext().eventDispatcher.endElement("maxDL_PRS_RSTD_MeasurementsPerTRPPair_r16", -1);
      }

      // encode timingReportingGranularityFactor_r16

      if (timingReportingGranularityFactor_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("timingReportingGranularityFactor_r16", -1);

         timingReportingGranularityFactor_r16.encode (buffer, 0, 5);

         buffer.getContext().eventDispatcher.endElement("timingReportingGranularityFactor_r16", -1);
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
