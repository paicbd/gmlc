/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_DL_TDOA_MeasurementCapability_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NR-DL-TDOA-MeasurementCapability-r16";
   }

   protected Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR1_r16;
   protected Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR2_r16;
   protected NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16 supportOfDL_PRS_RSRP_MeasFR1_r16 = null;  // optional
   protected NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16 supportOfDL_PRS_RSRP_MeasFR2_r16 = null;  // optional
   protected Asn1OpenExt extElem1;

   public NR_DL_TDOA_MeasurementCapability_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NR_DL_TDOA_MeasurementCapability_r16 (
      Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_,
      Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_,
      NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16 supportOfDL_PRS_RSRP_MeasFR1_r16_,
      NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16 supportOfDL_PRS_RSRP_MeasFR2_r16_
   ) throws Asn1Exception {
      super();
      setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_);
      setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_);
      setSupportOfDL_PRS_RSRP_MeasFR1_r16 (supportOfDL_PRS_RSRP_MeasFR1_r16_);
      setSupportOfDL_PRS_RSRP_MeasFR2_r16 (supportOfDL_PRS_RSRP_MeasFR2_r16_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public NR_DL_TDOA_MeasurementCapability_r16 (
      Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_,
      Asn1Integer dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_
   ) throws Asn1Exception {
      super();
      setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_);
      setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NR_DL_TDOA_MeasurementCapability_r16 (long dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_,
      long dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_,
      NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16 supportOfDL_PRS_RSRP_MeasFR1_r16_,
      NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16 supportOfDL_PRS_RSRP_MeasFR2_r16_
   ) throws Asn1Exception {
      super();
      setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_);
      setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_);
      setSupportOfDL_PRS_RSRP_MeasFR1_r16 (supportOfDL_PRS_RSRP_MeasFR1_r16_);
      setSupportOfDL_PRS_RSRP_MeasFR2_r16 (supportOfDL_PRS_RSRP_MeasFR2_r16_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public NR_DL_TDOA_MeasurementCapability_r16 (
      long dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_,
      long dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_
   ) throws Asn1Exception {
      super();
      setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16_);
      setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16_);
   }

   public void init () {
      dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 = null;
      dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 = null;
      supportOfDL_PRS_RSRP_MeasFR1_r16 = null;
      supportOfDL_PRS_RSRP_MeasFR2_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NR_DL_TDOA_MeasurementCapability_r16) ) return false;

      NR_DL_TDOA_MeasurementCapability_r16 rhs = (NR_DL_TDOA_MeasurementCapability_r16) obj;

      if (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 == null) {
         if (rhs.dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 != null) return false;
      }
      else {
         if (!dl_RSTD_MeasurementPerPairOfTRP_FR1_r16.equals(rhs.dl_RSTD_MeasurementPerPairOfTRP_FR1_r16)) {
            return false;
         }
      }

      if (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 == null) {
         if (rhs.dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 != null) return false;
      }
      else {
         if (!dl_RSTD_MeasurementPerPairOfTRP_FR2_r16.equals(rhs.dl_RSTD_MeasurementPerPairOfTRP_FR2_r16)) {
            return false;
         }
      }

      if (supportOfDL_PRS_RSRP_MeasFR1_r16 == null) {
         if (rhs.supportOfDL_PRS_RSRP_MeasFR1_r16 != null) return false;
      }
      else {
         if (!supportOfDL_PRS_RSRP_MeasFR1_r16.equals(rhs.supportOfDL_PRS_RSRP_MeasFR1_r16)) {
            return false;
         }
      }

      if (supportOfDL_PRS_RSRP_MeasFR2_r16 == null) {
         if (rhs.supportOfDL_PRS_RSRP_MeasFR2_r16 != null) return false;
      }
      else {
         if (!supportOfDL_PRS_RSRP_MeasFR2_r16.equals(rhs.supportOfDL_PRS_RSRP_MeasFR2_r16)) {
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

      if (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 != null) __code = 31*__code + dl_RSTD_MeasurementPerPairOfTRP_FR1_r16.hashCode();
      if (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 != null) __code = 31*__code + dl_RSTD_MeasurementPerPairOfTRP_FR2_r16.hashCode();
      if (supportOfDL_PRS_RSRP_MeasFR1_r16 != null) __code = 31*__code + supportOfDL_PRS_RSRP_MeasFR1_r16.hashCode();
      if (supportOfDL_PRS_RSRP_MeasFR2_r16 != null) __code = 31*__code + supportOfDL_PRS_RSRP_MeasFR2_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Dl_RSTD_MeasurementPerPairOfTRP_FR1_r16
    */
   public Asn1Integer getDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 () {
      return dl_RSTD_MeasurementPerPairOfTRP_FR1_r16;
   }

   public void setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 4))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 = value;
   }

   public void setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (long value) 
      throws Asn1Exception
   {
      setDl_RSTD_MeasurementPerPairOfTRP_FR1_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Dl_RSTD_MeasurementPerPairOfTRP_FR2_r16
    */
   public Asn1Integer getDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 () {
      return dl_RSTD_MeasurementPerPairOfTRP_FR2_r16;
   }

   public void setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 4))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 = value;
   }

   public void setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (long value) 
      throws Asn1Exception
   {
      setDl_RSTD_MeasurementPerPairOfTRP_FR2_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for SupportOfDL_PRS_RSRP_MeasFR1_r16
    */
   public NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16 getSupportOfDL_PRS_RSRP_MeasFR1_r16 () {
      return supportOfDL_PRS_RSRP_MeasFR1_r16;
   }

   public void setSupportOfDL_PRS_RSRP_MeasFR1_r16 (NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16 value) {
      this.supportOfDL_PRS_RSRP_MeasFR1_r16 = value;
   }

   public boolean hasSupportOfDL_PRS_RSRP_MeasFR1_r16 () {
      return (supportOfDL_PRS_RSRP_MeasFR1_r16 != null);
   }

   /**
    * Accessor/mutator methods for SupportOfDL_PRS_RSRP_MeasFR2_r16
    */
   public NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16 getSupportOfDL_PRS_RSRP_MeasFR2_r16 () {
      return supportOfDL_PRS_RSRP_MeasFR2_r16;
   }

   public void setSupportOfDL_PRS_RSRP_MeasFR2_r16 (NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16 value) {
      this.supportOfDL_PRS_RSRP_MeasFR2_r16 = value;
   }

   public boolean hasSupportOfDL_PRS_RSRP_MeasFR2_r16 () {
      return (supportOfDL_PRS_RSRP_MeasFR2_r16 != null);
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
         case 0: return dl_RSTD_MeasurementPerPairOfTRP_FR1_r16;
         case 1: return dl_RSTD_MeasurementPerPairOfTRP_FR2_r16;
         case 2: return supportOfDL_PRS_RSRP_MeasFR1_r16;
         case 3: return supportOfDL_PRS_RSRP_MeasFR2_r16;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "dl-RSTD-MeasurementPerPairOfTRP-FR1-r16";
         case 1: return "dl-RSTD-MeasurementPerPairOfTRP-FR2-r16";
         case 2: return "supportOfDL-PRS-RSRP-MeasFR1-r16";
         case 3: return "supportOfDL-PRS-RSRP-MeasFR2-r16";
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

      boolean supportOfDL_PRS_RSRP_MeasFR1_r16Present = buffer.decodeBit ("supportOfDL_PRS_RSRP_MeasFR1_r16Present");
      boolean supportOfDL_PRS_RSRP_MeasFR2_r16Present = buffer.decodeBit ("supportOfDL_PRS_RSRP_MeasFR2_r16Present");

      // decode dl_RSTD_MeasurementPerPairOfTRP_FR1_r16

      buffer.getContext().eventDispatcher.startElement("dl_RSTD_MeasurementPerPairOfTRP_FR1_r16", -1);

      dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 = new Asn1Integer();
      dl_RSTD_MeasurementPerPairOfTRP_FR1_r16.decode (buffer, 1, 4);
      buffer.getContext().eventDispatcher.endElement("dl_RSTD_MeasurementPerPairOfTRP_FR1_r16", -1);

      // decode dl_RSTD_MeasurementPerPairOfTRP_FR2_r16

      buffer.getContext().eventDispatcher.startElement("dl_RSTD_MeasurementPerPairOfTRP_FR2_r16", -1);

      dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 = new Asn1Integer();
      dl_RSTD_MeasurementPerPairOfTRP_FR2_r16.decode (buffer, 1, 4);
      buffer.getContext().eventDispatcher.endElement("dl_RSTD_MeasurementPerPairOfTRP_FR2_r16", -1);

      // decode supportOfDL_PRS_RSRP_MeasFR1_r16

      if (supportOfDL_PRS_RSRP_MeasFR1_r16Present) {
         buffer.getContext().eventDispatcher.startElement("supportOfDL_PRS_RSRP_MeasFR1_r16", -1);

         int tval = NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16.decodeEnumValue (buffer);
         supportOfDL_PRS_RSRP_MeasFR1_r16 = NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR1_r16.valueOf (tval);
         buffer.getContext().eventDispatcher.endElement("supportOfDL_PRS_RSRP_MeasFR1_r16", -1);
      }
      else {
         supportOfDL_PRS_RSRP_MeasFR1_r16 = null;
      }

      // decode supportOfDL_PRS_RSRP_MeasFR2_r16

      if (supportOfDL_PRS_RSRP_MeasFR2_r16Present) {
         buffer.getContext().eventDispatcher.startElement("supportOfDL_PRS_RSRP_MeasFR2_r16", -1);

         int tval = NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16.decodeEnumValue (buffer);
         supportOfDL_PRS_RSRP_MeasFR2_r16 = NR_DL_TDOA_MeasurementCapability_r16_supportOfDL_PRS_RSRP_MeasFR2_r16.valueOf (tval);
         buffer.getContext().eventDispatcher.endElement("supportOfDL_PRS_RSRP_MeasFR2_r16", -1);
      }
      else {
         supportOfDL_PRS_RSRP_MeasFR2_r16 = null;
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

      buffer.encodeBit ((supportOfDL_PRS_RSRP_MeasFR1_r16 != null), null);
      buffer.encodeBit ((supportOfDL_PRS_RSRP_MeasFR2_r16 != null), null);

      // encode dl_RSTD_MeasurementPerPairOfTRP_FR1_r16

      if (dl_RSTD_MeasurementPerPairOfTRP_FR1_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("dl_RSTD_MeasurementPerPairOfTRP_FR1_r16", -1);

         dl_RSTD_MeasurementPerPairOfTRP_FR1_r16.encode (buffer, 1, 4);

         buffer.getContext().eventDispatcher.endElement("dl_RSTD_MeasurementPerPairOfTRP_FR1_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("dl_RSTD_MeasurementPerPairOfTRP_FR1_r16");

      // encode dl_RSTD_MeasurementPerPairOfTRP_FR2_r16

      if (dl_RSTD_MeasurementPerPairOfTRP_FR2_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("dl_RSTD_MeasurementPerPairOfTRP_FR2_r16", -1);

         dl_RSTD_MeasurementPerPairOfTRP_FR2_r16.encode (buffer, 1, 4);

         buffer.getContext().eventDispatcher.endElement("dl_RSTD_MeasurementPerPairOfTRP_FR2_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("dl_RSTD_MeasurementPerPairOfTRP_FR2_r16");

      // encode supportOfDL_PRS_RSRP_MeasFR1_r16

      if (supportOfDL_PRS_RSRP_MeasFR1_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("supportOfDL_PRS_RSRP_MeasFR1_r16", -1);

         supportOfDL_PRS_RSRP_MeasFR1_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("supportOfDL_PRS_RSRP_MeasFR1_r16", -1);
      }

      // encode supportOfDL_PRS_RSRP_MeasFR2_r16

      if (supportOfDL_PRS_RSRP_MeasFR2_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("supportOfDL_PRS_RSRP_MeasFR2_r16", -1);

         supportOfDL_PRS_RSRP_MeasFR2_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("supportOfDL_PRS_RSRP_MeasFR2_r16", -1);
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
