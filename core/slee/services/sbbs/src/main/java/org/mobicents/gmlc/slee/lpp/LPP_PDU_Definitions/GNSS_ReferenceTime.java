/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class GNSS_ReferenceTime extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GNSS-ReferenceTime";
   }

   protected GNSS_SystemTime gnss_SystemTime;
   protected Asn1Integer referenceTimeUnc;  // optional
   protected GNSS_ReferenceTime_gnss_ReferenceTimeForCells gnss_ReferenceTimeForCells;  // optional
   protected Asn1OpenExt extElem1;

   public GNSS_ReferenceTime () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GNSS_ReferenceTime (
      GNSS_SystemTime gnss_SystemTime_,
      Asn1Integer referenceTimeUnc_,
      GNSS_ReferenceTime_gnss_ReferenceTimeForCells gnss_ReferenceTimeForCells_
   ) throws Asn1Exception {
      super();
      setGnss_SystemTime (gnss_SystemTime_);
      setReferenceTimeUnc (referenceTimeUnc_);
      setGnss_ReferenceTimeForCells (gnss_ReferenceTimeForCells_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GNSS_ReferenceTime (
      GNSS_SystemTime gnss_SystemTime_
   ) throws Asn1Exception {
      super();
      setGnss_SystemTime (gnss_SystemTime_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GNSS_ReferenceTime (GNSS_SystemTime gnss_SystemTime_,
      long referenceTimeUnc_,
      GNSS_ReferenceTime_gnss_ReferenceTimeForCells gnss_ReferenceTimeForCells_
   ) throws Asn1Exception {
      super();
      setGnss_SystemTime (gnss_SystemTime_);
      setReferenceTimeUnc (referenceTimeUnc_);
      setGnss_ReferenceTimeForCells (gnss_ReferenceTimeForCells_);
   }

   public void init () {
      gnss_SystemTime = null;
      referenceTimeUnc = null;
      gnss_ReferenceTimeForCells = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GNSS_ReferenceTime) ) return false;

      GNSS_ReferenceTime rhs = (GNSS_ReferenceTime) obj;

      if (gnss_SystemTime == null) {
         if (rhs.gnss_SystemTime != null) return false;
      }
      else {
         if (!gnss_SystemTime.equals(rhs.gnss_SystemTime)) {
            return false;
         }
      }

      if (referenceTimeUnc == null) {
         if (rhs.referenceTimeUnc != null) return false;
      }
      else {
         if (!referenceTimeUnc.equals(rhs.referenceTimeUnc)) {
            return false;
         }
      }

      if (gnss_ReferenceTimeForCells == null) {
         if (rhs.gnss_ReferenceTimeForCells != null) return false;
      }
      else {
         if (!gnss_ReferenceTimeForCells.equals(rhs.gnss_ReferenceTimeForCells)) {
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

      if (gnss_SystemTime != null) __code = 31*__code + gnss_SystemTime.hashCode();
      if (referenceTimeUnc != null) __code = 31*__code + referenceTimeUnc.hashCode();
      if (gnss_ReferenceTimeForCells != null) __code = 31*__code + gnss_ReferenceTimeForCells.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Gnss_SystemTime
    */
   public GNSS_SystemTime getGnss_SystemTime () {
      return gnss_SystemTime;
   }

   public void setGnss_SystemTime (GNSS_SystemTime value) {
      this.gnss_SystemTime = value;
   }

   /**
    * Accessor/mutator methods for ReferenceTimeUnc
    */
   public Asn1Integer getReferenceTimeUnc () {
      return referenceTimeUnc;
   }

   public void setReferenceTimeUnc (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.referenceTimeUnc = value;
   }

   public void setReferenceTimeUnc (long value) 
      throws Asn1Exception
   {
      setReferenceTimeUnc (new Asn1Integer(value));
   }
   public boolean hasReferenceTimeUnc () {
      return (referenceTimeUnc != null);
   }

   /**
    * Accessor/mutator methods for Gnss_ReferenceTimeForCells
    */
   public GNSS_ReferenceTime_gnss_ReferenceTimeForCells getGnss_ReferenceTimeForCells () {
      return gnss_ReferenceTimeForCells;
   }

   public void setGnss_ReferenceTimeForCells (GNSS_ReferenceTime_gnss_ReferenceTimeForCells value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 1 && value.getElements().length <= 16))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.gnss_ReferenceTimeForCells = value;
   }

   public boolean hasGnss_ReferenceTimeForCells () {
      return (gnss_ReferenceTimeForCells != null);
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
         case 0: return gnss_SystemTime;
         case 1: return referenceTimeUnc;
         case 2: return gnss_ReferenceTimeForCells;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "gnss-SystemTime";
         case 1: return "referenceTimeUnc";
         case 2: return "gnss-ReferenceTimeForCells";
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

      boolean referenceTimeUncPresent = buffer.decodeBit ("referenceTimeUncPresent");
      boolean gnss_ReferenceTimeForCellsPresent = buffer.decodeBit ("gnss_ReferenceTimeForCellsPresent");

      // decode gnss_SystemTime

      buffer.getContext().eventDispatcher.startElement("gnss_SystemTime", -1);

      gnss_SystemTime = new GNSS_SystemTime();
      gnss_SystemTime.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("gnss_SystemTime", -1);

      // decode referenceTimeUnc

      if (referenceTimeUncPresent) {
         buffer.getContext().eventDispatcher.startElement("referenceTimeUnc", -1);

         referenceTimeUnc = new Asn1Integer();
         referenceTimeUnc.decode (buffer, 0, 127);
         buffer.getContext().eventDispatcher.endElement("referenceTimeUnc", -1);
      }
      else {
         referenceTimeUnc = null;
      }

      // decode gnss_ReferenceTimeForCells

      if (gnss_ReferenceTimeForCellsPresent) {
         buffer.getContext().eventDispatcher.startElement("gnss_ReferenceTimeForCells", -1);

         gnss_ReferenceTimeForCells = new GNSS_ReferenceTime_gnss_ReferenceTimeForCells();
         gnss_ReferenceTimeForCells.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("gnss_ReferenceTimeForCells", -1);
      }
      else {
         gnss_ReferenceTimeForCells = null;
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

      buffer.encodeBit ((referenceTimeUnc != null), null);
      buffer.encodeBit ((gnss_ReferenceTimeForCells != null), null);

      // encode gnss_SystemTime

      if (gnss_SystemTime != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_SystemTime", -1);

         gnss_SystemTime.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("gnss_SystemTime", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_SystemTime");

      // encode referenceTimeUnc

      if (referenceTimeUnc != null) {
         buffer.getContext().eventDispatcher.startElement("referenceTimeUnc", -1);

         referenceTimeUnc.encode (buffer, 0, 127);

         buffer.getContext().eventDispatcher.endElement("referenceTimeUnc", -1);
      }

      // encode gnss_ReferenceTimeForCells

      if (gnss_ReferenceTimeForCells != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_ReferenceTimeForCells", -1);

         gnss_ReferenceTimeForCells.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("gnss_ReferenceTimeForCells", -1);
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
