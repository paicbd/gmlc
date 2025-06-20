/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class SatListRelatedDataElement extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SatListRelatedDataElement";
   }

   protected SV_ID svID;
   protected Asn1BitString iod;
   protected Asn1Integer clockModelID;  // optional
   protected Asn1Integer orbitModelID;  // optional
   protected Asn1OpenExt extElem1;

   public SatListRelatedDataElement () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SatListRelatedDataElement (
      SV_ID svID_,
      Asn1BitString iod_,
      Asn1Integer clockModelID_,
      Asn1Integer orbitModelID_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setIod (iod_);
      setClockModelID (clockModelID_);
      setOrbitModelID (orbitModelID_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public SatListRelatedDataElement (
      SV_ID svID_,
      Asn1BitString iod_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setIod (iod_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SatListRelatedDataElement (SV_ID svID_,
      Asn1BitString iod_,
      long clockModelID_,
      long orbitModelID_
   ) throws Asn1Exception {
      super();
      setSvID (svID_);
      setIod (iod_);
      setClockModelID (clockModelID_);
      setOrbitModelID (orbitModelID_);
   }

   public void init () {
      svID = null;
      iod = null;
      clockModelID = null;
      orbitModelID = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SatListRelatedDataElement) ) return false;

      SatListRelatedDataElement rhs = (SatListRelatedDataElement) obj;

      if (svID == null) {
         if (rhs.svID != null) return false;
      }
      else {
         if (!svID.equals(rhs.svID)) {
            return false;
         }
      }

      if (iod == null) {
         if (rhs.iod != null) return false;
      }
      else {
         if (!iod.equals(rhs.iod)) {
            return false;
         }
      }

      if (clockModelID == null) {
         if (rhs.clockModelID != null) return false;
      }
      else {
         if (!clockModelID.equals(rhs.clockModelID)) {
            return false;
         }
      }

      if (orbitModelID == null) {
         if (rhs.orbitModelID != null) return false;
      }
      else {
         if (!orbitModelID.equals(rhs.orbitModelID)) {
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

      if (svID != null) __code = 31*__code + svID.hashCode();
      if (iod != null) __code = 31*__code + iod.hashCode();
      if (clockModelID != null) __code = 31*__code + clockModelID.hashCode();
      if (orbitModelID != null) __code = 31*__code + orbitModelID.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for SvID
    */
   public SV_ID getSvID () {
      return svID;
   }

   public void setSvID (SV_ID value) {
      this.svID = value;
   }

   /**
    * Accessor/mutator methods for Iod
    */
   public Asn1BitString getIod () {
      return iod;
   }

   public void setIod (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 11)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.iod = value;
   }

   /**
    * Accessor/mutator methods for ClockModelID
    */
   public Asn1Integer getClockModelID () {
      return clockModelID;
   }

   public void setClockModelID (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 8))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.clockModelID = value;
   }

   public void setClockModelID (long value) 
      throws Asn1Exception
   {
      setClockModelID (new Asn1Integer(value));
   }
   public boolean hasClockModelID () {
      return (clockModelID != null);
   }

   /**
    * Accessor/mutator methods for OrbitModelID
    */
   public Asn1Integer getOrbitModelID () {
      return orbitModelID;
   }

   public void setOrbitModelID (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 1 && value.value <= 8))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.orbitModelID = value;
   }

   public void setOrbitModelID (long value) 
      throws Asn1Exception
   {
      setOrbitModelID (new Asn1Integer(value));
   }
   public boolean hasOrbitModelID () {
      return (orbitModelID != null);
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
         case 0: return svID;
         case 1: return iod;
         case 2: return clockModelID;
         case 3: return orbitModelID;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "svID";
         case 1: return "iod";
         case 2: return "clockModelID";
         case 3: return "orbitModelID";
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

      boolean clockModelIDPresent = buffer.decodeBit ("clockModelIDPresent");
      boolean orbitModelIDPresent = buffer.decodeBit ("orbitModelIDPresent");

      // decode svID

      buffer.getContext().eventDispatcher.startElement("svID", -1);

      svID = new SV_ID();
      svID.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("svID", -1);

      // decode iod

      buffer.getContext().eventDispatcher.startElement("iod", -1);

      iod = new Asn1BitString();
      iod.decode (buffer, 11, 11);
      buffer.getContext().eventDispatcher.endElement("iod", -1);

      // decode clockModelID

      if (clockModelIDPresent) {
         buffer.getContext().eventDispatcher.startElement("clockModelID", -1);

         clockModelID = new Asn1Integer();
         clockModelID.decode (buffer, 1, 8);
         buffer.getContext().eventDispatcher.endElement("clockModelID", -1);
      }
      else {
         clockModelID = null;
      }

      // decode orbitModelID

      if (orbitModelIDPresent) {
         buffer.getContext().eventDispatcher.startElement("orbitModelID", -1);

         orbitModelID = new Asn1Integer();
         orbitModelID.decode (buffer, 1, 8);
         buffer.getContext().eventDispatcher.endElement("orbitModelID", -1);
      }
      else {
         orbitModelID = null;
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

      buffer.encodeBit ((clockModelID != null), null);
      buffer.encodeBit ((orbitModelID != null), null);

      // encode svID

      if (svID != null) {
         buffer.getContext().eventDispatcher.startElement("svID", -1);

         svID.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("svID", -1);
      }
      else throw new Asn1MissingRequiredException ("svID");

      // encode iod

      if (iod != null) {
         buffer.getContext().eventDispatcher.startElement("iod", -1);

         iod.encode (buffer, 11, 11);

         buffer.getContext().eventDispatcher.endElement("iod", -1);
      }
      else throw new Asn1MissingRequiredException ("iod");

      // encode clockModelID

      if (clockModelID != null) {
         buffer.getContext().eventDispatcher.startElement("clockModelID", -1);

         clockModelID.encode (buffer, 1, 8);

         buffer.getContext().eventDispatcher.endElement("clockModelID", -1);
      }

      // encode orbitModelID

      if (orbitModelID != null) {
         buffer.getContext().eventDispatcher.startElement("orbitModelID", -1);

         orbitModelID.encode (buffer, 1, 8);

         buffer.getContext().eventDispatcher.endElement("orbitModelID", -1);
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
