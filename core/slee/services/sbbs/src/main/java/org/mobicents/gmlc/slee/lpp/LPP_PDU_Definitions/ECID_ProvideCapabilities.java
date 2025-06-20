/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class ECID_ProvideCapabilities extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ECID-ProvideCapabilities";
   }

   protected ECID_ProvideCapabilities_ecid_MeasSupported ecid_MeasSupported;
   protected ECID_ProvideCapabilities_ueRxTxSupTDD_r13 ueRxTxSupTDD_r13 = null;  // optional
   protected ECID_ProvideCapabilities_periodicalReporting_r14 periodicalReporting_r14 = null;  // optional
   protected ECID_ProvideCapabilities_triggeredReporting_r14 triggeredReporting_r14 = null;  // optional
   protected ECID_ProvideCapabilities_idleStateForMeasurements_r14 idleStateForMeasurements_r14 = null;  // optional
   protected Asn1OpenExt extElem1;

   public ECID_ProvideCapabilities () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public ECID_ProvideCapabilities (
      ECID_ProvideCapabilities_ecid_MeasSupported ecid_MeasSupported_,
      ECID_ProvideCapabilities_ueRxTxSupTDD_r13 ueRxTxSupTDD_r13_,
      ECID_ProvideCapabilities_periodicalReporting_r14 periodicalReporting_r14_,
      ECID_ProvideCapabilities_triggeredReporting_r14 triggeredReporting_r14_,
      ECID_ProvideCapabilities_idleStateForMeasurements_r14 idleStateForMeasurements_r14_
   ) throws Asn1Exception {
      super();
      setEcid_MeasSupported (ecid_MeasSupported_);
      setUeRxTxSupTDD_r13 (ueRxTxSupTDD_r13_);
      setPeriodicalReporting_r14 (periodicalReporting_r14_);
      setTriggeredReporting_r14 (triggeredReporting_r14_);
      setIdleStateForMeasurements_r14 (idleStateForMeasurements_r14_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public ECID_ProvideCapabilities (
      ECID_ProvideCapabilities_ecid_MeasSupported ecid_MeasSupported_
   ) throws Asn1Exception {
      super();
      setEcid_MeasSupported (ecid_MeasSupported_);
   }

   public void init () {
      ecid_MeasSupported = null;
      ueRxTxSupTDD_r13 = null;
      periodicalReporting_r14 = null;
      triggeredReporting_r14 = null;
      idleStateForMeasurements_r14 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof ECID_ProvideCapabilities) ) return false;

      ECID_ProvideCapabilities rhs = (ECID_ProvideCapabilities) obj;

      if (ecid_MeasSupported == null) {
         if (rhs.ecid_MeasSupported != null) return false;
      }
      else {
         if (!ecid_MeasSupported.equals(rhs.ecid_MeasSupported)) {
            return false;
         }
      }

      if (ueRxTxSupTDD_r13 == null) {
         if (rhs.ueRxTxSupTDD_r13 != null) return false;
      }
      else {
         if (!ueRxTxSupTDD_r13.equals(rhs.ueRxTxSupTDD_r13)) {
            return false;
         }
      }

      if (periodicalReporting_r14 == null) {
         if (rhs.periodicalReporting_r14 != null) return false;
      }
      else {
         if (!periodicalReporting_r14.equals(rhs.periodicalReporting_r14)) {
            return false;
         }
      }

      if (triggeredReporting_r14 == null) {
         if (rhs.triggeredReporting_r14 != null) return false;
      }
      else {
         if (!triggeredReporting_r14.equals(rhs.triggeredReporting_r14)) {
            return false;
         }
      }

      if (idleStateForMeasurements_r14 == null) {
         if (rhs.idleStateForMeasurements_r14 != null) return false;
      }
      else {
         if (!idleStateForMeasurements_r14.equals(rhs.idleStateForMeasurements_r14)) {
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

      if (ecid_MeasSupported != null) __code = 31*__code + ecid_MeasSupported.hashCode();
      if (ueRxTxSupTDD_r13 != null) __code = 31*__code + ueRxTxSupTDD_r13.hashCode();
      if (periodicalReporting_r14 != null) __code = 31*__code + periodicalReporting_r14.hashCode();
      if (triggeredReporting_r14 != null) __code = 31*__code + triggeredReporting_r14.hashCode();
      if (idleStateForMeasurements_r14 != null) __code = 31*__code + idleStateForMeasurements_r14.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Ecid_MeasSupported
    */
   public ECID_ProvideCapabilities_ecid_MeasSupported getEcid_MeasSupported () {
      return ecid_MeasSupported;
   }

   public void setEcid_MeasSupported (ECID_ProvideCapabilities_ecid_MeasSupported value) 
      throws Asn1Exception
   {
      if (!((value.getLength() >= 1 && value.getLength() <= 8))) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.ecid_MeasSupported = value;
   }

   /**
    * Accessor/mutator methods for UeRxTxSupTDD_r13
    */
   public ECID_ProvideCapabilities_ueRxTxSupTDD_r13 getUeRxTxSupTDD_r13 () {
      return ueRxTxSupTDD_r13;
   }

   public void setUeRxTxSupTDD_r13 (ECID_ProvideCapabilities_ueRxTxSupTDD_r13 value) {
      this.ueRxTxSupTDD_r13 = value;
   }

   public boolean hasUeRxTxSupTDD_r13 () {
      return (ueRxTxSupTDD_r13 != null);
   }

   /**
    * Accessor/mutator methods for PeriodicalReporting_r14
    */
   public ECID_ProvideCapabilities_periodicalReporting_r14 getPeriodicalReporting_r14 () {
      return periodicalReporting_r14;
   }

   public void setPeriodicalReporting_r14 (ECID_ProvideCapabilities_periodicalReporting_r14 value) {
      this.periodicalReporting_r14 = value;
   }

   public boolean hasPeriodicalReporting_r14 () {
      return (periodicalReporting_r14 != null);
   }

   /**
    * Accessor/mutator methods for TriggeredReporting_r14
    */
   public ECID_ProvideCapabilities_triggeredReporting_r14 getTriggeredReporting_r14 () {
      return triggeredReporting_r14;
   }

   public void setTriggeredReporting_r14 (ECID_ProvideCapabilities_triggeredReporting_r14 value) {
      this.triggeredReporting_r14 = value;
   }

   public boolean hasTriggeredReporting_r14 () {
      return (triggeredReporting_r14 != null);
   }

   /**
    * Accessor/mutator methods for IdleStateForMeasurements_r14
    */
   public ECID_ProvideCapabilities_idleStateForMeasurements_r14 getIdleStateForMeasurements_r14 () {
      return idleStateForMeasurements_r14;
   }

   public void setIdleStateForMeasurements_r14 (ECID_ProvideCapabilities_idleStateForMeasurements_r14 value) {
      this.idleStateForMeasurements_r14 = value;
   }

   public boolean hasIdleStateForMeasurements_r14 () {
      return (idleStateForMeasurements_r14 != null);
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
         case 0: return ecid_MeasSupported;
         case 1: return ueRxTxSupTDD_r13;
         case 2: return periodicalReporting_r14;
         case 3: return triggeredReporting_r14;
         case 4: return idleStateForMeasurements_r14;
         case 5: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "ecid-MeasSupported";
         case 1: return "ueRxTxSupTDD-r13";
         case 2: return "periodicalReporting-r14";
         case 3: return "triggeredReporting-r14";
         case 4: return "idleStateForMeasurements-r14";
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

      // decode ecid_MeasSupported

      buffer.getContext().eventDispatcher.startElement("ecid_MeasSupported", -1);

      ecid_MeasSupported = new ECID_ProvideCapabilities_ecid_MeasSupported();
      ecid_MeasSupported.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("ecid_MeasSupported", -1);

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("ueRxTxSupTDD_r13Present");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("periodicalReporting_r14Present");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("triggeredReporting_r14Present");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("idleStateForMeasurements_r14Present");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode ueRxTxSupTDD_r13

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("ueRxTxSupTDD_r13", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = ECID_ProvideCapabilities_ueRxTxSupTDD_r13.decodeEnumValue (buffer);
               ueRxTxSupTDD_r13 = ECID_ProvideCapabilities_ueRxTxSupTDD_r13.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("ueRxTxSupTDD_r13", -1);

         }
         else {
            ueRxTxSupTDD_r13 = null;
         }

         // decode periodicalReporting_r14

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("periodicalReporting_r14", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = ECID_ProvideCapabilities_periodicalReporting_r14.decodeEnumValue (buffer);
               periodicalReporting_r14 = ECID_ProvideCapabilities_periodicalReporting_r14.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("periodicalReporting_r14", -1);

         }
         else {
            periodicalReporting_r14 = null;
         }

         // decode triggeredReporting_r14

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("triggeredReporting_r14", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = ECID_ProvideCapabilities_triggeredReporting_r14.decodeEnumValue (buffer);
               triggeredReporting_r14 = ECID_ProvideCapabilities_triggeredReporting_r14.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("triggeredReporting_r14", -1);

         }
         else {
            triggeredReporting_r14 = null;
         }

         // decode idleStateForMeasurements_r14

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("idleStateForMeasurements_r14", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = ECID_ProvideCapabilities_idleStateForMeasurements_r14.decodeEnumValue (buffer);
               idleStateForMeasurements_r14 = ECID_ProvideCapabilities_idleStateForMeasurements_r14.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("idleStateForMeasurements_r14", -1);

         }
         else {
            idleStateForMeasurements_r14 = null;
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

      boolean extbit = ((ueRxTxSupTDD_r13 != null) ||
      (periodicalReporting_r14 != null) ||
      (triggeredReporting_r14 != null) ||
      (idleStateForMeasurements_r14 != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode ecid_MeasSupported

      if (ecid_MeasSupported != null) {
         buffer.getContext().eventDispatcher.startElement("ecid_MeasSupported", -1);

         ecid_MeasSupported.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("ecid_MeasSupported", -1);
      }
      else throw new Asn1MissingRequiredException ("ecid_MeasSupported");

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 4;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((ueRxTxSupTDD_r13 != null), null);
         buffer.encodeBit ((periodicalReporting_r14 != null), null);
         buffer.encodeBit ((triggeredReporting_r14 != null), null);
         buffer.encodeBit ((idleStateForMeasurements_r14 != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // ueRxTxSupTDD_r13

         if (ueRxTxSupTDD_r13 != null) {
            buffer.reset();
            ueRxTxSupTDD_r13.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("ueRxTxSupTDD_r13", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("ueRxTxSupTDD_r13", -1);
         }

         // periodicalReporting_r14

         if (periodicalReporting_r14 != null) {
            buffer.reset();
            periodicalReporting_r14.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("periodicalReporting_r14", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("periodicalReporting_r14", -1);
         }

         // triggeredReporting_r14

         if (triggeredReporting_r14 != null) {
            buffer.reset();
            triggeredReporting_r14.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("triggeredReporting_r14", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("triggeredReporting_r14", -1);
         }

         // idleStateForMeasurements_r14

         if (idleStateForMeasurements_r14 != null) {
            buffer.reset();
            idleStateForMeasurements_r14.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("idleStateForMeasurements_r14", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("idleStateForMeasurements_r14", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
