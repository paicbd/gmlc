/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NeQuickModelParameter extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "NeQuickModelParameter";
   }

   protected Asn1Integer ai0;
   protected Asn1Integer ai1;
   protected Asn1Integer ai2;
   protected Asn1Integer ionoStormFlag1;  // optional
   protected Asn1Integer ionoStormFlag2;  // optional
   protected Asn1Integer ionoStormFlag3;  // optional
   protected Asn1Integer ionoStormFlag4;  // optional
   protected Asn1Integer ionoStormFlag5;  // optional
   protected Asn1OpenExt extElem1;

   public NeQuickModelParameter () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public NeQuickModelParameter (
      Asn1Integer ai0_,
      Asn1Integer ai1_,
      Asn1Integer ai2_,
      Asn1Integer ionoStormFlag1_,
      Asn1Integer ionoStormFlag2_,
      Asn1Integer ionoStormFlag3_,
      Asn1Integer ionoStormFlag4_,
      Asn1Integer ionoStormFlag5_
   ) throws Asn1Exception {
      super();
      setAi0 (ai0_);
      setAi1 (ai1_);
      setAi2 (ai2_);
      setIonoStormFlag1 (ionoStormFlag1_);
      setIonoStormFlag2 (ionoStormFlag2_);
      setIonoStormFlag3 (ionoStormFlag3_);
      setIonoStormFlag4 (ionoStormFlag4_);
      setIonoStormFlag5 (ionoStormFlag5_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public NeQuickModelParameter (
      Asn1Integer ai0_,
      Asn1Integer ai1_,
      Asn1Integer ai2_
   ) throws Asn1Exception {
      super();
      setAi0 (ai0_);
      setAi1 (ai1_);
      setAi2 (ai2_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public NeQuickModelParameter (long ai0_,
      long ai1_,
      long ai2_,
      long ionoStormFlag1_,
      long ionoStormFlag2_,
      long ionoStormFlag3_,
      long ionoStormFlag4_,
      long ionoStormFlag5_
   ) throws Asn1Exception {
      super();
      setAi0 (ai0_);
      setAi1 (ai1_);
      setAi2 (ai2_);
      setIonoStormFlag1 (ionoStormFlag1_);
      setIonoStormFlag2 (ionoStormFlag2_);
      setIonoStormFlag3 (ionoStormFlag3_);
      setIonoStormFlag4 (ionoStormFlag4_);
      setIonoStormFlag5 (ionoStormFlag5_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public NeQuickModelParameter (
      long ai0_,
      long ai1_,
      long ai2_
   ) throws Asn1Exception {
      super();
      setAi0 (ai0_);
      setAi1 (ai1_);
      setAi2 (ai2_);
   }

   public void init () {
      ai0 = null;
      ai1 = null;
      ai2 = null;
      ionoStormFlag1 = null;
      ionoStormFlag2 = null;
      ionoStormFlag3 = null;
      ionoStormFlag4 = null;
      ionoStormFlag5 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof NeQuickModelParameter) ) return false;

      NeQuickModelParameter rhs = (NeQuickModelParameter) obj;

      if (ai0 == null) {
         if (rhs.ai0 != null) return false;
      }
      else {
         if (!ai0.equals(rhs.ai0)) {
            return false;
         }
      }

      if (ai1 == null) {
         if (rhs.ai1 != null) return false;
      }
      else {
         if (!ai1.equals(rhs.ai1)) {
            return false;
         }
      }

      if (ai2 == null) {
         if (rhs.ai2 != null) return false;
      }
      else {
         if (!ai2.equals(rhs.ai2)) {
            return false;
         }
      }

      if (ionoStormFlag1 == null) {
         if (rhs.ionoStormFlag1 != null) return false;
      }
      else {
         if (!ionoStormFlag1.equals(rhs.ionoStormFlag1)) {
            return false;
         }
      }

      if (ionoStormFlag2 == null) {
         if (rhs.ionoStormFlag2 != null) return false;
      }
      else {
         if (!ionoStormFlag2.equals(rhs.ionoStormFlag2)) {
            return false;
         }
      }

      if (ionoStormFlag3 == null) {
         if (rhs.ionoStormFlag3 != null) return false;
      }
      else {
         if (!ionoStormFlag3.equals(rhs.ionoStormFlag3)) {
            return false;
         }
      }

      if (ionoStormFlag4 == null) {
         if (rhs.ionoStormFlag4 != null) return false;
      }
      else {
         if (!ionoStormFlag4.equals(rhs.ionoStormFlag4)) {
            return false;
         }
      }

      if (ionoStormFlag5 == null) {
         if (rhs.ionoStormFlag5 != null) return false;
      }
      else {
         if (!ionoStormFlag5.equals(rhs.ionoStormFlag5)) {
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

      if (ai0 != null) __code = 31*__code + ai0.hashCode();
      if (ai1 != null) __code = 31*__code + ai1.hashCode();
      if (ai2 != null) __code = 31*__code + ai2.hashCode();
      if (ionoStormFlag1 != null) __code = 31*__code + ionoStormFlag1.hashCode();
      if (ionoStormFlag2 != null) __code = 31*__code + ionoStormFlag2.hashCode();
      if (ionoStormFlag3 != null) __code = 31*__code + ionoStormFlag3.hashCode();
      if (ionoStormFlag4 != null) __code = 31*__code + ionoStormFlag4.hashCode();
      if (ionoStormFlag5 != null) __code = 31*__code + ionoStormFlag5.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Ai0
    */
   public Asn1Integer getAi0 () {
      return ai0;
   }

   public void setAi0 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 2047))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ai0 = value;
   }

   public void setAi0 (long value) 
      throws Asn1Exception
   {
      setAi0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Ai1
    */
   public Asn1Integer getAi1 () {
      return ai1;
   }

   public void setAi1 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -1024 && value.value <= 1023))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ai1 = value;
   }

   public void setAi1 (long value) 
      throws Asn1Exception
   {
      setAi1 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Ai2
    */
   public Asn1Integer getAi2 () {
      return ai2;
   }

   public void setAi2 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8192 && value.value <= 8191))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ai2 = value;
   }

   public void setAi2 (long value) 
      throws Asn1Exception
   {
      setAi2 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for IonoStormFlag1
    */
   public Asn1Integer getIonoStormFlag1 () {
      return ionoStormFlag1;
   }

   public void setIonoStormFlag1 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ionoStormFlag1 = value;
   }

   public void setIonoStormFlag1 (long value) 
      throws Asn1Exception
   {
      setIonoStormFlag1 (new Asn1Integer(value));
   }
   public boolean hasIonoStormFlag1 () {
      return (ionoStormFlag1 != null);
   }

   /**
    * Accessor/mutator methods for IonoStormFlag2
    */
   public Asn1Integer getIonoStormFlag2 () {
      return ionoStormFlag2;
   }

   public void setIonoStormFlag2 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ionoStormFlag2 = value;
   }

   public void setIonoStormFlag2 (long value) 
      throws Asn1Exception
   {
      setIonoStormFlag2 (new Asn1Integer(value));
   }
   public boolean hasIonoStormFlag2 () {
      return (ionoStormFlag2 != null);
   }

   /**
    * Accessor/mutator methods for IonoStormFlag3
    */
   public Asn1Integer getIonoStormFlag3 () {
      return ionoStormFlag3;
   }

   public void setIonoStormFlag3 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ionoStormFlag3 = value;
   }

   public void setIonoStormFlag3 (long value) 
      throws Asn1Exception
   {
      setIonoStormFlag3 (new Asn1Integer(value));
   }
   public boolean hasIonoStormFlag3 () {
      return (ionoStormFlag3 != null);
   }

   /**
    * Accessor/mutator methods for IonoStormFlag4
    */
   public Asn1Integer getIonoStormFlag4 () {
      return ionoStormFlag4;
   }

   public void setIonoStormFlag4 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ionoStormFlag4 = value;
   }

   public void setIonoStormFlag4 (long value) 
      throws Asn1Exception
   {
      setIonoStormFlag4 (new Asn1Integer(value));
   }
   public boolean hasIonoStormFlag4 () {
      return (ionoStormFlag4 != null);
   }

   /**
    * Accessor/mutator methods for IonoStormFlag5
    */
   public Asn1Integer getIonoStormFlag5 () {
      return ionoStormFlag5;
   }

   public void setIonoStormFlag5 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ionoStormFlag5 = value;
   }

   public void setIonoStormFlag5 (long value) 
      throws Asn1Exception
   {
      setIonoStormFlag5 (new Asn1Integer(value));
   }
   public boolean hasIonoStormFlag5 () {
      return (ionoStormFlag5 != null);
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

   public int getElementCount() { return 9; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return ai0;
         case 1: return ai1;
         case 2: return ai2;
         case 3: return ionoStormFlag1;
         case 4: return ionoStormFlag2;
         case 5: return ionoStormFlag3;
         case 6: return ionoStormFlag4;
         case 7: return ionoStormFlag5;
         case 8: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "ai0";
         case 1: return "ai1";
         case 2: return "ai2";
         case 3: return "ionoStormFlag1";
         case 4: return "ionoStormFlag2";
         case 5: return "ionoStormFlag3";
         case 6: return "ionoStormFlag4";
         case 7: return "ionoStormFlag5";
         case 8: return null;
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

      boolean ionoStormFlag1Present = buffer.decodeBit ("ionoStormFlag1Present");
      boolean ionoStormFlag2Present = buffer.decodeBit ("ionoStormFlag2Present");
      boolean ionoStormFlag3Present = buffer.decodeBit ("ionoStormFlag3Present");
      boolean ionoStormFlag4Present = buffer.decodeBit ("ionoStormFlag4Present");
      boolean ionoStormFlag5Present = buffer.decodeBit ("ionoStormFlag5Present");

      // decode ai0

      buffer.getContext().eventDispatcher.startElement("ai0", -1);

      ai0 = new Asn1Integer();
      ai0.decode (buffer, 0, 2047);
      buffer.getContext().eventDispatcher.endElement("ai0", -1);

      // decode ai1

      buffer.getContext().eventDispatcher.startElement("ai1", -1);

      ai1 = new Asn1Integer();
      ai1.decode (buffer, -1024, 1023);
      buffer.getContext().eventDispatcher.endElement("ai1", -1);

      // decode ai2

      buffer.getContext().eventDispatcher.startElement("ai2", -1);

      ai2 = new Asn1Integer();
      ai2.decode (buffer, -8192, 8191);
      buffer.getContext().eventDispatcher.endElement("ai2", -1);

      // decode ionoStormFlag1

      if (ionoStormFlag1Present) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag1", -1);

         ionoStormFlag1 = new Asn1Integer();
         ionoStormFlag1.decode (buffer, 0, 1);
         buffer.getContext().eventDispatcher.endElement("ionoStormFlag1", -1);
      }
      else {
         ionoStormFlag1 = null;
      }

      // decode ionoStormFlag2

      if (ionoStormFlag2Present) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag2", -1);

         ionoStormFlag2 = new Asn1Integer();
         ionoStormFlag2.decode (buffer, 0, 1);
         buffer.getContext().eventDispatcher.endElement("ionoStormFlag2", -1);
      }
      else {
         ionoStormFlag2 = null;
      }

      // decode ionoStormFlag3

      if (ionoStormFlag3Present) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag3", -1);

         ionoStormFlag3 = new Asn1Integer();
         ionoStormFlag3.decode (buffer, 0, 1);
         buffer.getContext().eventDispatcher.endElement("ionoStormFlag3", -1);
      }
      else {
         ionoStormFlag3 = null;
      }

      // decode ionoStormFlag4

      if (ionoStormFlag4Present) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag4", -1);

         ionoStormFlag4 = new Asn1Integer();
         ionoStormFlag4.decode (buffer, 0, 1);
         buffer.getContext().eventDispatcher.endElement("ionoStormFlag4", -1);
      }
      else {
         ionoStormFlag4 = null;
      }

      // decode ionoStormFlag5

      if (ionoStormFlag5Present) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag5", -1);

         ionoStormFlag5 = new Asn1Integer();
         ionoStormFlag5.decode (buffer, 0, 1);
         buffer.getContext().eventDispatcher.endElement("ionoStormFlag5", -1);
      }
      else {
         ionoStormFlag5 = null;
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

      buffer.encodeBit ((ionoStormFlag1 != null), null);
      buffer.encodeBit ((ionoStormFlag2 != null), null);
      buffer.encodeBit ((ionoStormFlag3 != null), null);
      buffer.encodeBit ((ionoStormFlag4 != null), null);
      buffer.encodeBit ((ionoStormFlag5 != null), null);

      // encode ai0

      if (ai0 != null) {
         buffer.getContext().eventDispatcher.startElement("ai0", -1);

         ai0.encode (buffer, 0, 2047);

         buffer.getContext().eventDispatcher.endElement("ai0", -1);
      }
      else throw new Asn1MissingRequiredException ("ai0");

      // encode ai1

      if (ai1 != null) {
         buffer.getContext().eventDispatcher.startElement("ai1", -1);

         ai1.encode (buffer, -1024, 1023);

         buffer.getContext().eventDispatcher.endElement("ai1", -1);
      }
      else throw new Asn1MissingRequiredException ("ai1");

      // encode ai2

      if (ai2 != null) {
         buffer.getContext().eventDispatcher.startElement("ai2", -1);

         ai2.encode (buffer, -8192, 8191);

         buffer.getContext().eventDispatcher.endElement("ai2", -1);
      }
      else throw new Asn1MissingRequiredException ("ai2");

      // encode ionoStormFlag1

      if (ionoStormFlag1 != null) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag1", -1);

         ionoStormFlag1.encode (buffer, 0, 1);

         buffer.getContext().eventDispatcher.endElement("ionoStormFlag1", -1);
      }

      // encode ionoStormFlag2

      if (ionoStormFlag2 != null) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag2", -1);

         ionoStormFlag2.encode (buffer, 0, 1);

         buffer.getContext().eventDispatcher.endElement("ionoStormFlag2", -1);
      }

      // encode ionoStormFlag3

      if (ionoStormFlag3 != null) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag3", -1);

         ionoStormFlag3.encode (buffer, 0, 1);

         buffer.getContext().eventDispatcher.endElement("ionoStormFlag3", -1);
      }

      // encode ionoStormFlag4

      if (ionoStormFlag4 != null) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag4", -1);

         ionoStormFlag4.encode (buffer, 0, 1);

         buffer.getContext().eventDispatcher.endElement("ionoStormFlag4", -1);
      }

      // encode ionoStormFlag5

      if (ionoStormFlag5 != null) {
         buffer.getContext().eventDispatcher.startElement("ionoStormFlag5", -1);

         ionoStormFlag5.encode (buffer, 0, 1);

         buffer.getContext().eventDispatcher.endElement("ionoStormFlag5", -1);
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
