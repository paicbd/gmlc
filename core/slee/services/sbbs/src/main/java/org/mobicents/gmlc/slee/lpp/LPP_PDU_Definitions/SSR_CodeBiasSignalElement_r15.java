/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class SSR_CodeBiasSignalElement_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SSR-CodeBiasSignalElement-r15";
   }

   protected GNSS_SignalID signal_and_tracking_mode_ID_r15;
   protected Asn1Integer codeBias_r15;
   protected Asn1OpenExt extElem1;

   public SSR_CodeBiasSignalElement_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SSR_CodeBiasSignalElement_r15 (
      GNSS_SignalID signal_and_tracking_mode_ID_r15_,
      Asn1Integer codeBias_r15_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r15 (signal_and_tracking_mode_ID_r15_);
      setCodeBias_r15 (codeBias_r15_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SSR_CodeBiasSignalElement_r15 (GNSS_SignalID signal_and_tracking_mode_ID_r15_,
      long codeBias_r15_
   ) throws Asn1Exception {
      super();
      setSignal_and_tracking_mode_ID_r15 (signal_and_tracking_mode_ID_r15_);
      setCodeBias_r15 (codeBias_r15_);
   }

   public void init () {
      signal_and_tracking_mode_ID_r15 = null;
      codeBias_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SSR_CodeBiasSignalElement_r15) ) return false;

      SSR_CodeBiasSignalElement_r15 rhs = (SSR_CodeBiasSignalElement_r15) obj;

      if (signal_and_tracking_mode_ID_r15 == null) {
         if (rhs.signal_and_tracking_mode_ID_r15 != null) return false;
      }
      else {
         if (!signal_and_tracking_mode_ID_r15.equals(rhs.signal_and_tracking_mode_ID_r15)) {
            return false;
         }
      }

      if (codeBias_r15 == null) {
         if (rhs.codeBias_r15 != null) return false;
      }
      else {
         if (!codeBias_r15.equals(rhs.codeBias_r15)) {
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

      if (signal_and_tracking_mode_ID_r15 != null) __code = 31*__code + signal_and_tracking_mode_ID_r15.hashCode();
      if (codeBias_r15 != null) __code = 31*__code + codeBias_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Signal_and_tracking_mode_ID_r15
    */
   public GNSS_SignalID getSignal_and_tracking_mode_ID_r15 () {
      return signal_and_tracking_mode_ID_r15;
   }

   public void setSignal_and_tracking_mode_ID_r15 (GNSS_SignalID value) {
      this.signal_and_tracking_mode_ID_r15 = value;
   }

   /**
    * Accessor/mutator methods for CodeBias_r15
    */
   public Asn1Integer getCodeBias_r15 () {
      return codeBias_r15;
   }

   public void setCodeBias_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8192 && value.value <= 8191))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.codeBias_r15 = value;
   }

   public void setCodeBias_r15 (long value) 
      throws Asn1Exception
   {
      setCodeBias_r15 (new Asn1Integer(value));
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
         case 0: return signal_and_tracking_mode_ID_r15;
         case 1: return codeBias_r15;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "signal-and-tracking-mode-ID-r15";
         case 1: return "codeBias-r15";
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

      // decode signal_and_tracking_mode_ID_r15

      buffer.getContext().eventDispatcher.startElement("signal_and_tracking_mode_ID_r15", -1);

      signal_and_tracking_mode_ID_r15 = new GNSS_SignalID();
      signal_and_tracking_mode_ID_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("signal_and_tracking_mode_ID_r15", -1);

      // decode codeBias_r15

      buffer.getContext().eventDispatcher.startElement("codeBias_r15", -1);

      codeBias_r15 = new Asn1Integer();
      codeBias_r15.decode (buffer, -8192, 8191);
      buffer.getContext().eventDispatcher.endElement("codeBias_r15", -1);

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

      // encode signal_and_tracking_mode_ID_r15

      if (signal_and_tracking_mode_ID_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("signal_and_tracking_mode_ID_r15", -1);

         signal_and_tracking_mode_ID_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("signal_and_tracking_mode_ID_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("signal_and_tracking_mode_ID_r15");

      // encode codeBias_r15

      if (codeBias_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("codeBias_r15", -1);

         codeBias_r15.encode (buffer, -8192, 8191);

         buffer.getContext().eventDispatcher.endElement("codeBias_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("codeBias_r15");

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
