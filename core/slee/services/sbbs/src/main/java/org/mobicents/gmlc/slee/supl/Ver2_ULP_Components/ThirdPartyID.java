/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class ThirdPartyID extends Asn1Choice {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ThirdPartyID";
   }

   // Choice element identifier constants
   public final static byte _LOGICALNAME = 1;
   public final static byte _MSISDN = 2;
   public final static byte _EMAILADDR = 3;
   public final static byte _SIP_URI = 4;
   public final static byte _IMS_PUBLIC_IDENTITY = 5;
   public final static byte _MIN = 6;
   public final static byte _MDN = 7;
   public final static byte _URI = 8;
   public final static byte _EXTELEM1 = 9;

   public final static Asn1CharSet sip_uri_CHARSET =
      new Asn1DiscreteCharSet ("#%-./0123456789:?@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz~");

   public final static Asn1CharSet ims_public_identity_CHARSET =
      new Asn1DiscreteCharSet ("#%-./0123456789:?@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz~");

   public final static Asn1CharSet uri_CHARSET =
      new Asn1DiscreteCharSet ("#%-./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz~");

   public ThirdPartyID () {
      super();
   }

   public ThirdPartyID (byte choiceId_, Asn1Type element_) {
      super();
      setElement (choiceId_, element_);
   }

   public String getElemName () {
      switch (choiceID) {
      case _LOGICALNAME: return "logicalName";
      case _MSISDN: return "msisdn";
      case _EMAILADDR: return "emailaddr";
      case _SIP_URI: return "sip_uri";
      case _IMS_PUBLIC_IDENTITY: return "ims_public_identity";
      case _MIN: return "min";
      case _MDN: return "mdn";
      case _URI: return "uri";
      case _EXTELEM1: return "...";
      default: return "UNDEFINED";
      }
   }

   /**
    * Accessor/mutator methods for logicalName
    */
   public Asn1IA5String getLogicalName () {
      return (choiceID == _LOGICALNAME) ? (Asn1IA5String)element : null;
   }
   public void setLogicalName (Asn1IA5String value) {
      setElement (_LOGICALNAME, value);
   }
   public boolean isLogicalName () {
      return (choiceID == _LOGICALNAME);
   }

   /**
    * Accessor/mutator methods for msisdn
    */
   public Asn1OctetString getMsisdn () {
      return (choiceID == _MSISDN) ? (Asn1OctetString)element : null;
   }
   public void setMsisdn (Asn1OctetString value) {
      setElement (_MSISDN, value);
   }
   public boolean isMsisdn () {
      return (choiceID == _MSISDN);
   }

   /**
    * Accessor/mutator methods for emailaddr
    */
   public Asn1IA5String getEmailaddr () {
      return (choiceID == _EMAILADDR) ? (Asn1IA5String)element : null;
   }
   public void setEmailaddr (Asn1IA5String value) {
      setElement (_EMAILADDR, value);
   }
   public boolean isEmailaddr () {
      return (choiceID == _EMAILADDR);
   }

   /**
    * Accessor/mutator methods for sip_uri
    */
   public Asn1VisibleString getSip_uri () {
      return (choiceID == _SIP_URI) ? (Asn1VisibleString)element : null;
   }
   public void setSip_uri (Asn1VisibleString value) {
      setElement (_SIP_URI, value);
   }
   public boolean isSip_uri () {
      return (choiceID == _SIP_URI);
   }

   /**
    * Accessor/mutator methods for ims_public_identity
    */
   public Asn1VisibleString getIms_public_identity () {
      return (choiceID == _IMS_PUBLIC_IDENTITY) ? (Asn1VisibleString)element : null;
   }
   public void setIms_public_identity (Asn1VisibleString value) {
      setElement (_IMS_PUBLIC_IDENTITY, value);
   }
   public boolean isIms_public_identity () {
      return (choiceID == _IMS_PUBLIC_IDENTITY);
   }

   /**
    * Accessor/mutator methods for min
    */
   public Asn1BitString getMin () {
      return (choiceID == _MIN) ? (Asn1BitString)element : null;
   }
   public void setMin (Asn1BitString value) {
      setElement (_MIN, value);
   }
   public boolean isMin () {
      return (choiceID == _MIN);
   }

   /**
    * Accessor/mutator methods for mdn
    */
   public Asn1OctetString getMdn () {
      return (choiceID == _MDN) ? (Asn1OctetString)element : null;
   }
   public void setMdn (Asn1OctetString value) {
      setElement (_MDN, value);
   }
   public boolean isMdn () {
      return (choiceID == _MDN);
   }

   /**
    * Accessor/mutator methods for uri
    */
   public Asn1VisibleString getUri () {
      return (choiceID == _URI) ? (Asn1VisibleString)element : null;
   }
   public void setUri (Asn1VisibleString value) {
      setElement (_URI, value);
   }
   public boolean isUri () {
      return (choiceID == _URI);
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
         int idx = (int) buffer.decodeConsWholeNumber (8, "index");
         choiceID = idx + 1;
         buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

         switch (idx) {
            // logicalName
            case 0: { 
               Asn1IA5String logicalName;
               element = logicalName = new Asn1IA5String();
               logicalName.decode (buffer, (Asn1CharSet)null, 1, 1000);
               break;
            }

            // msisdn
            case 1: { 
               Asn1OctetString msisdn;
               element = msisdn = new Asn1OctetString();
               msisdn.decode (buffer, 8, 8);
               break;
            }

            // emailaddr
            case 2: { 
               Asn1IA5String emailaddr;
               element = emailaddr = new Asn1IA5String();
               emailaddr.decode (buffer, (Asn1CharSet)null, 1, 1000);
               break;
            }

            // sip_uri
            case 3: { 
               Asn1VisibleString sip_uri;
               element = sip_uri = new Asn1VisibleString();
               sip_uri.decode (buffer, sip_uri_CHARSET, 1, 255);
               break;
            }

            // ims_public_identity
            case 4: { 
               Asn1VisibleString ims_public_identity;
               element = ims_public_identity = new Asn1VisibleString();
               ims_public_identity.decode (buffer, ims_public_identity_CHARSET, 1, 255);
               break;
            }

            // min
            case 5: { 
               Asn1BitString min;
               element = min = new Asn1BitString();
               min.decode (buffer, 34, 34);
               break;
            }

            // mdn
            case 6: { 
               Asn1OctetString mdn;
               element = mdn = new Asn1OctetString();
               mdn.decode (buffer, 8, 8);
               break;
            }

            // uri
            case 7: { 
               Asn1VisibleString uri;
               element = uri = new Asn1VisibleString();
               uri.decode (buffer, uri_CHARSET, 1, 255);
               break;
            }

            default:
            throw new Asn1InvalidChoiceOptionException (buffer, idx);
         }
      }
      else {
         int idx = (int) buffer.decodeSmallNonNegWholeNumber ();
         choiceID = idx + 9;
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

      boolean extbit = (choiceID > 8);
      buffer.encodeBit (extbit, "extbit");

      if (!extbit) {

         // Encode choice index value

         buffer.encodeConsWholeNumber (choiceID - 1, 8, "index");

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         switch (choiceID) {
            // logicalName
            case _LOGICALNAME:
               Asn1IA5String logicalName = (Asn1IA5String) element;
               logicalName.encode (buffer, (Asn1CharSet)null, 1, 1000);
               break;

            // msisdn
            case _MSISDN:
               Asn1OctetString msisdn = (Asn1OctetString) element;
               msisdn.encode (buffer, 8, 8);
               break;

            // emailaddr
            case _EMAILADDR:
               Asn1IA5String emailaddr = (Asn1IA5String) element;
               emailaddr.encode (buffer, (Asn1CharSet)null, 1, 1000);
               break;

            // sip_uri
            case _SIP_URI:
               Asn1VisibleString sip_uri = (Asn1VisibleString) element;
               sip_uri.encode (buffer, sip_uri_CHARSET, 1, 255);
               break;

            // ims_public_identity
            case _IMS_PUBLIC_IDENTITY:
               Asn1VisibleString ims_public_identity = (Asn1VisibleString) element;
               ims_public_identity.encode (buffer, ims_public_identity_CHARSET, 1, 255);
               break;

            // min
            case _MIN:
               Asn1BitString min = (Asn1BitString) element;
               min.encode (buffer, 34, 34);
               break;

            // mdn
            case _MDN:
               Asn1OctetString mdn = (Asn1OctetString) element;
               mdn.encode (buffer, 8, 8);
               break;

            // uri
            case _URI:
               Asn1VisibleString uri = (Asn1VisibleString) element;
               uri.encode (buffer, uri_CHARSET, 1, 255);
               break;

            default:
            throw new Asn1InvalidChoiceOptionException();
         }
      }
      else {
         // Encode extension choice index value

         buffer.encodeSmallNonNegWholeNumber (choiceID - 9);

         // Encode extension element data value

         buffer.getContext().eventDispatcher.startElement("getElemName()", -1);

         Asn1OpenType openType = (Asn1OpenType) element;
         buffer.byteAlign();
         openType.encode (buffer);
      }

      buffer.getContext().eventDispatcher.endElement("getElemName()", -1);
   }

}
