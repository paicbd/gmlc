/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_RTK_SatelliteSignalDataElement_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-RTK-SatelliteSignalDataElement-r15";
   }

   protected GNSS_SignalID gnss_SignalID_r15;
   protected Asn1Integer fine_PseudoRange_r15;
   protected Asn1Integer fine_PhaseRange_r15;
   protected Asn1Integer lockTimeIndicator_r15;
   protected Asn1BitString halfCycleAmbiguityIndicator_r15;
   protected Asn1Integer carrier_to_noise_ratio_r15;  // optional
   protected Asn1Integer fine_PhaseRangeRate_r15;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_RTK_SatelliteSignalDataElement_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_RTK_SatelliteSignalDataElement_r15 (
      GNSS_SignalID gnss_SignalID_r15_,
      Asn1Integer fine_PseudoRange_r15_,
      Asn1Integer fine_PhaseRange_r15_,
      Asn1Integer lockTimeIndicator_r15_,
      Asn1BitString halfCycleAmbiguityIndicator_r15_,
      Asn1Integer carrier_to_noise_ratio_r15_,
      Asn1Integer fine_PhaseRangeRate_r15_
   ) throws Asn1Exception {
      super();
      setGnss_SignalID_r15 (gnss_SignalID_r15_);
      setFine_PseudoRange_r15 (fine_PseudoRange_r15_);
      setFine_PhaseRange_r15 (fine_PhaseRange_r15_);
      setLockTimeIndicator_r15 (lockTimeIndicator_r15_);
      setHalfCycleAmbiguityIndicator_r15 (halfCycleAmbiguityIndicator_r15_);
      setCarrier_to_noise_ratio_r15 (carrier_to_noise_ratio_r15_);
      setFine_PhaseRangeRate_r15 (fine_PhaseRangeRate_r15_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_RTK_SatelliteSignalDataElement_r15 (
      GNSS_SignalID gnss_SignalID_r15_,
      Asn1Integer fine_PseudoRange_r15_,
      Asn1Integer fine_PhaseRange_r15_,
      Asn1Integer lockTimeIndicator_r15_,
      Asn1BitString halfCycleAmbiguityIndicator_r15_
   ) throws Asn1Exception {
      super();
      setGnss_SignalID_r15 (gnss_SignalID_r15_);
      setFine_PseudoRange_r15 (fine_PseudoRange_r15_);
      setFine_PhaseRange_r15 (fine_PhaseRange_r15_);
      setLockTimeIndicator_r15 (lockTimeIndicator_r15_);
      setHalfCycleAmbiguityIndicator_r15 (halfCycleAmbiguityIndicator_r15_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_RTK_SatelliteSignalDataElement_r15 (GNSS_SignalID gnss_SignalID_r15_,
      long fine_PseudoRange_r15_,
      long fine_PhaseRange_r15_,
      long lockTimeIndicator_r15_,
      Asn1BitString halfCycleAmbiguityIndicator_r15_,
      long carrier_to_noise_ratio_r15_,
      long fine_PhaseRangeRate_r15_
   ) throws Asn1Exception {
      super();
      setGnss_SignalID_r15 (gnss_SignalID_r15_);
      setFine_PseudoRange_r15 (fine_PseudoRange_r15_);
      setFine_PhaseRange_r15 (fine_PhaseRange_r15_);
      setLockTimeIndicator_r15 (lockTimeIndicator_r15_);
      setHalfCycleAmbiguityIndicator_r15 (halfCycleAmbiguityIndicator_r15_);
      setCarrier_to_noise_ratio_r15 (carrier_to_noise_ratio_r15_);
      setFine_PhaseRangeRate_r15 (fine_PhaseRangeRate_r15_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public GNSS_RTK_SatelliteSignalDataElement_r15 (
      GNSS_SignalID gnss_SignalID_r15_,
      long fine_PseudoRange_r15_,
      long fine_PhaseRange_r15_,
      long lockTimeIndicator_r15_,
      Asn1BitString halfCycleAmbiguityIndicator_r15_
   ) throws Asn1Exception {
      super();
      setGnss_SignalID_r15 (gnss_SignalID_r15_);
      setFine_PseudoRange_r15 (fine_PseudoRange_r15_);
      setFine_PhaseRange_r15 (fine_PhaseRange_r15_);
      setLockTimeIndicator_r15 (lockTimeIndicator_r15_);
      setHalfCycleAmbiguityIndicator_r15 (halfCycleAmbiguityIndicator_r15_);
   }

   public void init () {
      gnss_SignalID_r15 = null;
      fine_PseudoRange_r15 = null;
      fine_PhaseRange_r15 = null;
      lockTimeIndicator_r15 = null;
      halfCycleAmbiguityIndicator_r15 = null;
      carrier_to_noise_ratio_r15 = null;
      fine_PhaseRangeRate_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_RTK_SatelliteSignalDataElement_r15) ) return false;

      GNSS_RTK_SatelliteSignalDataElement_r15 rhs = (GNSS_RTK_SatelliteSignalDataElement_r15) obj;

      if (gnss_SignalID_r15 == null) {
         if (rhs.gnss_SignalID_r15 != null) return false;
      }
      else {
         if (!gnss_SignalID_r15.equals(rhs.gnss_SignalID_r15)) {
            return false;
         }
      }

      if (fine_PseudoRange_r15 == null) {
         if (rhs.fine_PseudoRange_r15 != null) return false;
      }
      else {
         if (!fine_PseudoRange_r15.equals(rhs.fine_PseudoRange_r15)) {
            return false;
         }
      }

      if (fine_PhaseRange_r15 == null) {
         if (rhs.fine_PhaseRange_r15 != null) return false;
      }
      else {
         if (!fine_PhaseRange_r15.equals(rhs.fine_PhaseRange_r15)) {
            return false;
         }
      }

      if (lockTimeIndicator_r15 == null) {
         if (rhs.lockTimeIndicator_r15 != null) return false;
      }
      else {
         if (!lockTimeIndicator_r15.equals(rhs.lockTimeIndicator_r15)) {
            return false;
         }
      }

      if (halfCycleAmbiguityIndicator_r15 == null) {
         if (rhs.halfCycleAmbiguityIndicator_r15 != null) return false;
      }
      else {
         if (!halfCycleAmbiguityIndicator_r15.equals(rhs.halfCycleAmbiguityIndicator_r15)) {
            return false;
         }
      }

      if (carrier_to_noise_ratio_r15 == null) {
         if (rhs.carrier_to_noise_ratio_r15 != null) return false;
      }
      else {
         if (!carrier_to_noise_ratio_r15.equals(rhs.carrier_to_noise_ratio_r15)) {
            return false;
         }
      }

      if (fine_PhaseRangeRate_r15 == null) {
         if (rhs.fine_PhaseRangeRate_r15 != null) return false;
      }
      else {
         if (!fine_PhaseRangeRate_r15.equals(rhs.fine_PhaseRangeRate_r15)) {
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

      if (gnss_SignalID_r15 != null) __code = 31*__code + gnss_SignalID_r15.hashCode();
      if (fine_PseudoRange_r15 != null) __code = 31*__code + fine_PseudoRange_r15.hashCode();
      if (fine_PhaseRange_r15 != null) __code = 31*__code + fine_PhaseRange_r15.hashCode();
      if (lockTimeIndicator_r15 != null) __code = 31*__code + lockTimeIndicator_r15.hashCode();
      if (halfCycleAmbiguityIndicator_r15 != null) __code = 31*__code + halfCycleAmbiguityIndicator_r15.hashCode();
      if (carrier_to_noise_ratio_r15 != null) __code = 31*__code + carrier_to_noise_ratio_r15.hashCode();
      if (fine_PhaseRangeRate_r15 != null) __code = 31*__code + fine_PhaseRangeRate_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Gnss_SignalID_r15
    */
   public GNSS_SignalID getGnss_SignalID_r15 () {
      return gnss_SignalID_r15;
   }

   public void setGnss_SignalID_r15 (GNSS_SignalID value) {
      this.gnss_SignalID_r15 = value;
   }

   /**
    * Accessor/mutator methods for Fine_PseudoRange_r15
    */
   public Asn1Integer getFine_PseudoRange_r15 () {
      return fine_PseudoRange_r15;
   }

   public void setFine_PseudoRange_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -524288 && value.value <= 524287))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.fine_PseudoRange_r15 = value;
   }

   public void setFine_PseudoRange_r15 (long value) 
      throws Asn1Exception
   {
      setFine_PseudoRange_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Fine_PhaseRange_r15
    */
   public Asn1Integer getFine_PhaseRange_r15 () {
      return fine_PhaseRange_r15;
   }

   public void setFine_PhaseRange_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8388608 && value.value <= 8388607))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.fine_PhaseRange_r15 = value;
   }

   public void setFine_PhaseRange_r15 (long value) 
      throws Asn1Exception
   {
      setFine_PhaseRange_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for LockTimeIndicator_r15
    */
   public Asn1Integer getLockTimeIndicator_r15 () {
      return lockTimeIndicator_r15;
   }

   public void setLockTimeIndicator_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.lockTimeIndicator_r15 = value;
   }

   public void setLockTimeIndicator_r15 (long value) 
      throws Asn1Exception
   {
      setLockTimeIndicator_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for HalfCycleAmbiguityIndicator_r15
    */
   public Asn1BitString getHalfCycleAmbiguityIndicator_r15 () {
      return halfCycleAmbiguityIndicator_r15;
   }

   public void setHalfCycleAmbiguityIndicator_r15 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 1)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.halfCycleAmbiguityIndicator_r15 = value;
   }

   /**
    * Accessor/mutator methods for Carrier_to_noise_ratio_r15
    */
   public Asn1Integer getCarrier_to_noise_ratio_r15 () {
      return carrier_to_noise_ratio_r15;
   }

   public void setCarrier_to_noise_ratio_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.carrier_to_noise_ratio_r15 = value;
   }

   public void setCarrier_to_noise_ratio_r15 (long value) 
      throws Asn1Exception
   {
      setCarrier_to_noise_ratio_r15 (new Asn1Integer(value));
   }
   public boolean hasCarrier_to_noise_ratio_r15 () {
      return (carrier_to_noise_ratio_r15 != null);
   }

   /**
    * Accessor/mutator methods for Fine_PhaseRangeRate_r15
    */
   public Asn1Integer getFine_PhaseRangeRate_r15 () {
      return fine_PhaseRangeRate_r15;
   }

   public void setFine_PhaseRangeRate_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -16384 && value.value <= 16383))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.fine_PhaseRangeRate_r15 = value;
   }

   public void setFine_PhaseRangeRate_r15 (long value) 
      throws Asn1Exception
   {
      setFine_PhaseRangeRate_r15 (new Asn1Integer(value));
   }
   public boolean hasFine_PhaseRangeRate_r15 () {
      return (fine_PhaseRangeRate_r15 != null);
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
         case 0: return gnss_SignalID_r15;
         case 1: return fine_PseudoRange_r15;
         case 2: return fine_PhaseRange_r15;
         case 3: return lockTimeIndicator_r15;
         case 4: return halfCycleAmbiguityIndicator_r15;
         case 5: return carrier_to_noise_ratio_r15;
         case 6: return fine_PhaseRangeRate_r15;
         case 7: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "gnss-SignalID-r15";
         case 1: return "fine-PseudoRange-r15";
         case 2: return "fine-PhaseRange-r15";
         case 3: return "lockTimeIndicator-r15";
         case 4: return "halfCycleAmbiguityIndicator-r15";
         case 5: return "carrier-to-noise-ratio-r15";
         case 6: return "fine-PhaseRangeRate-r15";
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

      boolean carrier_to_noise_ratio_r15Present = buffer.decodeBit ("carrier_to_noise_ratio_r15Present");
      boolean fine_PhaseRangeRate_r15Present = buffer.decodeBit ("fine_PhaseRangeRate_r15Present");

      // decode gnss_SignalID_r15

      buffer.getContext().eventDispatcher.startElement("gnss_SignalID_r15", -1);

      gnss_SignalID_r15 = new GNSS_SignalID();
      gnss_SignalID_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("gnss_SignalID_r15", -1);

      // decode fine_PseudoRange_r15

      buffer.getContext().eventDispatcher.startElement("fine_PseudoRange_r15", -1);

      fine_PseudoRange_r15 = new Asn1Integer();
      fine_PseudoRange_r15.decode (buffer, -524288, 524287);
      buffer.getContext().eventDispatcher.endElement("fine_PseudoRange_r15", -1);

      // decode fine_PhaseRange_r15

      buffer.getContext().eventDispatcher.startElement("fine_PhaseRange_r15", -1);

      fine_PhaseRange_r15 = new Asn1Integer();
      fine_PhaseRange_r15.decode (buffer, -8388608, 8388607);
      buffer.getContext().eventDispatcher.endElement("fine_PhaseRange_r15", -1);

      // decode lockTimeIndicator_r15

      buffer.getContext().eventDispatcher.startElement("lockTimeIndicator_r15", -1);

      lockTimeIndicator_r15 = new Asn1Integer();
      lockTimeIndicator_r15.decode (buffer, 0, 1023);
      buffer.getContext().eventDispatcher.endElement("lockTimeIndicator_r15", -1);

      // decode halfCycleAmbiguityIndicator_r15

      buffer.getContext().eventDispatcher.startElement("halfCycleAmbiguityIndicator_r15", -1);

      halfCycleAmbiguityIndicator_r15 = new Asn1BitString();
      halfCycleAmbiguityIndicator_r15.decode (buffer, 1, 1);
      buffer.getContext().eventDispatcher.endElement("halfCycleAmbiguityIndicator_r15", -1);

      // decode carrier_to_noise_ratio_r15

      if (carrier_to_noise_ratio_r15Present) {
         buffer.getContext().eventDispatcher.startElement("carrier_to_noise_ratio_r15", -1);

         carrier_to_noise_ratio_r15 = new Asn1Integer();
         carrier_to_noise_ratio_r15.decode (buffer, 0, 1023);
         buffer.getContext().eventDispatcher.endElement("carrier_to_noise_ratio_r15", -1);
      }
      else {
         carrier_to_noise_ratio_r15 = null;
      }

      // decode fine_PhaseRangeRate_r15

      if (fine_PhaseRangeRate_r15Present) {
         buffer.getContext().eventDispatcher.startElement("fine_PhaseRangeRate_r15", -1);

         fine_PhaseRangeRate_r15 = new Asn1Integer();
         fine_PhaseRangeRate_r15.decode (buffer, -16384, 16383);
         buffer.getContext().eventDispatcher.endElement("fine_PhaseRangeRate_r15", -1);
      }
      else {
         fine_PhaseRangeRate_r15 = null;
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

      buffer.encodeBit ((carrier_to_noise_ratio_r15 != null), null);
      buffer.encodeBit ((fine_PhaseRangeRate_r15 != null), null);

      // encode gnss_SignalID_r15

      if (gnss_SignalID_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_SignalID_r15", -1);

         gnss_SignalID_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("gnss_SignalID_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_SignalID_r15");

      // encode fine_PseudoRange_r15

      if (fine_PseudoRange_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("fine_PseudoRange_r15", -1);

         fine_PseudoRange_r15.encode (buffer, -524288, 524287);

         buffer.getContext().eventDispatcher.endElement("fine_PseudoRange_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("fine_PseudoRange_r15");

      // encode fine_PhaseRange_r15

      if (fine_PhaseRange_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("fine_PhaseRange_r15", -1);

         fine_PhaseRange_r15.encode (buffer, -8388608, 8388607);

         buffer.getContext().eventDispatcher.endElement("fine_PhaseRange_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("fine_PhaseRange_r15");

      // encode lockTimeIndicator_r15

      if (lockTimeIndicator_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("lockTimeIndicator_r15", -1);

         lockTimeIndicator_r15.encode (buffer, 0, 1023);

         buffer.getContext().eventDispatcher.endElement("lockTimeIndicator_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("lockTimeIndicator_r15");

      // encode halfCycleAmbiguityIndicator_r15

      if (halfCycleAmbiguityIndicator_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("halfCycleAmbiguityIndicator_r15", -1);

         halfCycleAmbiguityIndicator_r15.encode (buffer, 1, 1);

         buffer.getContext().eventDispatcher.endElement("halfCycleAmbiguityIndicator_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("halfCycleAmbiguityIndicator_r15");

      // encode carrier_to_noise_ratio_r15

      if (carrier_to_noise_ratio_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("carrier_to_noise_ratio_r15", -1);

         carrier_to_noise_ratio_r15.encode (buffer, 0, 1023);

         buffer.getContext().eventDispatcher.endElement("carrier_to_noise_ratio_r15", -1);
      }

      // encode fine_PhaseRangeRate_r15

      if (fine_PhaseRangeRate_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("fine_PhaseRangeRate_r15", -1);

         fine_PhaseRangeRate_r15.encode (buffer, -16384, 16383);

         buffer.getContext().eventDispatcher.endElement("fine_PhaseRangeRate_r15", -1);
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
