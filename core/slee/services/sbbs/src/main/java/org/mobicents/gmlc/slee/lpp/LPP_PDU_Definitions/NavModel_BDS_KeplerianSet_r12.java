/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NavModel_BDS_KeplerianSet_r12 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NavModel-BDS-KeplerianSet-r12";
   }

   protected Asn1Integer bdsAODE_r12;
   protected Asn1Integer bdsURAI_r12;
   protected Asn1Integer bdsToe_r12;
   protected Asn1Integer bdsAPowerHalf_r12;
   protected Asn1Integer bdsE_r12;
   protected Asn1Integer bdsW_r12;
   protected Asn1Integer bdsDeltaN_r12;
   protected Asn1Integer bdsM0_r12;
   protected Asn1Integer bdsOmega0_r12;
   protected Asn1Integer bdsOmegaDot_r12;
   protected Asn1Integer bdsI0_r12;
   protected Asn1Integer bdsIDot_r12;
   protected Asn1Integer bdsCuc_r12;
   protected Asn1Integer bdsCus_r12;
   protected Asn1Integer bdsCrc_r12;
   protected Asn1Integer bdsCrs_r12;
   protected Asn1Integer bdsCic_r12;
   protected Asn1Integer bdsCis_r12;
   protected Asn1OpenExt extElem1;

   public NavModel_BDS_KeplerianSet_r12 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NavModel_BDS_KeplerianSet_r12 (
      Asn1Integer bdsAODE_r12_,
      Asn1Integer bdsURAI_r12_,
      Asn1Integer bdsToe_r12_,
      Asn1Integer bdsAPowerHalf_r12_,
      Asn1Integer bdsE_r12_,
      Asn1Integer bdsW_r12_,
      Asn1Integer bdsDeltaN_r12_,
      Asn1Integer bdsM0_r12_,
      Asn1Integer bdsOmega0_r12_,
      Asn1Integer bdsOmegaDot_r12_,
      Asn1Integer bdsI0_r12_,
      Asn1Integer bdsIDot_r12_,
      Asn1Integer bdsCuc_r12_,
      Asn1Integer bdsCus_r12_,
      Asn1Integer bdsCrc_r12_,
      Asn1Integer bdsCrs_r12_,
      Asn1Integer bdsCic_r12_,
      Asn1Integer bdsCis_r12_
   ) throws Asn1Exception {
      super();
      setBdsAODE_r12 (bdsAODE_r12_);
      setBdsURAI_r12 (bdsURAI_r12_);
      setBdsToe_r12 (bdsToe_r12_);
      setBdsAPowerHalf_r12 (bdsAPowerHalf_r12_);
      setBdsE_r12 (bdsE_r12_);
      setBdsW_r12 (bdsW_r12_);
      setBdsDeltaN_r12 (bdsDeltaN_r12_);
      setBdsM0_r12 (bdsM0_r12_);
      setBdsOmega0_r12 (bdsOmega0_r12_);
      setBdsOmegaDot_r12 (bdsOmegaDot_r12_);
      setBdsI0_r12 (bdsI0_r12_);
      setBdsIDot_r12 (bdsIDot_r12_);
      setBdsCuc_r12 (bdsCuc_r12_);
      setBdsCus_r12 (bdsCus_r12_);
      setBdsCrc_r12 (bdsCrc_r12_);
      setBdsCrs_r12 (bdsCrs_r12_);
      setBdsCic_r12 (bdsCic_r12_);
      setBdsCis_r12 (bdsCis_r12_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NavModel_BDS_KeplerianSet_r12 (long bdsAODE_r12_,
      long bdsURAI_r12_,
      long bdsToe_r12_,
      long bdsAPowerHalf_r12_,
      long bdsE_r12_,
      long bdsW_r12_,
      long bdsDeltaN_r12_,
      long bdsM0_r12_,
      long bdsOmega0_r12_,
      long bdsOmegaDot_r12_,
      long bdsI0_r12_,
      long bdsIDot_r12_,
      long bdsCuc_r12_,
      long bdsCus_r12_,
      long bdsCrc_r12_,
      long bdsCrs_r12_,
      long bdsCic_r12_,
      long bdsCis_r12_
   ) throws Asn1Exception {
      super();
      setBdsAODE_r12 (bdsAODE_r12_);
      setBdsURAI_r12 (bdsURAI_r12_);
      setBdsToe_r12 (bdsToe_r12_);
      setBdsAPowerHalf_r12 (bdsAPowerHalf_r12_);
      setBdsE_r12 (bdsE_r12_);
      setBdsW_r12 (bdsW_r12_);
      setBdsDeltaN_r12 (bdsDeltaN_r12_);
      setBdsM0_r12 (bdsM0_r12_);
      setBdsOmega0_r12 (bdsOmega0_r12_);
      setBdsOmegaDot_r12 (bdsOmegaDot_r12_);
      setBdsI0_r12 (bdsI0_r12_);
      setBdsIDot_r12 (bdsIDot_r12_);
      setBdsCuc_r12 (bdsCuc_r12_);
      setBdsCus_r12 (bdsCus_r12_);
      setBdsCrc_r12 (bdsCrc_r12_);
      setBdsCrs_r12 (bdsCrs_r12_);
      setBdsCic_r12 (bdsCic_r12_);
      setBdsCis_r12 (bdsCis_r12_);
   }

   public void init () {
      bdsAODE_r12 = null;
      bdsURAI_r12 = null;
      bdsToe_r12 = null;
      bdsAPowerHalf_r12 = null;
      bdsE_r12 = null;
      bdsW_r12 = null;
      bdsDeltaN_r12 = null;
      bdsM0_r12 = null;
      bdsOmega0_r12 = null;
      bdsOmegaDot_r12 = null;
      bdsI0_r12 = null;
      bdsIDot_r12 = null;
      bdsCuc_r12 = null;
      bdsCus_r12 = null;
      bdsCrc_r12 = null;
      bdsCrs_r12 = null;
      bdsCic_r12 = null;
      bdsCis_r12 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NavModel_BDS_KeplerianSet_r12) ) return false;

      NavModel_BDS_KeplerianSet_r12 rhs = (NavModel_BDS_KeplerianSet_r12) obj;

      if (bdsAODE_r12 == null) {
         if (rhs.bdsAODE_r12 != null) return false;
      }
      else {
         if (!bdsAODE_r12.equals(rhs.bdsAODE_r12)) {
            return false;
         }
      }

      if (bdsURAI_r12 == null) {
         if (rhs.bdsURAI_r12 != null) return false;
      }
      else {
         if (!bdsURAI_r12.equals(rhs.bdsURAI_r12)) {
            return false;
         }
      }

      if (bdsToe_r12 == null) {
         if (rhs.bdsToe_r12 != null) return false;
      }
      else {
         if (!bdsToe_r12.equals(rhs.bdsToe_r12)) {
            return false;
         }
      }

      if (bdsAPowerHalf_r12 == null) {
         if (rhs.bdsAPowerHalf_r12 != null) return false;
      }
      else {
         if (!bdsAPowerHalf_r12.equals(rhs.bdsAPowerHalf_r12)) {
            return false;
         }
      }

      if (bdsE_r12 == null) {
         if (rhs.bdsE_r12 != null) return false;
      }
      else {
         if (!bdsE_r12.equals(rhs.bdsE_r12)) {
            return false;
         }
      }

      if (bdsW_r12 == null) {
         if (rhs.bdsW_r12 != null) return false;
      }
      else {
         if (!bdsW_r12.equals(rhs.bdsW_r12)) {
            return false;
         }
      }

      if (bdsDeltaN_r12 == null) {
         if (rhs.bdsDeltaN_r12 != null) return false;
      }
      else {
         if (!bdsDeltaN_r12.equals(rhs.bdsDeltaN_r12)) {
            return false;
         }
      }

      if (bdsM0_r12 == null) {
         if (rhs.bdsM0_r12 != null) return false;
      }
      else {
         if (!bdsM0_r12.equals(rhs.bdsM0_r12)) {
            return false;
         }
      }

      if (bdsOmega0_r12 == null) {
         if (rhs.bdsOmega0_r12 != null) return false;
      }
      else {
         if (!bdsOmega0_r12.equals(rhs.bdsOmega0_r12)) {
            return false;
         }
      }

      if (bdsOmegaDot_r12 == null) {
         if (rhs.bdsOmegaDot_r12 != null) return false;
      }
      else {
         if (!bdsOmegaDot_r12.equals(rhs.bdsOmegaDot_r12)) {
            return false;
         }
      }

      if (bdsI0_r12 == null) {
         if (rhs.bdsI0_r12 != null) return false;
      }
      else {
         if (!bdsI0_r12.equals(rhs.bdsI0_r12)) {
            return false;
         }
      }

      if (bdsIDot_r12 == null) {
         if (rhs.bdsIDot_r12 != null) return false;
      }
      else {
         if (!bdsIDot_r12.equals(rhs.bdsIDot_r12)) {
            return false;
         }
      }

      if (bdsCuc_r12 == null) {
         if (rhs.bdsCuc_r12 != null) return false;
      }
      else {
         if (!bdsCuc_r12.equals(rhs.bdsCuc_r12)) {
            return false;
         }
      }

      if (bdsCus_r12 == null) {
         if (rhs.bdsCus_r12 != null) return false;
      }
      else {
         if (!bdsCus_r12.equals(rhs.bdsCus_r12)) {
            return false;
         }
      }

      if (bdsCrc_r12 == null) {
         if (rhs.bdsCrc_r12 != null) return false;
      }
      else {
         if (!bdsCrc_r12.equals(rhs.bdsCrc_r12)) {
            return false;
         }
      }

      if (bdsCrs_r12 == null) {
         if (rhs.bdsCrs_r12 != null) return false;
      }
      else {
         if (!bdsCrs_r12.equals(rhs.bdsCrs_r12)) {
            return false;
         }
      }

      if (bdsCic_r12 == null) {
         if (rhs.bdsCic_r12 != null) return false;
      }
      else {
         if (!bdsCic_r12.equals(rhs.bdsCic_r12)) {
            return false;
         }
      }

      if (bdsCis_r12 == null) {
         if (rhs.bdsCis_r12 != null) return false;
      }
      else {
         if (!bdsCis_r12.equals(rhs.bdsCis_r12)) {
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

      if (bdsAODE_r12 != null) __code = 31*__code + bdsAODE_r12.hashCode();
      if (bdsURAI_r12 != null) __code = 31*__code + bdsURAI_r12.hashCode();
      if (bdsToe_r12 != null) __code = 31*__code + bdsToe_r12.hashCode();
      if (bdsAPowerHalf_r12 != null) __code = 31*__code + bdsAPowerHalf_r12.hashCode();
      if (bdsE_r12 != null) __code = 31*__code + bdsE_r12.hashCode();
      if (bdsW_r12 != null) __code = 31*__code + bdsW_r12.hashCode();
      if (bdsDeltaN_r12 != null) __code = 31*__code + bdsDeltaN_r12.hashCode();
      if (bdsM0_r12 != null) __code = 31*__code + bdsM0_r12.hashCode();
      if (bdsOmega0_r12 != null) __code = 31*__code + bdsOmega0_r12.hashCode();
      if (bdsOmegaDot_r12 != null) __code = 31*__code + bdsOmegaDot_r12.hashCode();
      if (bdsI0_r12 != null) __code = 31*__code + bdsI0_r12.hashCode();
      if (bdsIDot_r12 != null) __code = 31*__code + bdsIDot_r12.hashCode();
      if (bdsCuc_r12 != null) __code = 31*__code + bdsCuc_r12.hashCode();
      if (bdsCus_r12 != null) __code = 31*__code + bdsCus_r12.hashCode();
      if (bdsCrc_r12 != null) __code = 31*__code + bdsCrc_r12.hashCode();
      if (bdsCrs_r12 != null) __code = 31*__code + bdsCrs_r12.hashCode();
      if (bdsCic_r12 != null) __code = 31*__code + bdsCic_r12.hashCode();
      if (bdsCis_r12 != null) __code = 31*__code + bdsCis_r12.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for BdsAODE_r12
    */
   public Asn1Integer getBdsAODE_r12 () {
      return bdsAODE_r12;
   }

   public void setBdsAODE_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 31))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsAODE_r12 = value;
   }

   public void setBdsAODE_r12 (long value) 
      throws Asn1Exception
   {
      setBdsAODE_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsURAI_r12
    */
   public Asn1Integer getBdsURAI_r12 () {
      return bdsURAI_r12;
   }

   public void setBdsURAI_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 15))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsURAI_r12 = value;
   }

   public void setBdsURAI_r12 (long value) 
      throws Asn1Exception
   {
      setBdsURAI_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsToe_r12
    */
   public Asn1Integer getBdsToe_r12 () {
      return bdsToe_r12;
   }

   public void setBdsToe_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsToe_r12 = value;
   }

   public void setBdsToe_r12 (long value) 
      throws Asn1Exception
   {
      setBdsToe_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsAPowerHalf_r12
    */
   public Asn1Integer getBdsAPowerHalf_r12 () {
      return bdsAPowerHalf_r12;
   }

   public void setBdsAPowerHalf_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsAPowerHalf_r12 = value;
   }

   public void setBdsAPowerHalf_r12 (long value) 
      throws Asn1Exception
   {
      setBdsAPowerHalf_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsE_r12
    */
   public Asn1Integer getBdsE_r12 () {
      return bdsE_r12;
   }

   public void setBdsE_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsE_r12 = value;
   }

   public void setBdsE_r12 (long value) 
      throws Asn1Exception
   {
      setBdsE_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsW_r12
    */
   public Asn1Integer getBdsW_r12 () {
      return bdsW_r12;
   }

   public void setBdsW_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsW_r12 = value;
   }

   public void setBdsW_r12 (long value) 
      throws Asn1Exception
   {
      setBdsW_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsDeltaN_r12
    */
   public Asn1Integer getBdsDeltaN_r12 () {
      return bdsDeltaN_r12;
   }

   public void setBdsDeltaN_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -32768 && value.value <= 32767))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsDeltaN_r12 = value;
   }

   public void setBdsDeltaN_r12 (long value) 
      throws Asn1Exception
   {
      setBdsDeltaN_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsM0_r12
    */
   public Asn1Integer getBdsM0_r12 () {
      return bdsM0_r12;
   }

   public void setBdsM0_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsM0_r12 = value;
   }

   public void setBdsM0_r12 (long value) 
      throws Asn1Exception
   {
      setBdsM0_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsOmega0_r12
    */
   public Asn1Integer getBdsOmega0_r12 () {
      return bdsOmega0_r12;
   }

   public void setBdsOmega0_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsOmega0_r12 = value;
   }

   public void setBdsOmega0_r12 (long value) 
      throws Asn1Exception
   {
      setBdsOmega0_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsOmegaDot_r12
    */
   public Asn1Integer getBdsOmegaDot_r12 () {
      return bdsOmegaDot_r12;
   }

   public void setBdsOmegaDot_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8388608 && value.value <= 8388607))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsOmegaDot_r12 = value;
   }

   public void setBdsOmegaDot_r12 (long value) 
      throws Asn1Exception
   {
      setBdsOmegaDot_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsI0_r12
    */
   public Asn1Integer getBdsI0_r12 () {
      return bdsI0_r12;
   }

   public void setBdsI0_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.bdsI0_r12 = value;
   }

   public void setBdsI0_r12 (long value) 
      throws Asn1Exception
   {
      setBdsI0_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsIDot_r12
    */
   public Asn1Integer getBdsIDot_r12 () {
      return bdsIDot_r12;
   }

   public void setBdsIDot_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8192 && value.value <= 8191))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsIDot_r12 = value;
   }

   public void setBdsIDot_r12 (long value) 
      throws Asn1Exception
   {
      setBdsIDot_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCuc_r12
    */
   public Asn1Integer getBdsCuc_r12 () {
      return bdsCuc_r12;
   }

   public void setBdsCuc_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCuc_r12 = value;
   }

   public void setBdsCuc_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCuc_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCus_r12
    */
   public Asn1Integer getBdsCus_r12 () {
      return bdsCus_r12;
   }

   public void setBdsCus_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCus_r12 = value;
   }

   public void setBdsCus_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCus_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCrc_r12
    */
   public Asn1Integer getBdsCrc_r12 () {
      return bdsCrc_r12;
   }

   public void setBdsCrc_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCrc_r12 = value;
   }

   public void setBdsCrc_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCrc_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCrs_r12
    */
   public Asn1Integer getBdsCrs_r12 () {
      return bdsCrs_r12;
   }

   public void setBdsCrs_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCrs_r12 = value;
   }

   public void setBdsCrs_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCrs_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCic_r12
    */
   public Asn1Integer getBdsCic_r12 () {
      return bdsCic_r12;
   }

   public void setBdsCic_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCic_r12 = value;
   }

   public void setBdsCic_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCic_r12 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for BdsCis_r12
    */
   public Asn1Integer getBdsCis_r12 () {
      return bdsCis_r12;
   }

   public void setBdsCis_r12 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -131072 && value.value <= 131071))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.bdsCis_r12 = value;
   }

   public void setBdsCis_r12 (long value) 
      throws Asn1Exception
   {
      setBdsCis_r12 (new Asn1Integer(value));
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

   public int getElementCount() { return 19; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return bdsAODE_r12;
         case 1: return bdsURAI_r12;
         case 2: return bdsToe_r12;
         case 3: return bdsAPowerHalf_r12;
         case 4: return bdsE_r12;
         case 5: return bdsW_r12;
         case 6: return bdsDeltaN_r12;
         case 7: return bdsM0_r12;
         case 8: return bdsOmega0_r12;
         case 9: return bdsOmegaDot_r12;
         case 10: return bdsI0_r12;
         case 11: return bdsIDot_r12;
         case 12: return bdsCuc_r12;
         case 13: return bdsCus_r12;
         case 14: return bdsCrc_r12;
         case 15: return bdsCrs_r12;
         case 16: return bdsCic_r12;
         case 17: return bdsCis_r12;
         case 18: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "bdsAODE-r12";
         case 1: return "bdsURAI-r12";
         case 2: return "bdsToe-r12";
         case 3: return "bdsAPowerHalf-r12";
         case 4: return "bdsE-r12";
         case 5: return "bdsW-r12";
         case 6: return "bdsDeltaN-r12";
         case 7: return "bdsM0-r12";
         case 8: return "bdsOmega0-r12";
         case 9: return "bdsOmegaDot-r12";
         case 10: return "bdsI0-r12";
         case 11: return "bdsIDot-r12";
         case 12: return "bdsCuc-r12";
         case 13: return "bdsCus-r12";
         case 14: return "bdsCrc-r12";
         case 15: return "bdsCrs-r12";
         case 16: return "bdsCic-r12";
         case 17: return "bdsCis-r12";
         case 18: return null;
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // extension bit

      boolean extbit = buffer.decodeBit ("extbit");

      // decode bdsAODE_r12

      buffer.getContext().eventDispatcher.startElement("bdsAODE_r12", -1);

      bdsAODE_r12 = new Asn1Integer();
      bdsAODE_r12.decode (buffer, 0, 31);
      buffer.getContext().eventDispatcher.endElement("bdsAODE_r12", -1);

      // decode bdsURAI_r12

      buffer.getContext().eventDispatcher.startElement("bdsURAI_r12", -1);

      bdsURAI_r12 = new Asn1Integer();
      bdsURAI_r12.decode (buffer, 0, 15);
      buffer.getContext().eventDispatcher.endElement("bdsURAI_r12", -1);

      // decode bdsToe_r12

      buffer.getContext().eventDispatcher.startElement("bdsToe_r12", -1);

      bdsToe_r12 = new Asn1Integer();
      bdsToe_r12.decode (buffer, 0, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsToe_r12", -1);

      // decode bdsAPowerHalf_r12

      buffer.getContext().eventDispatcher.startElement("bdsAPowerHalf_r12", -1);

      bdsAPowerHalf_r12 = new Asn1Integer();
      bdsAPowerHalf_r12.decode (buffer, 0, 4294967295L);
      buffer.getContext().eventDispatcher.endElement("bdsAPowerHalf_r12", -1);

      // decode bdsE_r12

      buffer.getContext().eventDispatcher.startElement("bdsE_r12", -1);

      bdsE_r12 = new Asn1Integer();
      bdsE_r12.decode (buffer, 0, 4294967295L);
      buffer.getContext().eventDispatcher.endElement("bdsE_r12", -1);

      // decode bdsW_r12

      buffer.getContext().eventDispatcher.startElement("bdsW_r12", -1);

      bdsW_r12 = new Asn1Integer();
      bdsW_r12.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("bdsW_r12", -1);

      // decode bdsDeltaN_r12

      buffer.getContext().eventDispatcher.startElement("bdsDeltaN_r12", -1);

      bdsDeltaN_r12 = new Asn1Integer();
      bdsDeltaN_r12.decode (buffer, -32768, 32767);
      buffer.getContext().eventDispatcher.endElement("bdsDeltaN_r12", -1);

      // decode bdsM0_r12

      buffer.getContext().eventDispatcher.startElement("bdsM0_r12", -1);

      bdsM0_r12 = new Asn1Integer();
      bdsM0_r12.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("bdsM0_r12", -1);

      // decode bdsOmega0_r12

      buffer.getContext().eventDispatcher.startElement("bdsOmega0_r12", -1);

      bdsOmega0_r12 = new Asn1Integer();
      bdsOmega0_r12.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("bdsOmega0_r12", -1);

      // decode bdsOmegaDot_r12

      buffer.getContext().eventDispatcher.startElement("bdsOmegaDot_r12", -1);

      bdsOmegaDot_r12 = new Asn1Integer();
      bdsOmegaDot_r12.decode (buffer, -8388608, 8388607);
      buffer.getContext().eventDispatcher.endElement("bdsOmegaDot_r12", -1);

      // decode bdsI0_r12

      buffer.getContext().eventDispatcher.startElement("bdsI0_r12", -1);

      bdsI0_r12 = new Asn1Integer();
      bdsI0_r12.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("bdsI0_r12", -1);

      // decode bdsIDot_r12

      buffer.getContext().eventDispatcher.startElement("bdsIDot_r12", -1);

      bdsIDot_r12 = new Asn1Integer();
      bdsIDot_r12.decode (buffer, -8192, 8191);
      buffer.getContext().eventDispatcher.endElement("bdsIDot_r12", -1);

      // decode bdsCuc_r12

      buffer.getContext().eventDispatcher.startElement("bdsCuc_r12", -1);

      bdsCuc_r12 = new Asn1Integer();
      bdsCuc_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCuc_r12", -1);

      // decode bdsCus_r12

      buffer.getContext().eventDispatcher.startElement("bdsCus_r12", -1);

      bdsCus_r12 = new Asn1Integer();
      bdsCus_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCus_r12", -1);

      // decode bdsCrc_r12

      buffer.getContext().eventDispatcher.startElement("bdsCrc_r12", -1);

      bdsCrc_r12 = new Asn1Integer();
      bdsCrc_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCrc_r12", -1);

      // decode bdsCrs_r12

      buffer.getContext().eventDispatcher.startElement("bdsCrs_r12", -1);

      bdsCrs_r12 = new Asn1Integer();
      bdsCrs_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCrs_r12", -1);

      // decode bdsCic_r12

      buffer.getContext().eventDispatcher.startElement("bdsCic_r12", -1);

      bdsCic_r12 = new Asn1Integer();
      bdsCic_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCic_r12", -1);

      // decode bdsCis_r12

      buffer.getContext().eventDispatcher.startElement("bdsCis_r12", -1);

      bdsCis_r12 = new Asn1Integer();
      bdsCis_r12.decode (buffer, -131072, 131071);
      buffer.getContext().eventDispatcher.endElement("bdsCis_r12", -1);

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

      // encode bdsAODE_r12

      if (bdsAODE_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsAODE_r12", -1);

         bdsAODE_r12.encode (buffer, 0, 31);

         buffer.getContext().eventDispatcher.endElement("bdsAODE_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsAODE_r12");

      // encode bdsURAI_r12

      if (bdsURAI_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsURAI_r12", -1);

         bdsURAI_r12.encode (buffer, 0, 15);

         buffer.getContext().eventDispatcher.endElement("bdsURAI_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsURAI_r12");

      // encode bdsToe_r12

      if (bdsToe_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsToe_r12", -1);

         bdsToe_r12.encode (buffer, 0, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsToe_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsToe_r12");

      // encode bdsAPowerHalf_r12

      if (bdsAPowerHalf_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsAPowerHalf_r12", -1);

         bdsAPowerHalf_r12.encode (buffer, 0, 4294967295L);

         buffer.getContext().eventDispatcher.endElement("bdsAPowerHalf_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsAPowerHalf_r12");

      // encode bdsE_r12

      if (bdsE_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsE_r12", -1);

         bdsE_r12.encode (buffer, 0, 4294967295L);

         buffer.getContext().eventDispatcher.endElement("bdsE_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsE_r12");

      // encode bdsW_r12

      if (bdsW_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsW_r12", -1);

         bdsW_r12.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("bdsW_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsW_r12");

      // encode bdsDeltaN_r12

      if (bdsDeltaN_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsDeltaN_r12", -1);

         bdsDeltaN_r12.encode (buffer, -32768, 32767);

         buffer.getContext().eventDispatcher.endElement("bdsDeltaN_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsDeltaN_r12");

      // encode bdsM0_r12

      if (bdsM0_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsM0_r12", -1);

         bdsM0_r12.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("bdsM0_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsM0_r12");

      // encode bdsOmega0_r12

      if (bdsOmega0_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsOmega0_r12", -1);

         bdsOmega0_r12.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("bdsOmega0_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsOmega0_r12");

      // encode bdsOmegaDot_r12

      if (bdsOmegaDot_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsOmegaDot_r12", -1);

         bdsOmegaDot_r12.encode (buffer, -8388608, 8388607);

         buffer.getContext().eventDispatcher.endElement("bdsOmegaDot_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsOmegaDot_r12");

      // encode bdsI0_r12

      if (bdsI0_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsI0_r12", -1);

         bdsI0_r12.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("bdsI0_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsI0_r12");

      // encode bdsIDot_r12

      if (bdsIDot_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsIDot_r12", -1);

         bdsIDot_r12.encode (buffer, -8192, 8191);

         buffer.getContext().eventDispatcher.endElement("bdsIDot_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsIDot_r12");

      // encode bdsCuc_r12

      if (bdsCuc_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCuc_r12", -1);

         bdsCuc_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCuc_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCuc_r12");

      // encode bdsCus_r12

      if (bdsCus_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCus_r12", -1);

         bdsCus_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCus_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCus_r12");

      // encode bdsCrc_r12

      if (bdsCrc_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCrc_r12", -1);

         bdsCrc_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCrc_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCrc_r12");

      // encode bdsCrs_r12

      if (bdsCrs_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCrs_r12", -1);

         bdsCrs_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCrs_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCrs_r12");

      // encode bdsCic_r12

      if (bdsCic_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCic_r12", -1);

         bdsCic_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCic_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCic_r12");

      // encode bdsCis_r12

      if (bdsCis_r12 != null) {
         buffer.getContext().eventDispatcher.startElement("bdsCis_r12", -1);

         bdsCis_r12.encode (buffer, -131072, 131071);

         buffer.getContext().eventDispatcher.endElement("bdsCis_r12", -1);
      }
      else throw new Asn1MissingRequiredException ("bdsCis_r12");

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
