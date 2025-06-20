/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class OTDOA_RequestAssistanceData extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "OTDOA-RequestAssistanceData";
   }

   protected Asn1Integer physCellId;
   protected OTDOA_RequestAssistanceData_adType_r14 adType_r14;  // optional
   protected Asn1Integer nrPhysCellId_r15;  // optional
   protected Asn1OpenExt extElem1;

   public OTDOA_RequestAssistanceData () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public OTDOA_RequestAssistanceData (
      Asn1Integer physCellId_,
      OTDOA_RequestAssistanceData_adType_r14 adType_r14_,
      Asn1Integer nrPhysCellId_r15_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setAdType_r14 (adType_r14_);
      setNrPhysCellId_r15 (nrPhysCellId_r15_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public OTDOA_RequestAssistanceData (
      Asn1Integer physCellId_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public OTDOA_RequestAssistanceData (long physCellId_,
      OTDOA_RequestAssistanceData_adType_r14 adType_r14_,
      long nrPhysCellId_r15_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setAdType_r14 (adType_r14_);
      setNrPhysCellId_r15 (nrPhysCellId_r15_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public OTDOA_RequestAssistanceData (
      long physCellId_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
   }

   public void init () {
      physCellId = null;
      adType_r14 = null;
      nrPhysCellId_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof OTDOA_RequestAssistanceData) ) return false;

      OTDOA_RequestAssistanceData rhs = (OTDOA_RequestAssistanceData) obj;

      if (physCellId == null) {
         if (rhs.physCellId != null) return false;
      }
      else {
         if (!physCellId.equals(rhs.physCellId)) {
            return false;
         }
      }

      if (adType_r14 == null) {
         if (rhs.adType_r14 != null) return false;
      }
      else {
         if (!adType_r14.equals(rhs.adType_r14)) {
            return false;
         }
      }

      if (nrPhysCellId_r15 == null) {
         if (rhs.nrPhysCellId_r15 != null) return false;
      }
      else {
         if (!nrPhysCellId_r15.equals(rhs.nrPhysCellId_r15)) {
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

      if (physCellId != null) __code = 31*__code + physCellId.hashCode();
      if (adType_r14 != null) __code = 31*__code + adType_r14.hashCode();
      if (nrPhysCellId_r15 != null) __code = 31*__code + nrPhysCellId_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for PhysCellId
    */
   public Asn1Integer getPhysCellId () {
      return physCellId;
   }

   public void setPhysCellId (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 503))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.physCellId = value;
   }

   public void setPhysCellId (long value) 
      throws Asn1Exception
   {
      setPhysCellId (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for AdType_r14
    */
   public OTDOA_RequestAssistanceData_adType_r14 getAdType_r14 () {
      return adType_r14;
   }

   public void setAdType_r14 (OTDOA_RequestAssistanceData_adType_r14 value) 
      throws Asn1Exception
   {
      if (!((value.getLength() >= 1 && value.getLength() <= 8))) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.adType_r14 = value;
   }

   public boolean hasAdType_r14 () {
      return (adType_r14 != null);
   }

   /**
    * Accessor/mutator methods for NrPhysCellId_r15
    */
   public Asn1Integer getNrPhysCellId_r15 () {
      return nrPhysCellId_r15;
   }

   public void setNrPhysCellId_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1007))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.nrPhysCellId_r15 = value;
   }

   public void setNrPhysCellId_r15 (long value) 
      throws Asn1Exception
   {
      setNrPhysCellId_r15 (new Asn1Integer(value));
   }
   public boolean hasNrPhysCellId_r15 () {
      return (nrPhysCellId_r15 != null);
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
         case 0: return physCellId;
         case 1: return adType_r14;
         case 2: return nrPhysCellId_r15;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "physCellId";
         case 1: return "adType-r14";
         case 2: return "nrPhysCellId-r15";
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

      // decode physCellId

      buffer.getContext().eventDispatcher.startElement("physCellId", -1);

      physCellId = new Asn1Integer();
      physCellId.decode (buffer, 0, 503);
      buffer.getContext().eventDispatcher.endElement("physCellId", -1);

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("adType_r14Present");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("nrPhysCellId_r15Present");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode adType_r14

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("adType_r14", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            adType_r14 = new OTDOA_RequestAssistanceData_adType_r14();
            adType_r14.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("adType_r14", -1);

         }
         else {
            adType_r14 = null;
         }

         // decode nrPhysCellId_r15

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("nrPhysCellId_r15", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            nrPhysCellId_r15 = new Asn1Integer();
            nrPhysCellId_r15.decode (buffer, 0, 1007);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("nrPhysCellId_r15", -1);

         }
         else {
            nrPhysCellId_r15 = null;
         }

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

      boolean extbit = ((adType_r14 != null) ||
      (nrPhysCellId_r15 != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode physCellId

      if (physCellId != null) {
         buffer.getContext().eventDispatcher.startElement("physCellId", -1);

         physCellId.encode (buffer, 0, 503);

         buffer.getContext().eventDispatcher.endElement("physCellId", -1);
      }
      else throw new Asn1MissingRequiredException ("physCellId");

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 2;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((adType_r14 != null), null);
         buffer.encodeBit ((nrPhysCellId_r15 != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // adType_r14

         if (adType_r14 != null) {
            buffer.reset();
            adType_r14.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("adType_r14", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("adType_r14", -1);
         }

         // nrPhysCellId_r15

         if (nrPhysCellId_r15 != null) {
            buffer.reset();
            nrPhysCellId_r15.encode (buffer, 0, 1007);
            buffer.getContext().eventDispatcher.startElement("nrPhysCellId_r15", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("nrPhysCellId_r15", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
