/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NavModelKeplerianSet extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NavModelKeplerianSet";
   }

   protected Asn1Integer keplerToe;
   protected Asn1Integer keplerW;
   protected Asn1Integer keplerDeltaN;
   protected Asn1Integer keplerM0;
   protected Asn1Integer keplerOmegaDot;
   protected Asn1Integer keplerE;
   protected Asn1Integer keplerIDot;
   protected Asn1Integer keplerAPowerHalf;
   protected Asn1Integer keplerI0;
   protected Asn1Integer keplerOmega0;
   protected Asn1Integer keplerCrs;
   protected Asn1Integer keplerCis;
   protected Asn1Integer keplerCus;
   protected Asn1Integer keplerCrc;
   protected Asn1Integer keplerCic;
   protected Asn1Integer keplerCuc;
   protected Asn1OpenExt extElem1;

   public NavModelKeplerianSet () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NavModelKeplerianSet (
      Asn1Integer keplerToe_,
      Asn1Integer keplerW_,
      Asn1Integer keplerDeltaN_,
      Asn1Integer keplerM0_,
      Asn1Integer keplerOmegaDot_,
      Asn1Integer keplerE_,
      Asn1Integer keplerIDot_,
      Asn1Integer keplerAPowerHalf_,
      Asn1Integer keplerI0_,
      Asn1Integer keplerOmega0_,
      Asn1Integer keplerCrs_,
      Asn1Integer keplerCis_,
      Asn1Integer keplerCus_,
      Asn1Integer keplerCrc_,
      Asn1Integer keplerCic_,
      Asn1Integer keplerCuc_
   ) throws Asn1Exception {
      super();
      setKeplerToe (keplerToe_);
      setKeplerW (keplerW_);
      setKeplerDeltaN (keplerDeltaN_);
      setKeplerM0 (keplerM0_);
      setKeplerOmegaDot (keplerOmegaDot_);
      setKeplerE (keplerE_);
      setKeplerIDot (keplerIDot_);
      setKeplerAPowerHalf (keplerAPowerHalf_);
      setKeplerI0 (keplerI0_);
      setKeplerOmega0 (keplerOmega0_);
      setKeplerCrs (keplerCrs_);
      setKeplerCis (keplerCis_);
      setKeplerCus (keplerCus_);
      setKeplerCrc (keplerCrc_);
      setKeplerCic (keplerCic_);
      setKeplerCuc (keplerCuc_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NavModelKeplerianSet (long keplerToe_,
      long keplerW_,
      long keplerDeltaN_,
      long keplerM0_,
      long keplerOmegaDot_,
      long keplerE_,
      long keplerIDot_,
      long keplerAPowerHalf_,
      long keplerI0_,
      long keplerOmega0_,
      long keplerCrs_,
      long keplerCis_,
      long keplerCus_,
      long keplerCrc_,
      long keplerCic_,
      long keplerCuc_
   ) throws Asn1Exception {
      super();
      setKeplerToe (keplerToe_);
      setKeplerW (keplerW_);
      setKeplerDeltaN (keplerDeltaN_);
      setKeplerM0 (keplerM0_);
      setKeplerOmegaDot (keplerOmegaDot_);
      setKeplerE (keplerE_);
      setKeplerIDot (keplerIDot_);
      setKeplerAPowerHalf (keplerAPowerHalf_);
      setKeplerI0 (keplerI0_);
      setKeplerOmega0 (keplerOmega0_);
      setKeplerCrs (keplerCrs_);
      setKeplerCis (keplerCis_);
      setKeplerCus (keplerCus_);
      setKeplerCrc (keplerCrc_);
      setKeplerCic (keplerCic_);
      setKeplerCuc (keplerCuc_);
   }

   public void init () {
      keplerToe = null;
      keplerW = null;
      keplerDeltaN = null;
      keplerM0 = null;
      keplerOmegaDot = null;
      keplerE = null;
      keplerIDot = null;
      keplerAPowerHalf = null;
      keplerI0 = null;
      keplerOmega0 = null;
      keplerCrs = null;
      keplerCis = null;
      keplerCus = null;
      keplerCrc = null;
      keplerCic = null;
      keplerCuc = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NavModelKeplerianSet) ) return false;

      NavModelKeplerianSet rhs = (NavModelKeplerianSet) obj;

      if (keplerToe == null) {
         if (rhs.keplerToe != null) return false;
      }
      else {
         if (!keplerToe.equals(rhs.keplerToe)) {
            return false;
         }
      }

      if (keplerW == null) {
         if (rhs.keplerW != null) return false;
      }
      else {
         if (!keplerW.equals(rhs.keplerW)) {
            return false;
         }
      }

      if (keplerDeltaN == null) {
         if (rhs.keplerDeltaN != null) return false;
      }
      else {
         if (!keplerDeltaN.equals(rhs.keplerDeltaN)) {
            return false;
         }
      }

      if (keplerM0 == null) {
         if (rhs.keplerM0 != null) return false;
      }
      else {
         if (!keplerM0.equals(rhs.keplerM0)) {
            return false;
         }
      }

      if (keplerOmegaDot == null) {
         if (rhs.keplerOmegaDot != null) return false;
      }
      else {
         if (!keplerOmegaDot.equals(rhs.keplerOmegaDot)) {
            return false;
         }
      }

      if (keplerE == null) {
         if (rhs.keplerE != null) return false;
      }
      else {
         if (!keplerE.equals(rhs.keplerE)) {
            return false;
         }
      }

      if (keplerIDot == null) {
         if (rhs.keplerIDot != null) return false;
      }
      else {
         if (!keplerIDot.equals(rhs.keplerIDot)) {
            return false;
         }
      }

      if (keplerAPowerHalf == null) {
         if (rhs.keplerAPowerHalf != null) return false;
      }
      else {
         if (!keplerAPowerHalf.equals(rhs.keplerAPowerHalf)) {
            return false;
         }
      }

      if (keplerI0 == null) {
         if (rhs.keplerI0 != null) return false;
      }
      else {
         if (!keplerI0.equals(rhs.keplerI0)) {
            return false;
         }
      }

      if (keplerOmega0 == null) {
         if (rhs.keplerOmega0 != null) return false;
      }
      else {
         if (!keplerOmega0.equals(rhs.keplerOmega0)) {
            return false;
         }
      }

      if (keplerCrs == null) {
         if (rhs.keplerCrs != null) return false;
      }
      else {
         if (!keplerCrs.equals(rhs.keplerCrs)) {
            return false;
         }
      }

      if (keplerCis == null) {
         if (rhs.keplerCis != null) return false;
      }
      else {
         if (!keplerCis.equals(rhs.keplerCis)) {
            return false;
         }
      }

      if (keplerCus == null) {
         if (rhs.keplerCus != null) return false;
      }
      else {
         if (!keplerCus.equals(rhs.keplerCus)) {
            return false;
         }
      }

      if (keplerCrc == null) {
         if (rhs.keplerCrc != null) return false;
      }
      else {
         if (!keplerCrc.equals(rhs.keplerCrc)) {
            return false;
         }
      }

      if (keplerCic == null) {
         if (rhs.keplerCic != null) return false;
      }
      else {
         if (!keplerCic.equals(rhs.keplerCic)) {
            return false;
         }
      }

      if (keplerCuc == null) {
         if (rhs.keplerCuc != null) return false;
      }
      else {
         if (!keplerCuc.equals(rhs.keplerCuc)) {
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

      if (keplerToe != null) __code = 31*__code + keplerToe.hashCode();
      if (keplerW != null) __code = 31*__code + keplerW.hashCode();
      if (keplerDeltaN != null) __code = 31*__code + keplerDeltaN.hashCode();
      if (keplerM0 != null) __code = 31*__code + keplerM0.hashCode();
      if (keplerOmegaDot != null) __code = 31*__code + keplerOmegaDot.hashCode();
      if (keplerE != null) __code = 31*__code + keplerE.hashCode();
      if (keplerIDot != null) __code = 31*__code + keplerIDot.hashCode();
      if (keplerAPowerHalf != null) __code = 31*__code + keplerAPowerHalf.hashCode();
      if (keplerI0 != null) __code = 31*__code + keplerI0.hashCode();
      if (keplerOmega0 != null) __code = 31*__code + keplerOmega0.hashCode();
      if (keplerCrs != null) __code = 31*__code + keplerCrs.hashCode();
      if (keplerCis != null) __code = 31*__code + keplerCis.hashCode();
      if (keplerCus != null) __code = 31*__code + keplerCus.hashCode();
      if (keplerCrc != null) __code = 31*__code + keplerCrc.hashCode();
      if (keplerCic != null) __code = 31*__code + keplerCic.hashCode();
      if (keplerCuc != null) __code = 31*__code + keplerCuc.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for KeplerToe
    */
   public Asn1Integer getKeplerToe () {
      return keplerToe;
   }

   public void setKeplerToe (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 16383))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerToe = value;
   }

   public void setKeplerToe (long value) 
      throws Asn1Exception
   {
      setKeplerToe (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerW
    */
   public Asn1Integer getKeplerW () {
      return keplerW;
   }

   public void setKeplerW (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerW = value;
   }

   public void setKeplerW (long value) 
      throws Asn1Exception
   {
      setKeplerW (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerDeltaN
    */
   public Asn1Integer getKeplerDeltaN () {
      return keplerDeltaN;
   }

   public void setKeplerDeltaN (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerDeltaN = value;
   }

   public void setKeplerDeltaN (long value) 
      throws Asn1Exception
   {
      setKeplerDeltaN (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerM0
    */
   public Asn1Integer getKeplerM0 () {
      return keplerM0;
   }

   public void setKeplerM0 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerM0 = value;
   }

   public void setKeplerM0 (long value) 
      throws Asn1Exception
   {
      setKeplerM0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerOmegaDot
    */
   public Asn1Integer getKeplerOmegaDot () {
      return keplerOmegaDot;
   }

   public void setKeplerOmegaDot (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8388608 && value.value <= 8388607))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerOmegaDot = value;
   }

   public void setKeplerOmegaDot (long value) 
      throws Asn1Exception
   {
      setKeplerOmegaDot (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerE
    */
   public Asn1Integer getKeplerE () {
      return keplerE;
   }

   public void setKeplerE (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerE = value;
   }

   public void setKeplerE (long value) 
      throws Asn1Exception
   {
      setKeplerE (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerIDot
    */
   public Asn1Integer getKeplerIDot () {
      return keplerIDot;
   }

   public void setKeplerIDot (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8192 && value.value <= 8191))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerIDot = value;
   }

   public void setKeplerIDot (long value) 
      throws Asn1Exception
   {
      setKeplerIDot (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerAPowerHalf
    */
   public Asn1Integer getKeplerAPowerHalf () {
      return keplerAPowerHalf;
   }

   public void setKeplerAPowerHalf (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerAPowerHalf = value;
   }

   public void setKeplerAPowerHalf (long value) 
      throws Asn1Exception
   {
      setKeplerAPowerHalf (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerI0
    */
   public Asn1Integer getKeplerI0 () {
      return keplerI0;
   }

   public void setKeplerI0 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerI0 = value;
   }

   public void setKeplerI0 (long value) 
      throws Asn1Exception
   {
      setKeplerI0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerOmega0
    */
   public Asn1Integer getKeplerOmega0 () {
      return keplerOmega0;
   }

   public void setKeplerOmega0 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.keplerOmega0 = value;
   }

   public void setKeplerOmega0 (long value) 
      throws Asn1Exception
   {
      setKeplerOmega0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCrs
    */
   public Asn1Integer getKeplerCrs () {
      return keplerCrs;
   }

   public void setKeplerCrs (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCrs = value;
   }

   public void setKeplerCrs (long value) 
      throws Asn1Exception
   {
      setKeplerCrs (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCis
    */
   public Asn1Integer getKeplerCis () {
      return keplerCis;
   }

   public void setKeplerCis (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCis = value;
   }

   public void setKeplerCis (long value) 
      throws Asn1Exception
   {
      setKeplerCis (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCus
    */
   public Asn1Integer getKeplerCus () {
      return keplerCus;
   }

   public void setKeplerCus (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCus = value;
   }

   public void setKeplerCus (long value) 
      throws Asn1Exception
   {
      setKeplerCus (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCrc
    */
   public Asn1Integer getKeplerCrc () {
      return keplerCrc;
   }

   public void setKeplerCrc (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCrc = value;
   }

   public void setKeplerCrc (long value) 
      throws Asn1Exception
   {
      setKeplerCrc (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCic
    */
   public Asn1Integer getKeplerCic () {
      return keplerCic;
   }

   public void setKeplerCic (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCic = value;
   }

   public void setKeplerCic (long value) 
      throws Asn1Exception
   {
      setKeplerCic (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for KeplerCuc
    */
   public Asn1Integer getKeplerCuc () {
      return keplerCuc;
   }

   public void setKeplerCuc (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.keplerCuc = value;
   }

   public void setKeplerCuc (long value) 
      throws Asn1Exception
   {
      setKeplerCuc (new Asn1Integer(value));
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

   public int getElementCount() { return 17; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return keplerToe;
         case 1: return keplerW;
         case 2: return keplerDeltaN;
         case 3: return keplerM0;
         case 4: return keplerOmegaDot;
         case 5: return keplerE;
         case 6: return keplerIDot;
         case 7: return keplerAPowerHalf;
         case 8: return keplerI0;
         case 9: return keplerOmega0;
         case 10: return keplerCrs;
         case 11: return keplerCis;
         case 12: return keplerCus;
         case 13: return keplerCrc;
         case 14: return keplerCic;
         case 15: return keplerCuc;
         case 16: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "keplerToe";
         case 1: return "keplerW";
         case 2: return "keplerDeltaN";
         case 3: return "keplerM0";
         case 4: return "keplerOmegaDot";
         case 5: return "keplerE";
         case 6: return "keplerIDot";
         case 7: return "keplerAPowerHalf";
         case 8: return "keplerI0";
         case 9: return "keplerOmega0";
         case 10: return "keplerCrs";
         case 11: return "keplerCis";
         case 12: return "keplerCus";
         case 13: return "keplerCrc";
         case 14: return "keplerCic";
         case 15: return "keplerCuc";
         case 16: return null;
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // extension bit

      boolean extbit = buffer.decodeBit ("extbit");

      // decode keplerToe

      buffer.getContext().eventDispatcher.startElement("keplerToe", -1);

      keplerToe = new Asn1Integer();
      keplerToe.decode (buffer, 0, 16383);
      buffer.getContext().eventDispatcher.endElement("keplerToe", -1);

      // decode keplerW

      buffer.getContext().eventDispatcher.startElement("keplerW", -1);

      keplerW = new Asn1Integer();
      keplerW.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("keplerW", -1);

      // decode keplerDeltaN

      buffer.getContext().eventDispatcher.startElement("keplerDeltaN", -1);

      keplerDeltaN = new Asn1Integer();
      keplerDeltaN.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerDeltaN", -1);

      // decode keplerM0

      buffer.getContext().eventDispatcher.startElement("keplerM0", -1);

      keplerM0 = new Asn1Integer();
      keplerM0.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("keplerM0", -1);

      // decode keplerOmegaDot

      buffer.getContext().eventDispatcher.startElement("keplerOmegaDot", -1);

      keplerOmegaDot = new Asn1Integer();
      keplerOmegaDot.decode (buffer, -8388608, 8388607);
      buffer.getContext().eventDispatcher.endElement("keplerOmegaDot", -1);

      // decode keplerE

      buffer.getContext().eventDispatcher.startElement("keplerE", -1);

      keplerE = new Asn1Integer();
      keplerE.decode (buffer, 0, 4294967295L);
      buffer.getContext().eventDispatcher.endElement("keplerE", -1);

      // decode keplerIDot

      buffer.getContext().eventDispatcher.startElement("keplerIDot", -1);

      keplerIDot = new Asn1Integer();
      keplerIDot.decode (buffer, -8192, 8191);
      buffer.getContext().eventDispatcher.endElement("keplerIDot", -1);

      // decode keplerAPowerHalf

      buffer.getContext().eventDispatcher.startElement("keplerAPowerHalf", -1);

      keplerAPowerHalf = new Asn1Integer();
      keplerAPowerHalf.decode (buffer, 0, 4294967295L);
      buffer.getContext().eventDispatcher.endElement("keplerAPowerHalf", -1);

      // decode keplerI0

      buffer.getContext().eventDispatcher.startElement("keplerI0", -1);

      keplerI0 = new Asn1Integer();
      keplerI0.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("keplerI0", -1);

      // decode keplerOmega0

      buffer.getContext().eventDispatcher.startElement("keplerOmega0", -1);

      keplerOmega0 = new Asn1Integer();
      keplerOmega0.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("keplerOmega0", -1);

      // decode keplerCrs

      buffer.getContext().eventDispatcher.startElement("keplerCrs", -1);

      keplerCrs = new Asn1Integer();
      keplerCrs.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCrs", -1);

      // decode keplerCis

      buffer.getContext().eventDispatcher.startElement("keplerCis", -1);

      keplerCis = new Asn1Integer();
      keplerCis.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCis", -1);

      // decode keplerCus

      buffer.getContext().eventDispatcher.startElement("keplerCus", -1);

      keplerCus = new Asn1Integer();
      keplerCus.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCus", -1);

      // decode keplerCrc

      buffer.getContext().eventDispatcher.startElement("keplerCrc", -1);

      keplerCrc = new Asn1Integer();
      keplerCrc.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCrc", -1);

      // decode keplerCic

      buffer.getContext().eventDispatcher.startElement("keplerCic", -1);

      keplerCic = new Asn1Integer();
      keplerCic.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCic", -1);

      // decode keplerCuc

      buffer.getContext().eventDispatcher.startElement("keplerCuc", -1);

      keplerCuc = new Asn1Integer();
      keplerCuc.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("keplerCuc", -1);

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

      // encode keplerToe

      if (keplerToe != null) {
         buffer.getContext().eventDispatcher.startElement("keplerToe", -1);

         keplerToe.encode (buffer, 0, 16383);

         buffer.getContext().eventDispatcher.endElement("keplerToe", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerToe");

      // encode keplerW

      if (keplerW != null) {
         buffer.getContext().eventDispatcher.startElement("keplerW", -1);

         keplerW.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("keplerW", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerW");

      // encode keplerDeltaN

      if (keplerDeltaN != null) {
         buffer.getContext().eventDispatcher.startElement("keplerDeltaN", -1);

         keplerDeltaN.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerDeltaN", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerDeltaN");

      // encode keplerM0

      if (keplerM0 != null) {
         buffer.getContext().eventDispatcher.startElement("keplerM0", -1);

         keplerM0.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("keplerM0", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerM0");

      // encode keplerOmegaDot

      if (keplerOmegaDot != null) {
         buffer.getContext().eventDispatcher.startElement("keplerOmegaDot", -1);

         keplerOmegaDot.encode (buffer, -8388608, 8388607);

         buffer.getContext().eventDispatcher.endElement("keplerOmegaDot", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerOmegaDot");

      // encode keplerE

      if (keplerE != null) {
         buffer.getContext().eventDispatcher.startElement("keplerE", -1);

         keplerE.encode (buffer, 0, 4294967295L);

         buffer.getContext().eventDispatcher.endElement("keplerE", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerE");

      // encode keplerIDot

      if (keplerIDot != null) {
         buffer.getContext().eventDispatcher.startElement("keplerIDot", -1);

         keplerIDot.encode (buffer, -8192, 8191);

         buffer.getContext().eventDispatcher.endElement("keplerIDot", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerIDot");

      // encode keplerAPowerHalf

      if (keplerAPowerHalf != null) {
         buffer.getContext().eventDispatcher.startElement("keplerAPowerHalf", -1);

         keplerAPowerHalf.encode (buffer, 0, 4294967295L);

         buffer.getContext().eventDispatcher.endElement("keplerAPowerHalf", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerAPowerHalf");

      // encode keplerI0

      if (keplerI0 != null) {
         buffer.getContext().eventDispatcher.startElement("keplerI0", -1);

         keplerI0.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("keplerI0", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerI0");

      // encode keplerOmega0

      if (keplerOmega0 != null) {
         buffer.getContext().eventDispatcher.startElement("keplerOmega0", -1);

         keplerOmega0.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("keplerOmega0", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerOmega0");

      // encode keplerCrs

      if (keplerCrs != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCrs", -1);

         keplerCrs.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCrs", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCrs");

      // encode keplerCis

      if (keplerCis != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCis", -1);

         keplerCis.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCis", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCis");

      // encode keplerCus

      if (keplerCus != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCus", -1);

         keplerCus.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCus", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCus");

      // encode keplerCrc

      if (keplerCrc != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCrc", -1);

         keplerCrc.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCrc", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCrc");

      // encode keplerCic

      if (keplerCic != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCic", -1);

         keplerCic.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCic", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCic");

      // encode keplerCuc

      if (keplerCuc != null) {
         buffer.getContext().eventDispatcher.startElement("keplerCuc", -1);

         keplerCuc.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("keplerCuc", -1);
      }
      else throw new Asn1MissingRequiredException ("keplerCuc");

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
