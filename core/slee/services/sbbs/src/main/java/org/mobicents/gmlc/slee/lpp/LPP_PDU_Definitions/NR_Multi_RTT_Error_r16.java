/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_Multi_RTT_Error_r16 extends Asn1Choice {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NR-Multi-RTT-Error-r16";
   }

   // Choice element identifier constants
   public final static byte _LOCATIONSERVERERRORCAUSES_R16 = 1;
   public final static byte _TARGETDEVICEERRORCAUSES_R16 = 2;
   public final static byte _EXTELEM1 = 3;

   public NR_Multi_RTT_Error_r16 () {
      super();
   }

   public NR_Multi_RTT_Error_r16 (byte choiceId_, Asn1Type element_) {
      super();
      setElement (choiceId_, element_);
   }

   public String getElemName () {
      switch (choiceID) {
      case _LOCATIONSERVERERRORCAUSES_R16: return "locationServerErrorCauses_r16";
      case _TARGETDEVICEERRORCAUSES_R16: return "targetDeviceErrorCauses_r16";
      case _EXTELEM1: return "...";
      default: return "UNDEFINED";
      }
   }

   /**
    * Accessor/mutator methods for locationServerErrorCauses_r16
    */
   public NR_Multi_RTT_LocationServerErrorCauses_r16 getLocationServerErrorCauses_r16 () {
      return (choiceID == _LOCATIONSERVERERRORCAUSES_R16) ? (NR_Multi_RTT_LocationServerErrorCauses_r16)element : null;
   }
   public void setLocationServerErrorCauses_r16 (NR_Multi_RTT_LocationServerErrorCauses_r16 value) {
      setElement (_LOCATIONSERVERERRORCAUSES_R16, value);
   }
   public boolean isLocationServerErrorCauses_r16 () {
      return (choiceID == _LOCATIONSERVERERRORCAUSES_R16);
   }

   /**
    * Accessor/mutator methods for targetDeviceErrorCauses_r16
    */
   public NR_Multi_RTT_TargetDeviceErrorCauses_r16 getTargetDeviceErrorCauses_r16 () {
      return (choiceID == _TARGETDEVICEERRORCAUSES_R16) ? (NR_Multi_RTT_TargetDeviceErrorCauses_r16)element : null;
   }
   public void setTargetDeviceErrorCauses_r16 (NR_Multi_RTT_TargetDeviceErrorCauses_r16 value) {
      setElement (_TARGETDEVICEERRORCAUSES_R16, value);
   }
   public boolean isTargetDeviceErrorCauses_r16 () {
      return (choiceID == _TARGETDEVICEERRORCAUSES_R16);
   }

   /**
    * Accessor/mutator methods for extElem1
    */
   public Asn1OpenType getExtElem1 () {
      return (choiceID == _EXTELEM1) ? (Asn1OpenType)element : null;
   }
   public void setExtElem1 (Asn1OpenType value) {
      setElement (_EXTELEM1, value);
   }
   public boolean isExtElem1 () {
      return (choiceID == _EXTELEM1);
   }

   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // extension bit

      Asn1PerDecodeBuffer savedBuffer = null;
      boolean extbit = buffer.decodeBit ("extbit");

      if (!extbit) {
         int idx = (int) buffer.decodeConsWholeNumber (2, "index");
         choiceID = idx + 1;
         buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

         switch (idx) {
            // locationServerErrorCauses_r16
            case 0: { 
               NR_Multi_RTT_LocationServerErrorCauses_r16 locationServerErrorCauses_r16;
               element = locationServerErrorCauses_r16 = new NR_Multi_RTT_LocationServerErrorCauses_r16();
               locationServerErrorCauses_r16.decode (buffer);
               break;
            }

            // targetDeviceErrorCauses_r16
            case 1: { 
               NR_Multi_RTT_TargetDeviceErrorCauses_r16 targetDeviceErrorCauses_r16;
               element = targetDeviceErrorCauses_r16 = new NR_Multi_RTT_TargetDeviceErrorCauses_r16();
               targetDeviceErrorCauses_r16.decode (buffer);
               break;
            }

            default:
            throw new Asn1InvalidChoiceOptionException (buffer, idx);
         }
      }
      else {
         int idx = (int) buffer.decodeSmallNonNegWholeNumber ();
         choiceID = idx + 3;
         buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

         buffer.byteAlign();

         Asn1OpenType openType = new Asn1OpenType ();
         openType.decode (buffer);

         setElement (_EXTELEM1, openType);
      }

      buffer.getContext().eventDispatcher.endElement (getElemName(), -1);

      if (savedBuffer != null) {
         buffer = savedBuffer;
      }
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // extension bit

      boolean extbit = (choiceID > 2);
      buffer.encodeBit (extbit, "extbit");

      if (!extbit) {

         // Encode choice index value

         buffer.encodeConsWholeNumber (choiceID - 1, 2, "index");

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         switch (choiceID) {
            // locationServerErrorCauses_r16
            case _LOCATIONSERVERERRORCAUSES_R16:
               NR_Multi_RTT_LocationServerErrorCauses_r16 locationServerErrorCauses_r16 = (NR_Multi_RTT_LocationServerErrorCauses_r16) element;
               locationServerErrorCauses_r16.encode (buffer);
               break;

            // targetDeviceErrorCauses_r16
            case _TARGETDEVICEERRORCAUSES_R16:
               NR_Multi_RTT_TargetDeviceErrorCauses_r16 targetDeviceErrorCauses_r16 = (NR_Multi_RTT_TargetDeviceErrorCauses_r16) element;
               targetDeviceErrorCauses_r16.encode (buffer);
               break;

            default:
            throw new Asn1InvalidChoiceOptionException();
         }
      }
      else {
         // Encode extension choice index value

         buffer.encodeSmallNonNegWholeNumber (choiceID - 3);

         // Encode extension element data value

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         Asn1OpenType openType = (Asn1OpenType) element;
         buffer.byteAlign();
         openType.encode (buffer);
      }

      buffer.getContext().eventDispatcher.endElement("getElemName()", -1);
   }

}
