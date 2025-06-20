/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class AUX_ReferenceStationID_Element_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "AUX-ReferenceStationID-Element-r15";
   }

   protected GNSS_ReferenceStationID_r15 aux_stationID_r15;
   protected Asn1OpenExt extElem1;

   public AUX_ReferenceStationID_Element_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public AUX_ReferenceStationID_Element_r15 (
      GNSS_ReferenceStationID_r15 aux_stationID_r15_
   ) {
      super();
      setAux_stationID_r15 (aux_stationID_r15_);
   }

   public void init () {
      aux_stationID_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof AUX_ReferenceStationID_Element_r15) ) return false;

      AUX_ReferenceStationID_Element_r15 rhs = (AUX_ReferenceStationID_Element_r15) obj;

      if (aux_stationID_r15 == null) {
         if (rhs.aux_stationID_r15 != null) return false;
      }
      else {
         if (!aux_stationID_r15.equals(rhs.aux_stationID_r15)) {
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

      if (aux_stationID_r15 != null) __code = 31*__code + aux_stationID_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Aux_stationID_r15
    */
   public GNSS_ReferenceStationID_r15 getAux_stationID_r15 () {
      return aux_stationID_r15;
   }

   public void setAux_stationID_r15 (GNSS_ReferenceStationID_r15 value) {
      this.aux_stationID_r15 = value;
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

   public int getElementCount() { return 2; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return aux_stationID_r15;
         case 1: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "aux-stationID-r15";
         case 1: return null;
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // extension bit

      boolean extbit = buffer.decodeBit ("extbit");

      // decode aux_stationID_r15

      buffer.getContext().eventDispatcher.startElement("aux_stationID_r15", -1);

      aux_stationID_r15 = new GNSS_ReferenceStationID_r15();
      aux_stationID_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("aux_stationID_r15", -1);

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

      // encode aux_stationID_r15

      if (aux_stationID_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("aux_stationID_r15", -1);

         aux_stationID_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("aux_stationID_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("aux_stationID_r15");

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
