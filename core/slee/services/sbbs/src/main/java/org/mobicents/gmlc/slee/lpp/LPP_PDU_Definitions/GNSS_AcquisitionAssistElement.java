/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_AcquisitionAssistElement extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-AcquisitionAssistElement";
   }

   protected SV_ID svID;
   protected Asn1Integer doppler0;
   protected Asn1Integer doppler1;
   protected Asn1Integer dopplerUncertainty;
   protected Asn1Integer codePhase;
   protected Asn1Integer intCodePhase;
   protected Asn1Integer codePhaseSearchWindow;
   protected Asn1Integer azimuth;
   protected Asn1Integer elevation;
   protected Asn1Boolean codePhase1023;  // optional
   protected GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10 dopplerUncertaintyExt_r10 = null;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_AcquisitionAssistElement () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_AcquisitionAssistElement (
      SV_ID svID_,
      Asn1Integer doppler0_,
      Asn1Integer doppler1_,
      Asn1Integer dopplerUncertainty_,
      Asn1Integer codePhase_,
      Asn1Integer intCodePhase_,
      Asn1Integer codePhaseSearchWindow_,
      Asn1Integer azimuth_,
      Asn1Integer elevation_,
      Asn1Boolean codePhase1023_,
      GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10 dopplerUncertaintyExt_r10_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setDoppler0 (doppler0_);
      setDoppler1 (doppler1_);
      setDopplerUncertainty (dopplerUncertainty_);
      setCodePhase (codePhase_);
      setIntCodePhase (intCodePhase_);
      setCodePhaseSearchWindow (codePhaseSearchWindow_);
      setAzimuth (azimuth_);
      setElevation (elevation_);
      setCodePhase1023 (codePhase1023_);
      setDopplerUncertaintyExt_r10 (dopplerUncertaintyExt_r10_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_AcquisitionAssistElement (
      SV_ID svID_,
      Asn1Integer doppler0_,
      Asn1Integer doppler1_,
      Asn1Integer dopplerUncertainty_,
      Asn1Integer codePhase_,
      Asn1Integer intCodePhase_,
      Asn1Integer codePhaseSearchWindow_,
      Asn1Integer azimuth_,
      Asn1Integer elevation_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setDoppler0 (doppler0_);
      setDoppler1 (doppler1_);
      setDopplerUncertainty (dopplerUncertainty_);
      setCodePhase (codePhase_);
      setIntCodePhase (intCodePhase_);
      setCodePhaseSearchWindow (codePhaseSearchWindow_);
      setAzimuth (azimuth_);
      setElevation (elevation_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_AcquisitionAssistElement (SV_ID svID_,
      long doppler0_,
      long doppler1_,
      long dopplerUncertainty_,
      long codePhase_,
      long intCodePhase_,
      long codePhaseSearchWindow_,
      long azimuth_,
      long elevation_,
      boolean codePhase1023_,
      GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10 dopplerUncertaintyExt_r10_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setDoppler0 (doppler0_);
      setDoppler1 (doppler1_);
      setDopplerUncertainty (dopplerUncertainty_);
      setCodePhase (codePhase_);
      setIntCodePhase (intCodePhase_);
      setCodePhaseSearchWindow (codePhaseSearchWindow_);
      setAzimuth (azimuth_);
      setElevation (elevation_);
      setCodePhase1023 (codePhase1023_);
      setDopplerUncertaintyExt_r10 (dopplerUncertaintyExt_r10_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public GNSS_AcquisitionAssistElement (
      SV_ID svID_,
      long doppler0_,
      long doppler1_,
      long dopplerUncertainty_,
      long codePhase_,
      long intCodePhase_,
      long codePhaseSearchWindow_,
      long azimuth_,
      long elevation_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setDoppler0 (doppler0_);
      setDoppler1 (doppler1_);
      setDopplerUncertainty (dopplerUncertainty_);
      setCodePhase (codePhase_);
      setIntCodePhase (intCodePhase_);
      setCodePhaseSearchWindow (codePhaseSearchWindow_);
      setAzimuth (azimuth_);
      setElevation (elevation_);
   }

   public void init () {
      svID = null;
      doppler0 = null;
      doppler1 = null;
      dopplerUncertainty = null;
      codePhase = null;
      intCodePhase = null;
      codePhaseSearchWindow = null;
      azimuth = null;
      elevation = null;
      codePhase1023 = null;
      dopplerUncertaintyExt_r10 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_AcquisitionAssistElement) ) return false;

      GNSS_AcquisitionAssistElement rhs = (GNSS_AcquisitionAssistElement) obj;

      if (svID == null) {
         if (rhs.svID != null) return false;
      }
      else {
         if (!svID.equals(rhs.svID)) {
            return false;
         }
      }

      if (doppler0 == null) {
         if (rhs.doppler0 != null) return false;
      }
      else {
         if (!doppler0.equals(rhs.doppler0)) {
            return false;
         }
      }

      if (doppler1 == null) {
         if (rhs.doppler1 != null) return false;
      }
      else {
         if (!doppler1.equals(rhs.doppler1)) {
            return false;
         }
      }

      if (dopplerUncertainty == null) {
         if (rhs.dopplerUncertainty != null) return false;
      }
      else {
         if (!dopplerUncertainty.equals(rhs.dopplerUncertainty)) {
            return false;
         }
      }

      if (codePhase == null) {
         if (rhs.codePhase != null) return false;
      }
      else {
         if (!codePhase.equals(rhs.codePhase)) {
            return false;
         }
      }

      if (intCodePhase == null) {
         if (rhs.intCodePhase != null) return false;
      }
      else {
         if (!intCodePhase.equals(rhs.intCodePhase)) {
            return false;
         }
      }

      if (codePhaseSearchWindow == null) {
         if (rhs.codePhaseSearchWindow != null) return false;
      }
      else {
         if (!codePhaseSearchWindow.equals(rhs.codePhaseSearchWindow)) {
            return false;
         }
      }

      if (azimuth == null) {
         if (rhs.azimuth != null) return false;
      }
      else {
         if (!azimuth.equals(rhs.azimuth)) {
            return false;
         }
      }

      if (elevation == null) {
         if (rhs.elevation != null) return false;
      }
      else {
         if (!elevation.equals(rhs.elevation)) {
            return false;
         }
      }

      if (codePhase1023 == null) {
         if (rhs.codePhase1023 != null) return false;
      }
      else {
         if (!codePhase1023.equals(rhs.codePhase1023)) {
            return false;
         }
      }

      if (dopplerUncertaintyExt_r10 == null) {
         if (rhs.dopplerUncertaintyExt_r10 != null) return false;
      }
      else {
         if (!dopplerUncertaintyExt_r10.equals(rhs.dopplerUncertaintyExt_r10)) {
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

      if (svID != null) __code = 31*__code + svID.hashCode();
      if (doppler0 != null) __code = 31*__code + doppler0.hashCode();
      if (doppler1 != null) __code = 31*__code + doppler1.hashCode();
      if (dopplerUncertainty != null) __code = 31*__code + dopplerUncertainty.hashCode();
      if (codePhase != null) __code = 31*__code + codePhase.hashCode();
      if (intCodePhase != null) __code = 31*__code + intCodePhase.hashCode();
      if (codePhaseSearchWindow != null) __code = 31*__code + codePhaseSearchWindow.hashCode();
      if (azimuth != null) __code = 31*__code + azimuth.hashCode();
      if (elevation != null) __code = 31*__code + elevation.hashCode();
      if (codePhase1023 != null) __code = 31*__code + codePhase1023.hashCode();
      if (dopplerUncertaintyExt_r10 != null) __code = 31*__code + dopplerUncertaintyExt_r10.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for SvID
    */
   public SV_ID getSvID () {
      return svID;
   }

   public void setSvID (SV_ID value) {
      this.svID = value;
   }

   /**
    * Accessor/mutator methods for Doppler0
    */
   public Asn1Integer getDoppler0 () {
      return doppler0;
   }

   public void setDoppler0 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -2048 && value.value <= 2047))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.doppler0 = value;
   }

   public void setDoppler0 (long value) 
      throws Asn1Exception
   {
      setDoppler0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Doppler1
    */
   public Asn1Integer getDoppler1 () {
      return doppler1;
   }

   public void setDoppler1 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 63))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.doppler1 = value;
   }

   public void setDoppler1 (long value) 
      throws Asn1Exception
   {
      setDoppler1 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for DopplerUncertainty
    */
   public Asn1Integer getDopplerUncertainty () {
      return dopplerUncertainty;
   }

   public void setDopplerUncertainty (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 4))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dopplerUncertainty = value;
   }

   public void setDopplerUncertainty (long value) 
      throws Asn1Exception
   {
      setDopplerUncertainty (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for CodePhase
    */
   public Asn1Integer getCodePhase () {
      return codePhase;
   }

   public void setCodePhase (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1022))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.codePhase = value;
   }

   public void setCodePhase (long value) 
      throws Asn1Exception
   {
      setCodePhase (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for IntCodePhase
    */
   public Asn1Integer getIntCodePhase () {
      return intCodePhase;
   }

   public void setIntCodePhase (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.intCodePhase = value;
   }

   public void setIntCodePhase (long value) 
      throws Asn1Exception
   {
      setIntCodePhase (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for CodePhaseSearchWindow
    */
   public Asn1Integer getCodePhaseSearchWindow () {
      return codePhaseSearchWindow;
   }

   public void setCodePhaseSearchWindow (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 31))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.codePhaseSearchWindow = value;
   }

   public void setCodePhaseSearchWindow (long value) 
      throws Asn1Exception
   {
      setCodePhaseSearchWindow (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Azimuth
    */
   public Asn1Integer getAzimuth () {
      return azimuth;
   }

   public void setAzimuth (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 511))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.azimuth = value;
   }

   public void setAzimuth (long value) 
      throws Asn1Exception
   {
      setAzimuth (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Elevation
    */
   public Asn1Integer getElevation () {
      return elevation;
   }

   public void setElevation (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.elevation = value;
   }

   public void setElevation (long value) 
      throws Asn1Exception
   {
      setElevation (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for CodePhase1023
    */
   public Asn1Boolean getCodePhase1023 () {
      return codePhase1023;
   }

   public void setCodePhase1023 (Asn1Boolean value) {
      this.codePhase1023 = value;
   }

   public void setCodePhase1023 (boolean value) {
      setCodePhase1023 (new Asn1Boolean(value));
   }
   public boolean hasCodePhase1023 () {
      return (codePhase1023 != null);
   }

   /**
    * Accessor/mutator methods for DopplerUncertaintyExt_r10
    */
   public GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10 getDopplerUncertaintyExt_r10 () {
      return dopplerUncertaintyExt_r10;
   }

   public void setDopplerUncertaintyExt_r10 (GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10 value) {
      this.dopplerUncertaintyExt_r10 = value;
   }

   public boolean hasDopplerUncertaintyExt_r10 () {
      return (dopplerUncertaintyExt_r10 != null);
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

   public int getElementCount() { return 12; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return svID;
         case 1: return doppler0;
         case 2: return doppler1;
         case 3: return dopplerUncertainty;
         case 4: return codePhase;
         case 5: return intCodePhase;
         case 6: return codePhaseSearchWindow;
         case 7: return azimuth;
         case 8: return elevation;
         case 9: return codePhase1023;
         case 10: return dopplerUncertaintyExt_r10;
         case 11: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "svID";
         case 1: return "doppler0";
         case 2: return "doppler1";
         case 3: return "dopplerUncertainty";
         case 4: return "codePhase";
         case 5: return "intCodePhase";
         case 6: return "codePhaseSearchWindow";
         case 7: return "azimuth";
         case 8: return "elevation";
         case 9: return "codePhase1023";
         case 10: return "dopplerUncertaintyExt-r10";
         case 11: return null;
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

      // decode svID

      buffer.getContext().eventDispatcher.startElement("svID", -1);

      svID = new SV_ID();
      svID.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("svID", -1);

      // decode doppler0

      buffer.getContext().eventDispatcher.startElement("doppler0", -1);

      doppler0 = new Asn1Integer();
      doppler0.decode (buffer, -2048, 2047);
      buffer.getContext().eventDispatcher.endElement("doppler0", -1);

      // decode doppler1

      buffer.getContext().eventDispatcher.startElement("doppler1", -1);

      doppler1 = new Asn1Integer();
      doppler1.decode (buffer, 0, 63);
      buffer.getContext().eventDispatcher.endElement("doppler1", -1);

      // decode dopplerUncertainty

      buffer.getContext().eventDispatcher.startElement("dopplerUncertainty", -1);

      dopplerUncertainty = new Asn1Integer();
      dopplerUncertainty.decode (buffer, 0, 4);
      buffer.getContext().eventDispatcher.endElement("dopplerUncertainty", -1);

      // decode codePhase

      buffer.getContext().eventDispatcher.startElement("codePhase", -1);

      codePhase = new Asn1Integer();
      codePhase.decode (buffer, 0, 1022);
      buffer.getContext().eventDispatcher.endElement("codePhase", -1);

      // decode intCodePhase

      buffer.getContext().eventDispatcher.startElement("intCodePhase", -1);

      intCodePhase = new Asn1Integer();
      intCodePhase.decode (buffer, 0, 127);
      buffer.getContext().eventDispatcher.endElement("intCodePhase", -1);

      // decode codePhaseSearchWindow

      buffer.getContext().eventDispatcher.startElement("codePhaseSearchWindow", -1);

      codePhaseSearchWindow = new Asn1Integer();
      codePhaseSearchWindow.decode (buffer, 0, 31);
      buffer.getContext().eventDispatcher.endElement("codePhaseSearchWindow", -1);

      // decode azimuth

      buffer.getContext().eventDispatcher.startElement("azimuth", -1);

      azimuth = new Asn1Integer();
      azimuth.decode (buffer, 0, 511);
      buffer.getContext().eventDispatcher.endElement("azimuth", -1);

      // decode elevation

      buffer.getContext().eventDispatcher.startElement("elevation", -1);

      elevation = new Asn1Integer();
      elevation.decode (buffer, 0, 127);
      buffer.getContext().eventDispatcher.endElement("elevation", -1);

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("codePhase1023Present");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("dopplerUncertaintyExt_r10Present");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode codePhase1023

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("codePhase1023", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            codePhase1023 = new Asn1Boolean();
            codePhase1023.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("codePhase1023", -1);

         }
         else {
            codePhase1023 = null;
         }

         // decode dopplerUncertaintyExt_r10

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("dopplerUncertaintyExt_r10", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10.decodeEnumValue (buffer);
               dopplerUncertaintyExt_r10 = GNSS_AcquisitionAssistElement_dopplerUncertaintyExt_r10.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("dopplerUncertaintyExt_r10", -1);

         }
         else {
            dopplerUncertaintyExt_r10 = null;
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

      boolean extbit = ((codePhase1023 != null) ||
      (dopplerUncertaintyExt_r10 != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode svID

      if (svID != null) {
         buffer.getContext().eventDispatcher.startElement("svID", -1);

         svID.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("svID", -1);
      }
      else throw new Asn1MissingRequiredException ("svID");

      // encode doppler0

      if (doppler0 != null) {
         buffer.getContext().eventDispatcher.startElement("doppler0", -1);

         doppler0.encode (buffer, -2048, 2047);

         buffer.getContext().eventDispatcher.endElement("doppler0", -1);
      }
      else throw new Asn1MissingRequiredException ("doppler0");

      // encode doppler1

      if (doppler1 != null) {
         buffer.getContext().eventDispatcher.startElement("doppler1", -1);

         doppler1.encode (buffer, 0, 63);

         buffer.getContext().eventDispatcher.endElement("doppler1", -1);
      }
      else throw new Asn1MissingRequiredException ("doppler1");

      // encode dopplerUncertainty

      if (dopplerUncertainty != null) {
         buffer.getContext().eventDispatcher.startElement("dopplerUncertainty", -1);

         dopplerUncertainty.encode (buffer, 0, 4);

         buffer.getContext().eventDispatcher.endElement("dopplerUncertainty", -1);
      }
      else throw new Asn1MissingRequiredException ("dopplerUncertainty");

      // encode codePhase

      if (codePhase != null) {
         buffer.getContext().eventDispatcher.startElement("codePhase", -1);

         codePhase.encode (buffer, 0, 1022);

         buffer.getContext().eventDispatcher.endElement("codePhase", -1);
      }
      else throw new Asn1MissingRequiredException ("codePhase");

      // encode intCodePhase

      if (intCodePhase != null) {
         buffer.getContext().eventDispatcher.startElement("intCodePhase", -1);

         intCodePhase.encode (buffer, 0, 127);

         buffer.getContext().eventDispatcher.endElement("intCodePhase", -1);
      }
      else throw new Asn1MissingRequiredException ("intCodePhase");

      // encode codePhaseSearchWindow

      if (codePhaseSearchWindow != null) {
         buffer.getContext().eventDispatcher.startElement("codePhaseSearchWindow", -1);

         codePhaseSearchWindow.encode (buffer, 0, 31);

         buffer.getContext().eventDispatcher.endElement("codePhaseSearchWindow", -1);
      }
      else throw new Asn1MissingRequiredException ("codePhaseSearchWindow");

      // encode azimuth

      if (azimuth != null) {
         buffer.getContext().eventDispatcher.startElement("azimuth", -1);

         azimuth.encode (buffer, 0, 511);

         buffer.getContext().eventDispatcher.endElement("azimuth", -1);
      }
      else throw new Asn1MissingRequiredException ("azimuth");

      // encode elevation

      if (elevation != null) {
         buffer.getContext().eventDispatcher.startElement("elevation", -1);

         elevation.encode (buffer, 0, 127);

         buffer.getContext().eventDispatcher.endElement("elevation", -1);
      }
      else throw new Asn1MissingRequiredException ("elevation");

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 2;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((codePhase1023 != null), null);
         buffer.encodeBit ((dopplerUncertaintyExt_r10 != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // codePhase1023

         if (codePhase1023 != null) {
            buffer.reset();
            codePhase1023.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("codePhase1023", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("codePhase1023", -1);
         }

         // dopplerUncertaintyExt_r10

         if (dopplerUncertaintyExt_r10 != null) {
            buffer.reset();
            dopplerUncertaintyExt_r10.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("dopplerUncertaintyExt_r10", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("dopplerUncertaintyExt_r10", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
