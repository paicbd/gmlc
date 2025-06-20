/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;
import org.mobicents.gmlc.slee.supl.ULP_Components.FQDN;

public class SPCTID extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SPCTID";
   }

   protected Asn1BitString rand;
   protected FQDN slpFQDN;
   protected Asn1OpenExt extElem1;

   public SPCTID () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SPCTID (
      Asn1BitString rand_,
      FQDN slpFQDN_
   ) throws Asn1Exception {
      super();
      setRand (rand_);
      setSlpFQDN (slpFQDN_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SPCTID (Asn1BitString rand_,
      String slpFQDN_
   ) throws Asn1Exception {
      super();
      setRand (rand_);
      setSlpFQDN (slpFQDN_);
   }

   public void init () {
      rand = null;
      slpFQDN = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SPCTID) ) return false;

      SPCTID rhs = (SPCTID) obj;

      if (rand == null) {
         if (rhs.rand != null) return false;
      }
      else {
         if (!rand.equals(rhs.rand)) {
            return false;
         }
      }

      if (slpFQDN == null) {
         if (rhs.slpFQDN != null) return false;
      }
      else {
         if (!slpFQDN.equals(rhs.slpFQDN)) {
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

      if (rand != null) __code = 31*__code + rand.hashCode();
      if (slpFQDN != null) __code = 31*__code + slpFQDN.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Rand
    */
   public Asn1BitString getRand () {
      return rand;
   }

   public void setRand (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 128)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.rand = value;
   }

   /**
    * Accessor/mutator methods for SlpFQDN
    */
   public FQDN getSlpFQDN () {
      return slpFQDN;
   }

   public void setSlpFQDN (FQDN value) 
      throws Asn1Exception
   {
      if (!((value.getLength() >= 1 && value.getLength() <= 255))) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.slpFQDN = value;
   }

   public void setSlpFQDN (String value) 
      throws Asn1Exception
   {
      setSlpFQDN (new FQDN(value));
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
         case 0: return rand;
         case 1: return slpFQDN;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "rand";
         case 1: return "slpFQDN";
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

      // decode rand

      buffer.getContext().eventDispatcher.startElement("rand", -1);

      rand = new Asn1BitString();
      rand.decode (buffer, 128, 128);
      buffer.getContext().eventDispatcher.endElement("rand", -1);

      // decode slpFQDN

      buffer.getContext().eventDispatcher.startElement("slpFQDN", -1);

      slpFQDN = new FQDN();
      slpFQDN.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("slpFQDN", -1);

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

      // encode rand

      if (rand != null) {
         buffer.getContext().eventDispatcher.startElement("rand", -1);

         rand.encode (buffer, 128, 128);

         buffer.getContext().eventDispatcher.endElement("rand", -1);
      }
      else throw new Asn1MissingRequiredException ("rand");

      // encode slpFQDN

      if (slpFQDN != null) {
         buffer.getContext().eventDispatcher.startElement("slpFQDN", -1);

         slpFQDN.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("slpFQDN", -1);
      }
      else throw new Asn1MissingRequiredException ("slpFQDN");

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
