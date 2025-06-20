/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_RESPONSE;

import com.objsys.asn1j.runtime.*;

public class BatchRepConditions extends Asn1Choice {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_SUPL_TRIGGERED_RESPONSERtkey._rtkey);
      Asn1Type._setLicLocation(_SUPL_TRIGGERED_RESPONSERtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "BatchRepConditions";
   }

   // Choice element identifier constants
   public final static byte _NUM_INTERVAL = 1;
   public final static byte _NUM_MINUTES = 2;
   public final static byte _ENDOFSESSION = 3;
   public final static byte _EXTELEM1 = 4;

   public BatchRepConditions () {
      super();
   }

   public BatchRepConditions (byte choiceId_, Asn1Type element_) {
      super();
      setElement (choiceId_, element_);
   }

   public String getElemName () {
      switch (choiceID) {
      case _NUM_INTERVAL: return "num_interval";
      case _NUM_MINUTES: return "num_minutes";
      case _ENDOFSESSION: return "endofsession";
      case _EXTELEM1: return "...";
      default: return "UNDEFINED";
      }
   }

   /**
    * Accessor/mutator methods for num_interval
    */
   public Asn1Integer getNum_interval () {
      return (choiceID == _NUM_INTERVAL) ? (Asn1Integer)element : null;
   }
   public void setNum_interval (Asn1Integer value) {
      setElement (_NUM_INTERVAL, value);
   }
   public boolean isNum_interval () {
      return (choiceID == _NUM_INTERVAL);
   }

   /**
    * Accessor/mutator methods for num_minutes
    */
   public Asn1Integer getNum_minutes () {
      return (choiceID == _NUM_MINUTES) ? (Asn1Integer)element : null;
   }
   public void setNum_minutes (Asn1Integer value) {
      setElement (_NUM_MINUTES, value);
   }
   public boolean isNum_minutes () {
      return (choiceID == _NUM_MINUTES);
   }

   /**
    * Accessor/mutator methods for endofsession
    */
   public void setEndofsession () {
      setElement (_ENDOFSESSION, Asn1Null.NULL_VALUE);
   }
   public boolean isEndofsession () {
      return (choiceID == _ENDOFSESSION);
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
         int idx = (int) buffer.decodeConsWholeNumber (3, "index");
         choiceID = idx + 1;
         buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

         switch (idx) {
            // num_interval
            case 0: { 
               Asn1Integer num_interval;
               element = num_interval = new Asn1Integer();
               num_interval.decode (buffer, 1, 1024);
               break;
            }

            // num_minutes
            case 1: { 
               Asn1Integer num_minutes;
               element = num_minutes = new Asn1Integer();
               num_minutes.decode (buffer, 1, 2048);
               break;
            }

            // endofsession
            case 2: { 
               Asn1Null endofsession;
               element = endofsession = Asn1Null.NULL_VALUE;
               endofsession.decode (buffer);
               break;
            }

            default:
            throw new Asn1InvalidChoiceOptionException (buffer, idx);
         }
      }
      else {
         int idx = (int) buffer.decodeSmallNonNegWholeNumber ();
         choiceID = idx + 4;
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

      boolean extbit = (choiceID > 3);
      buffer.encodeBit (extbit, "extbit");

      if (!extbit) {

         // Encode choice index value

         buffer.encodeConsWholeNumber (choiceID - 1, 3, "index");

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         switch (choiceID) {
            // num_interval
            case _NUM_INTERVAL:
               Asn1Integer num_interval = (Asn1Integer) element;
               num_interval.encode (buffer, 1, 1024);
               break;

            // num_minutes
            case _NUM_MINUTES:
               Asn1Integer num_minutes = (Asn1Integer) element;
               num_minutes.encode (buffer, 1, 2048);
               break;

            // endofsession
            case _ENDOFSESSION:
               Asn1Null endofsession = (Asn1Null) element;
               endofsession.encode (buffer);
               break;

            default:
            throw new Asn1InvalidChoiceOptionException();
         }
      }
      else {
         // Encode extension choice index value

         buffer.encodeSmallNonNegWholeNumber (choiceID - 4);

         // Encode extension element data value

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         Asn1OpenType openType = (Asn1OpenType) element;
         buffer.byteAlign();
         openType.encode (buffer);
      }

      buffer.getContext().eventDispatcher.endElement("getElemName()", -1);
   }

}
