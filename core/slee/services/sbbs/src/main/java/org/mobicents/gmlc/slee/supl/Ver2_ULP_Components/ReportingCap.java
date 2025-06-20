/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class ReportingCap extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ReportingCap";
   }

   protected Asn1Integer minInt;
   protected Asn1Integer maxInt;  // optional
   protected RepMode repMode;
   protected BatchRepCap batchRepCap;  // optional
   protected Asn1OpenExt extElem1;

   public ReportingCap () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public ReportingCap (
      Asn1Integer minInt_,
      Asn1Integer maxInt_,
      RepMode repMode_,
      BatchRepCap batchRepCap_
   ) throws Asn1Exception {
      super();
      setMinInt (minInt_);
      setMaxInt (maxInt_);
      setRepMode (repMode_);
      setBatchRepCap (batchRepCap_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public ReportingCap (
      Asn1Integer minInt_,
      RepMode repMode_
   ) throws Asn1Exception {
      super();
      setMinInt (minInt_);
      setRepMode (repMode_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public ReportingCap (long minInt_,
      long maxInt_,
      RepMode repMode_,
      BatchRepCap batchRepCap_
   ) throws Asn1Exception {
      super();
      setMinInt (minInt_);
      setMaxInt (maxInt_);
      setRepMode (repMode_);
      setBatchRepCap (batchRepCap_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public ReportingCap (
      long minInt_,
      RepMode repMode_
   ) throws Asn1Exception {
      super();
      setMinInt (minInt_);
      setRepMode (repMode_);
   }

   public void init () {
      minInt = null;
      maxInt = null;
      repMode = null;
      batchRepCap = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof ReportingCap) ) return false;

      ReportingCap rhs = (ReportingCap) obj;

      if (minInt == null) {
         if (rhs.minInt != null) return false;
      }
      else {
         if (!minInt.equals(rhs.minInt)) {
            return false;
         }
      }

      if (maxInt == null) {
         if (rhs.maxInt != null) return false;
      }
      else {
         if (!maxInt.equals(rhs.maxInt)) {
            return false;
         }
      }

      if (repMode == null) {
         if (rhs.repMode != null) return false;
      }
      else {
         if (!repMode.equals(rhs.repMode)) {
            return false;
         }
      }

      if (batchRepCap == null) {
         if (rhs.batchRepCap != null) return false;
      }
      else {
         if (!batchRepCap.equals(rhs.batchRepCap)) {
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

      if (minInt != null) __code = 31*__code + minInt.hashCode();
      if (maxInt != null) __code = 31*__code + maxInt.hashCode();
      if (repMode != null) __code = 31*__code + repMode.hashCode();
      if (batchRepCap != null) __code = 31*__code + batchRepCap.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for MinInt
    */
   public Asn1Integer getMinInt () {
      return minInt;
   }

   public void setMinInt (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 3600))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.minInt = value;
   }

   public void setMinInt (long value) 
      throws Asn1Exception
   {
      setMinInt (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for MaxInt
    */
   public Asn1Integer getMaxInt () {
      return maxInt;
   }

   public void setMaxInt (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 1440))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.maxInt = value;
   }

   public void setMaxInt (long value) 
      throws Asn1Exception
   {
      setMaxInt (new Asn1Integer(value));
   }
   public boolean hasMaxInt () {
      return (maxInt != null);
   }

   /**
    * Accessor/mutator methods for RepMode
    */
   public RepMode getRepMode () {
      return repMode;
   }

   public void setRepMode (RepMode value) {
      this.repMode = value;
   }

   /**
    * Accessor/mutator methods for BatchRepCap
    */
   public BatchRepCap getBatchRepCap () {
      return batchRepCap;
   }

   public void setBatchRepCap (BatchRepCap value) {
      this.batchRepCap = value;
   }

   public boolean hasBatchRepCap () {
      return (batchRepCap != null);
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

   public int getElementCount() { return 5; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return minInt;
         case 1: return maxInt;
         case 2: return repMode;
         case 3: return batchRepCap;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "minInt";
         case 1: return "maxInt";
         case 2: return "repMode";
         case 3: return "batchRepCap";
         case 4: return null;
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

      boolean maxIntPresent = buffer.decodeBit ("maxIntPresent");
      boolean batchRepCapPresent = buffer.decodeBit ("batchRepCapPresent");

      // decode minInt

      buffer.getContext().eventDispatcher.startElement("minInt", -1);

      minInt = new Asn1Integer();
      minInt.decode (buffer, 1, 3600);
      buffer.getContext().eventDispatcher.endElement("minInt", -1);

      // decode maxInt

      if (maxIntPresent) {
         buffer.getContext().eventDispatcher.startElement("maxInt", -1);

         maxInt = new Asn1Integer();
         maxInt.decode (buffer, 1, 1440);
         buffer.getContext().eventDispatcher.endElement("maxInt", -1);
      }
      else {
         maxInt = null;
      }

      // decode repMode

      buffer.getContext().eventDispatcher.startElement("repMode", -1);

      repMode = new RepMode();
      repMode.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("repMode", -1);

      // decode batchRepCap

      if (batchRepCapPresent) {
         buffer.getContext().eventDispatcher.startElement("batchRepCap", -1);

         batchRepCap = new BatchRepCap();
         batchRepCap.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("batchRepCap", -1);
      }
      else {
         batchRepCap = null;
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

      buffer.encodeBit ((maxInt != null), null);
      buffer.encodeBit ((batchRepCap != null), null);

      // encode minInt

      if (minInt != null) {
         buffer.getContext().eventDispatcher.startElement("minInt", -1);

         minInt.encode (buffer, 1, 3600);

         buffer.getContext().eventDispatcher.endElement("minInt", -1);
      }
      else throw new Asn1MissingRequiredException ("minInt");

      // encode maxInt

      if (maxInt != null) {
         buffer.getContext().eventDispatcher.startElement("maxInt", -1);

         maxInt.encode (buffer, 1, 1440);

         buffer.getContext().eventDispatcher.endElement("maxInt", -1);
      }

      // encode repMode

      if (repMode != null) {
         buffer.getContext().eventDispatcher.startElement("repMode", -1);

         repMode.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("repMode", -1);
      }
      else throw new Asn1MissingRequiredException ("repMode");

      // encode batchRepCap

      if (batchRepCap != null) {
         buffer.getContext().eventDispatcher.startElement("batchRepCap", -1);

         batchRepCap.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("batchRepCap", -1);
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
