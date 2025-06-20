/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Components;

import com.objsys.asn1j.runtime.*;

public class IPAddress extends Asn1Choice {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "IPAddress";
   }

   // Choice element identifier constants
   public final static byte _IPV4ADDRESS = 1;
   public final static byte _IPV6ADDRESS = 2;

   public IPAddress () {
      super();
   }

   public IPAddress (byte choiceId_, Asn1Type element_) {
      super();
      setElement (choiceId_, element_);
   }

   public String getElemName () {
      switch (choiceID) {
      case _IPV4ADDRESS: return "ipv4Address";
      case _IPV6ADDRESS: return "ipv6Address";
      default: return "UNDEFINED";
      }
   }

   /**
    * Accessor/mutator methods for ipv4Address
    */
   public Asn1OctetString getIpv4Address () {
      return (choiceID == _IPV4ADDRESS) ? (Asn1OctetString)element : null;
   }
   public void setIpv4Address (Asn1OctetString value) {
      setElement (_IPV4ADDRESS, value);
   }
   public boolean isIpv4Address () {
      return (choiceID == _IPV4ADDRESS);
   }

   /**
    * Accessor/mutator methods for ipv6Address
    */
   public Asn1OctetString getIpv6Address () {
      return (choiceID == _IPV6ADDRESS) ? (Asn1OctetString)element : null;
   }
   public void setIpv6Address (Asn1OctetString value) {
      setElement (_IPV6ADDRESS, value);
   }
   public boolean isIpv6Address () {
      return (choiceID == _IPV6ADDRESS);
   }

   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int idx = (int) buffer.decodeConsWholeNumber (2, "index");
      choiceID = idx + 1;
      buffer.getContext().eventDispatcher.startElement (getElemName(), -1);

      switch (idx) {
         // ipv4Address
         case 0: { 
            Asn1OctetString ipv4Address;
            element = ipv4Address = new Asn1OctetString();
            ipv4Address.decode (buffer, 4, 4);
            break;
         }

         // ipv6Address
         case 1: { 
            Asn1OctetString ipv6Address;
            element = ipv6Address = new Asn1OctetString();
            ipv6Address.decode (buffer, 16, 16);
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
         // ipv4Address
         case _IPV4ADDRESS:
            Asn1OctetString ipv4Address = (Asn1OctetString) element;
            ipv4Address.encode (buffer, 4, 4);
            break;

         // ipv6Address
         case _IPV6ADDRESS:
            Asn1OctetString ipv6Address = (Asn1OctetString) element;
            ipv6Address.encode (buffer, 16, 16);
            break;

         default:
         throw new Asn1InvalidChoiceOptionException();
      }

      buffer.getContext().eventDispatcher.endElement("getElemName()", -1);
   }

}
