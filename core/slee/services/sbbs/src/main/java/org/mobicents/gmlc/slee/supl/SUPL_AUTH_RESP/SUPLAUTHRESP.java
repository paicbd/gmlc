/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.SUPL_AUTH_RESP;

import com.objsys.asn1j.runtime.*;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.SPCSETKey;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.SPCTID;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.SPCSETKeylifetime;

public class SUPLAUTHRESP extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_SUPL_AUTH_RESPRtkey._rtkey);
      Asn1Type._setLicLocation(_SUPL_AUTH_RESPRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SUPLAUTHRESP";
   }

   protected SPCSETKey sPCSETKey;
   protected SPCTID spctid;
   protected SPCSETKeylifetime sPCSETKeylifetime;  // optional
   protected Asn1OpenExt extElem1;

   public SUPLAUTHRESP () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SUPLAUTHRESP (
      SPCSETKey sPCSETKey_,
      SPCTID spctid_,
      SPCSETKeylifetime sPCSETKeylifetime_
   ) throws Asn1Exception {
      super();
      setSPCSETKey (sPCSETKey_);
      setSpctid (spctid_);
      setSPCSETKeylifetime (sPCSETKeylifetime_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public SUPLAUTHRESP (
      SPCSETKey sPCSETKey_,
      SPCTID spctid_
   ) throws Asn1Exception {
      super();
      setSPCSETKey (sPCSETKey_);
      setSpctid (spctid_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SUPLAUTHRESP (SPCSETKey sPCSETKey_,
      SPCTID spctid_,
      long sPCSETKeylifetime_
   ) throws Asn1Exception {
      super();
      setSPCSETKey (sPCSETKey_);
      setSpctid (spctid_);
      setSPCSETKeylifetime (sPCSETKeylifetime_);
   }

   public void init () {
      sPCSETKey = null;
      spctid = null;
      sPCSETKeylifetime = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SUPLAUTHRESP) ) return false;

      SUPLAUTHRESP rhs = (SUPLAUTHRESP) obj;

      if (sPCSETKey == null) {
         if (rhs.sPCSETKey != null) return false;
      }
      else {
         if (!sPCSETKey.equals(rhs.sPCSETKey)) {
            return false;
         }
      }

      if (spctid == null) {
         if (rhs.spctid != null) return false;
      }
      else {
         if (!spctid.equals(rhs.spctid)) {
            return false;
         }
      }

      if (sPCSETKeylifetime == null) {
         if (rhs.sPCSETKeylifetime != null) return false;
      }
      else {
         if (!sPCSETKeylifetime.equals(rhs.sPCSETKeylifetime)) {
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

      if (sPCSETKey != null) __code = 31*__code + sPCSETKey.hashCode();
      if (spctid != null) __code = 31*__code + spctid.hashCode();
      if (sPCSETKeylifetime != null) __code = 31*__code + sPCSETKeylifetime.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for SPCSETKey
    */
   public SPCSETKey getSPCSETKey () {
      return sPCSETKey;
   }

   public void setSPCSETKey (SPCSETKey value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 128)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.sPCSETKey = value;
   }

   /**
    * Accessor/mutator methods for Spctid
    */
   public SPCTID getSpctid () {
      return spctid;
   }

   public void setSpctid (SPCTID value) {
      this.spctid = value;
   }

   /**
    * Accessor/mutator methods for SPCSETKeylifetime
    */
   public SPCSETKeylifetime getSPCSETKeylifetime () {
      return sPCSETKeylifetime;
   }

   public void setSPCSETKeylifetime (SPCSETKeylifetime value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 24))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.sPCSETKeylifetime = value;
   }

   public void setSPCSETKeylifetime (long value) 
      throws Asn1Exception
   {
      setSPCSETKeylifetime (new SPCSETKeylifetime(value));
   }
   public boolean hasSPCSETKeylifetime () {
      return (sPCSETKeylifetime != null);
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

   public int getElementCount() { return 4; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return sPCSETKey;
         case 1: return spctid;
         case 2: return sPCSETKeylifetime;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "sPCSETKey";
         case 1: return "spctid";
         case 2: return "sPCSETKeylifetime";
         case 3: return null;
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

      boolean sPCSETKeylifetimePresent = buffer.decodeBit ("sPCSETKeylifetimePresent");

      // decode sPCSETKey

      buffer.getContext().eventDispatcher.startElement("sPCSETKey", -1);

      sPCSETKey = new SPCSETKey();
      sPCSETKey.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("sPCSETKey", -1);

      // decode spctid

      buffer.getContext().eventDispatcher.startElement("spctid", -1);

      spctid = new SPCTID();
      spctid.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("spctid", -1);

      // decode sPCSETKeylifetime

      if (sPCSETKeylifetimePresent) {
         buffer.getContext().eventDispatcher.startElement("sPCSETKeylifetime", -1);

         sPCSETKeylifetime = new SPCSETKeylifetime();
         sPCSETKeylifetime.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("sPCSETKeylifetime", -1);
      }
      else {
         sPCSETKeylifetime = null;
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

      buffer.encodeBit ((sPCSETKeylifetime != null), null);

      // encode sPCSETKey

      if (sPCSETKey != null) {
         buffer.getContext().eventDispatcher.startElement("sPCSETKey", -1);

         sPCSETKey.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("sPCSETKey", -1);
      }
      else throw new Asn1MissingRequiredException ("sPCSETKey");

      // encode spctid

      if (spctid != null) {
         buffer.getContext().eventDispatcher.startElement("spctid", -1);

         spctid.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("spctid", -1);
      }
      else throw new Asn1MissingRequiredException ("spctid");

      // encode sPCSETKeylifetime

      if (sPCSETKeylifetime != null) {
         buffer.getContext().eventDispatcher.startElement("sPCSETKeylifetime", -1);

         sPCSETKeylifetime.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("sPCSETKeylifetime", -1);
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
