/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class SSR_PhaseBiasSignalElement_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SSR-PhaseBiasSignalElement-r16";
   }

   protected GNSS_SignalID signal_and_tracking_mode_ID_r16;
   protected Asn1Integer phaseBias_r16;
   protected Asn1Integer phaseDiscontinuityIndicator_r16;
   protected Asn1Integer phaseBiasIntegerIndicator_r16;  // optional
   protected Asn1OpenExt extElem1;

   public SSR_PhaseBiasSignalElement_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SSR_PhaseBiasSignalElement_r16 (
      GNSS_SignalID signal_and_tracking_mode_ID_r16_,
      Asn1Integer phaseBias_r16_,
      Asn1Integer phaseDiscontinuityIndicator_r16_,
      Asn1Integer phaseBiasIntegerIndicator_r16_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r16 (signal_and_tracking_mode_ID_r16_);
      setPhaseBias_r16 (phaseBias_r16_);
      setPhaseDiscontinuityIndicator_r16 (phaseDiscontinuityIndicator_r16_);
      setPhaseBiasIntegerIndicator_r16 (phaseBiasIntegerIndicator_r16_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public SSR_PhaseBiasSignalElement_r16 (
      GNSS_SignalID signal_and_tracking_mode_ID_r16_,
      Asn1Integer phaseBias_r16_,
      Asn1Integer phaseDiscontinuityIndicator_r16_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r16 (signal_and_tracking_mode_ID_r16_);
      setPhaseBias_r16 (phaseBias_r16_);
      setPhaseDiscontinuityIndicator_r16 (phaseDiscontinuityIndicator_r16_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SSR_PhaseBiasSignalElement_r16 (GNSS_SignalID signal_and_tracking_mode_ID_r16_,
      long phaseBias_r16_,
      long phaseDiscontinuityIndicator_r16_,
      long phaseBiasIntegerIndicator_r16_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r16 (signal_and_tracking_mode_ID_r16_);
      setPhaseBias_r16 (phaseBias_r16_);
      setPhaseDiscontinuityIndicator_r16 (phaseDiscontinuityIndicator_r16_);
      setPhaseBiasIntegerIndicator_r16 (phaseBiasIntegerIndicator_r16_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public SSR_PhaseBiasSignalElement_r16 (
      GNSS_SignalID signal_and_tracking_mode_ID_r16_,
      long phaseBias_r16_,
      long phaseDiscontinuityIndicator_r16_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r16 (signal_and_tracking_mode_ID_r16_);
      setPhaseBias_r16 (phaseBias_r16_);
      setPhaseDiscontinuityIndicator_r16 (phaseDiscontinuityIndicator_r16_);
   }

   public void init () {
      signal_and_tracking_mode_ID_r16 = null;
      phaseBias_r16 = null;
      phaseDiscontinuityIndicator_r16 = null;
      phaseBiasIntegerIndicator_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SSR_PhaseBiasSignalElement_r16) ) return false;

      SSR_PhaseBiasSignalElement_r16 rhs = (SSR_PhaseBiasSignalElement_r16) obj;

      if (signal_and_tracking_mode_ID_r16 == null) {
         if (rhs.signal_and_tracking_mode_ID_r16 != null) return false;
      }
      else {
         if (!signal_and_tracking_mode_ID_r16.equals(rhs.signal_and_tracking_mode_ID_r16)) {
            return false;
         }
      }

      if (phaseBias_r16 == null) {
         if (rhs.phaseBias_r16 != null) return false;
      }
      else {
         if (!phaseBias_r16.equals(rhs.phaseBias_r16)) {
            return false;
         }
      }

      if (phaseDiscontinuityIndicator_r16 == null) {
         if (rhs.phaseDiscontinuityIndicator_r16 != null) return false;
      }
      else {
         if (!phaseDiscontinuityIndicator_r16.equals(rhs.phaseDiscontinuityIndicator_r16)) {
            return false;
         }
      }

      if (phaseBiasIntegerIndicator_r16 == null) {
         if (rhs.phaseBiasIntegerIndicator_r16 != null) return false;
      }
      else {
         if (!phaseBiasIntegerIndicator_r16.equals(rhs.phaseBiasIntegerIndicator_r16)) {
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

      if (signal_and_tracking_mode_ID_r16 != null) __code = 31*__code + signal_and_tracking_mode_ID_r16.hashCode();
      if (phaseBias_r16 != null) __code = 31*__code + phaseBias_r16.hashCode();
      if (phaseDiscontinuityIndicator_r16 != null) __code = 31*__code + phaseDiscontinuityIndicator_r16.hashCode();
      if (phaseBiasIntegerIndicator_r16 != null) __code = 31*__code + phaseBiasIntegerIndicator_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Signal_and_tracking_mode_ID_r16
    */
   public GNSS_SignalID getSignal_and_tracking_mode_ID_r16 () {
      return signal_and_tracking_mode_ID_r16;
   }

   public void setSignal_and_tracking_mode_ID_r16 (GNSS_SignalID value) {
      this.signal_and_tracking_mode_ID_r16 = value;
   }

   /**
    * Accessor/mutator methods for PhaseBias_r16
    */
   public Asn1Integer getPhaseBias_r16 () {
      return phaseBias_r16;
   }

   public void setPhaseBias_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -16384 && value.value <= 16383))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.phaseBias_r16 = value;
   }

   public void setPhaseBias_r16 (long value) 
      throws Asn1Exception
   {
      setPhaseBias_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for PhaseDiscontinuityIndicator_r16
    */
   public Asn1Integer getPhaseDiscontinuityIndicator_r16 () {
      return phaseDiscontinuityIndicator_r16;
   }

   public void setPhaseDiscontinuityIndicator_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 3))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.phaseDiscontinuityIndicator_r16 = value;
   }

   public void setPhaseDiscontinuityIndicator_r16 (long value) 
      throws Asn1Exception
   {
      setPhaseDiscontinuityIndicator_r16 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for PhaseBiasIntegerIndicator_r16
    */
   public Asn1Integer getPhaseBiasIntegerIndicator_r16 () {
      return phaseBiasIntegerIndicator_r16;
   }

   public void setPhaseBiasIntegerIndicator_r16 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 3))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.phaseBiasIntegerIndicator_r16 = value;
   }

   public void setPhaseBiasIntegerIndicator_r16 (long value) 
      throws Asn1Exception
   {
      setPhaseBiasIntegerIndicator_r16 (new Asn1Integer(value));
   }
   public boolean hasPhaseBiasIntegerIndicator_r16 () {
      return (phaseBiasIntegerIndicator_r16 != null);
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
         case 0: return signal_and_tracking_mode_ID_r16;
         case 1: return phaseBias_r16;
         case 2: return phaseDiscontinuityIndicator_r16;
         case 3: return phaseBiasIntegerIndicator_r16;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "signal-and-tracking-mode-ID-r16";
         case 1: return "phaseBias-r16";
         case 2: return "phaseDiscontinuityIndicator-r16";
         case 3: return "phaseBiasIntegerIndicator-r16";
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

      boolean phaseBiasIntegerIndicator_r16Present = buffer.decodeBit ("phaseBiasIntegerIndicator_r16Present");

      // decode signal_and_tracking_mode_ID_r16

      buffer.getContext().eventDispatcher.startElement("signal_and_tracking_mode_ID_r16", -1);

      signal_and_tracking_mode_ID_r16 = new GNSS_SignalID();
      signal_and_tracking_mode_ID_r16.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("signal_and_tracking_mode_ID_r16", -1);

      // decode phaseBias_r16

      buffer.getContext().eventDispatcher.startElement("phaseBias_r16", -1);

      phaseBias_r16 = new Asn1Integer();
      phaseBias_r16.decode (buffer, -16384, 16383);
      buffer.getContext().eventDispatcher.endElement("phaseBias_r16", -1);

      // decode phaseDiscontinuityIndicator_r16

      buffer.getContext().eventDispatcher.startElement("phaseDiscontinuityIndicator_r16", -1);

      phaseDiscontinuityIndicator_r16 = new Asn1Integer();
      phaseDiscontinuityIndicator_r16.decode (buffer, 0, 3);
      buffer.getContext().eventDispatcher.endElement("phaseDiscontinuityIndicator_r16", -1);

      // decode phaseBiasIntegerIndicator_r16

      if (phaseBiasIntegerIndicator_r16Present) {
         buffer.getContext().eventDispatcher.startElement("phaseBiasIntegerIndicator_r16", -1);

         phaseBiasIntegerIndicator_r16 = new Asn1Integer();
         phaseBiasIntegerIndicator_r16.decode (buffer, 0, 3);
         buffer.getContext().eventDispatcher.endElement("phaseBiasIntegerIndicator_r16", -1);
      }
      else {
         phaseBiasIntegerIndicator_r16 = null;
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

      buffer.encodeBit ((phaseBiasIntegerIndicator_r16 != null), null);

      // encode signal_and_tracking_mode_ID_r16

      if (signal_and_tracking_mode_ID_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("signal_and_tracking_mode_ID_r16", -1);

         signal_and_tracking_mode_ID_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("signal_and_tracking_mode_ID_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("signal_and_tracking_mode_ID_r16");

      // encode phaseBias_r16

      if (phaseBias_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("phaseBias_r16", -1);

         phaseBias_r16.encode (buffer, -16384, 16383);

         buffer.getContext().eventDispatcher.endElement("phaseBias_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("phaseBias_r16");

      // encode phaseDiscontinuityIndicator_r16

      if (phaseDiscontinuityIndicator_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("phaseDiscontinuityIndicator_r16", -1);

         phaseDiscontinuityIndicator_r16.encode (buffer, 0, 3);

         buffer.getContext().eventDispatcher.endElement("phaseDiscontinuityIndicator_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("phaseDiscontinuityIndicator_r16");

      // encode phaseBiasIntegerIndicator_r16

      if (phaseBiasIntegerIndicator_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("phaseBiasIntegerIndicator_r16", -1);

         phaseBiasIntegerIndicator_r16.encode (buffer, 0, 3);

         buffer.getContext().eventDispatcher.endElement("phaseBiasIntegerIndicator_r16", -1);
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
