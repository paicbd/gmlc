/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_RTK_SatelliteDataElement_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-RTK-SatelliteDataElement-r15";
   }

   protected SV_ID svID_r15;
   protected Asn1Integer integer_ms_r15;  // optional
   protected Asn1Integer rough_range_r15;
   protected Asn1Integer rough_phase_range_rate_r15;  // optional
   protected GNSS_RTK_SatelliteSignalDataList_r15 gnss_rtk_SatelliteSignalDataList_r15;
   protected Asn1OpenExt extElem1;

   public GNSS_RTK_SatelliteDataElement_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_RTK_SatelliteDataElement_r15 (
      SV_ID svID_r15_,
      Asn1Integer integer_ms_r15_,
      Asn1Integer rough_range_r15_,
      Asn1Integer rough_phase_range_rate_r15_,
      GNSS_RTK_SatelliteSignalDataList_r15 gnss_rtk_SatelliteSignalDataList_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setInteger_ms_r15 (integer_ms_r15_);
      setRough_range_r15 (rough_range_r15_);
      setRough_phase_range_rate_r15 (rough_phase_range_rate_r15_);
      setGnss_rtk_SatelliteSignalDataList_r15 (gnss_rtk_SatelliteSignalDataList_r15_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_RTK_SatelliteDataElement_r15 (
      SV_ID svID_r15_,
      Asn1Integer rough_range_r15_,
      GNSS_RTK_SatelliteSignalDataList_r15 gnss_rtk_SatelliteSignalDataList_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setRough_range_r15 (rough_range_r15_);
      setGnss_rtk_SatelliteSignalDataList_r15 (gnss_rtk_SatelliteSignalDataList_r15_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_RTK_SatelliteDataElement_r15 (SV_ID svID_r15_,
      long integer_ms_r15_,
      long rough_range_r15_,
      long rough_phase_range_rate_r15_,
      GNSS_RTK_SatelliteSignalDataList_r15 gnss_rtk_SatelliteSignalDataList_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setInteger_ms_r15 (integer_ms_r15_);
      setRough_range_r15 (rough_range_r15_);
      setRough_phase_range_rate_r15 (rough_phase_range_rate_r15_);
      setGnss_rtk_SatelliteSignalDataList_r15 (gnss_rtk_SatelliteSignalDataList_r15_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public GNSS_RTK_SatelliteDataElement_r15 (
      SV_ID svID_r15_,
      long rough_range_r15_,
      GNSS_RTK_SatelliteSignalDataList_r15 gnss_rtk_SatelliteSignalDataList_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setRough_range_r15 (rough_range_r15_);
      setGnss_rtk_SatelliteSignalDataList_r15 (gnss_rtk_SatelliteSignalDataList_r15_);
   }

   public void init () {
      svID_r15 = null;
      integer_ms_r15 = null;
      rough_range_r15 = null;
      rough_phase_range_rate_r15 = null;
      gnss_rtk_SatelliteSignalDataList_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_RTK_SatelliteDataElement_r15) ) return false;

      GNSS_RTK_SatelliteDataElement_r15 rhs = (GNSS_RTK_SatelliteDataElement_r15) obj;

      if (svID_r15 == null) {
         if (rhs.svID_r15 != null) return false;
      }
      else {
         if (!svID_r15.equals(rhs.svID_r15)) {
            return false;
         }
      }

      if (integer_ms_r15 == null) {
         if (rhs.integer_ms_r15 != null) return false;
      }
      else {
         if (!integer_ms_r15.equals(rhs.integer_ms_r15)) {
            return false;
         }
      }

      if (rough_range_r15 == null) {
         if (rhs.rough_range_r15 != null) return false;
      }
      else {
         if (!rough_range_r15.equals(rhs.rough_range_r15)) {
            return false;
         }
      }

      if (rough_phase_range_rate_r15 == null) {
         if (rhs.rough_phase_range_rate_r15 != null) return false;
      }
      else {
         if (!rough_phase_range_rate_r15.equals(rhs.rough_phase_range_rate_r15)) {
            return false;
         }
      }

      if (gnss_rtk_SatelliteSignalDataList_r15 == null) {
         if (rhs.gnss_rtk_SatelliteSignalDataList_r15 != null) return false;
      }
      else {
         if (!gnss_rtk_SatelliteSignalDataList_r15.equals(rhs.gnss_rtk_SatelliteSignalDataList_r15)) {
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

      if (svID_r15 != null) __code = 31*__code + svID_r15.hashCode();
      if (integer_ms_r15 != null) __code = 31*__code + integer_ms_r15.hashCode();
      if (rough_range_r15 != null) __code = 31*__code + rough_range_r15.hashCode();
      if (rough_phase_range_rate_r15 != null) __code = 31*__code + rough_phase_range_rate_r15.hashCode();
      if (gnss_rtk_SatelliteSignalDataList_r15 != null) __code = 31*__code + gnss_rtk_SatelliteSignalDataList_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for SvID_r15
    */
   public SV_ID getSvID_r15 () {
      return svID_r15;
   }

   public void setSvID_r15 (SV_ID value) {
      this.svID_r15 = value;
   }

   /**
    * Accessor/mutator methods for Integer_ms_r15
    */
   public Asn1Integer getInteger_ms_r15 () {
      return integer_ms_r15;
   }

   public void setInteger_ms_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 254))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.integer_ms_r15 = value;
   }

   public void setInteger_ms_r15 (long value) 
      throws Asn1Exception
   {
      setInteger_ms_r15 (new Asn1Integer(value));
   }
   public boolean hasInteger_ms_r15 () {
      return (integer_ms_r15 != null);
   }

   /**
    * Accessor/mutator methods for Rough_range_r15
    */
   public Asn1Integer getRough_range_r15 () {
      return rough_range_r15;
   }

   public void setRough_range_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.rough_range_r15 = value;
   }

   public void setRough_range_r15 (long value) 
      throws Asn1Exception
   {
      setRough_range_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Rough_phase_range_rate_r15
    */
   public Asn1Integer getRough_phase_range_rate_r15 () {
      return rough_phase_range_rate_r15;
   }

   public void setRough_phase_range_rate_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8192 && value.value <= 8191))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.rough_phase_range_rate_r15 = value;
   }

   public void setRough_phase_range_rate_r15 (long value) 
      throws Asn1Exception
   {
      setRough_phase_range_rate_r15 (new Asn1Integer(value));
   }
   public boolean hasRough_phase_range_rate_r15 () {
      return (rough_phase_range_rate_r15 != null);
   }

   /**
    * Accessor/mutator methods for Gnss_rtk_SatelliteSignalDataList_r15
    */
   public GNSS_RTK_SatelliteSignalDataList_r15 getGnss_rtk_SatelliteSignalDataList_r15 () {
      return gnss_rtk_SatelliteSignalDataList_r15;
   }

   public void setGnss_rtk_SatelliteSignalDataList_r15 (GNSS_RTK_SatelliteSignalDataList_r15 value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 1 && value.getElements().length <= 24))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.gnss_rtk_SatelliteSignalDataList_r15 = value;
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
         case 0: return svID_r15;
         case 1: return integer_ms_r15;
         case 2: return rough_range_r15;
         case 3: return rough_phase_range_rate_r15;
         case 4: return gnss_rtk_SatelliteSignalDataList_r15;
         case 5: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "svID-r15";
         case 1: return "integer-ms-r15";
         case 2: return "rough-range-r15";
         case 3: return "rough-phase-range-rate-r15";
         case 4: return "gnss-rtk-SatelliteSignalDataList-r15";
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

      boolean integer_ms_r15Present = buffer.decodeBit ("integer_ms_r15Present");
      boolean rough_phase_range_rate_r15Present = buffer.decodeBit ("rough_phase_range_rate_r15Present");

      // decode svID_r15

      buffer.getContext().eventDispatcher.startElement("svID_r15", -1);

      svID_r15 = new SV_ID();
      svID_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("svID_r15", -1);

      // decode integer_ms_r15

      if (integer_ms_r15Present) {
         buffer.getContext().eventDispatcher.startElement("integer_ms_r15", -1);

         integer_ms_r15 = new Asn1Integer();
         integer_ms_r15.decode (buffer, 0, 254);
         buffer.getContext().eventDispatcher.endElement("integer_ms_r15", -1);
      }
      else {
         integer_ms_r15 = null;
      }

      // decode rough_range_r15

      buffer.getContext().eventDispatcher.startElement("rough_range_r15", -1);

      rough_range_r15 = new Asn1Integer();
      rough_range_r15.decode (buffer, 0, 1023);
      buffer.getContext().eventDispatcher.endElement("rough_range_r15", -1);

      // decode rough_phase_range_rate_r15

      if (rough_phase_range_rate_r15Present) {
         buffer.getContext().eventDispatcher.startElement("rough_phase_range_rate_r15", -1);

         rough_phase_range_rate_r15 = new Asn1Integer();
         rough_phase_range_rate_r15.decode (buffer, -8192, 8191);
         buffer.getContext().eventDispatcher.endElement("rough_phase_range_rate_r15", -1);
      }
      else {
         rough_phase_range_rate_r15 = null;
      }

      // decode gnss_rtk_SatelliteSignalDataList_r15

      buffer.getContext().eventDispatcher.startElement("gnss_rtk_SatelliteSignalDataList_r15", -1);

      gnss_rtk_SatelliteSignalDataList_r15 = new GNSS_RTK_SatelliteSignalDataList_r15();
      gnss_rtk_SatelliteSignalDataList_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("gnss_rtk_SatelliteSignalDataList_r15", -1);

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

      buffer.encodeBit ((integer_ms_r15 != null), null);
      buffer.encodeBit ((rough_phase_range_rate_r15 != null), null);

      // encode svID_r15

      if (svID_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("svID_r15", -1);

         svID_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("svID_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("svID_r15");

      // encode integer_ms_r15

      if (integer_ms_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("integer_ms_r15", -1);

         integer_ms_r15.encode (buffer, 0, 254);

         buffer.getContext().eventDispatcher.endElement("integer_ms_r15", -1);
      }

      // encode rough_range_r15

      if (rough_range_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("rough_range_r15", -1);

         rough_range_r15.encode (buffer, 0, 1023);

         buffer.getContext().eventDispatcher.endElement("rough_range_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("rough_range_r15");

      // encode rough_phase_range_rate_r15

      if (rough_phase_range_rate_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("rough_phase_range_rate_r15", -1);

         rough_phase_range_rate_r15.encode (buffer, -8192, 8191);

         buffer.getContext().eventDispatcher.endElement("rough_phase_range_rate_r15", -1);
      }

      // encode gnss_rtk_SatelliteSignalDataList_r15

      if (gnss_rtk_SatelliteSignalDataList_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_rtk_SatelliteSignalDataList_r15", -1);

         gnss_rtk_SatelliteSignalDataList_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("gnss_rtk_SatelliteSignalDataList_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_rtk_SatelliteSignalDataList_r15");

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
