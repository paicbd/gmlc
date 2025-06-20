/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class UTRAN_GPSReferenceTimeResult_modeSpecificInfo extends Asn1Choice {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "CHOICE";
   }

   // Choice element identifier constants
   public final static byte _FDD = 1;
   public final static byte _TDD = 2;

   public UTRAN_GPSReferenceTimeResult_modeSpecificInfo () {
      super();
   }

   public UTRAN_GPSReferenceTimeResult_modeSpecificInfo (byte choiceId_, Asn1Type element_) {
      super();
      setElement (choiceId_, element_);
   }

   public String getElemName () {
      switch (choiceID) {
      case _FDD: return "fdd";
      case _TDD: return "tdd";
      default: return "UNDEFINED";
      }
   }

   /**
    * Accessor/mutator methods for fdd
    */
   public UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd getFdd () {
      return (choiceID == _FDD) ? (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd)element : null;
   }
   public void setFdd (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd value) {
      setElement (_FDD, value);
   }
   public boolean isFdd () {
      return (choiceID == _FDD);
   }

   /**
    * Accessor/mutator methods for tdd
    */
   public UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd getTdd () {
      return (choiceID == _TDD) ? (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd)element : null;
   }
   public void setTdd (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd value) {
      setElement (_TDD, value);
   }
   public boolean isTdd () {
      return (choiceID == _TDD);
   }

   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int idx = (int) buffer.decodeConsWholeNumber (2, "index");
      choiceID = idx + 1;
      buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

      switch (idx) {
         // fdd
         case 0: { 
            UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd fdd;
            element = fdd = new UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd();
            fdd.decode (buffer);
            break;
         }

         // tdd
         case 1: { 
            UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd tdd;
            element = tdd = new UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd();
            tdd.decode (buffer);
            break;
         }

         default:
         throw new Asn1InvalidChoiceOptionException (buffer, idx);
      }

      buffer.getContext().eventDispatcher.endElement (getElemName(), -1);
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // Encode choice index value

      buffer.encodeConsWholeNumber (choiceID - 1, 2, "index");

      buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

      switch (choiceID) {
         // fdd
         case _FDD:
            UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd fdd = (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_fdd) element;
            fdd.encode (buffer);
            break;

         // tdd
         case _TDD:
            UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd tdd = (UTRAN_GPSReferenceTimeResult_modeSpecificInfo_tdd) element;
            tdd.encode (buffer);
            break;

         default:
         throw new Asn1InvalidChoiceOptionException();
      }

      buffer.getContext().eventDispatcher.endElement("getElemName()", -1);
   }

}
