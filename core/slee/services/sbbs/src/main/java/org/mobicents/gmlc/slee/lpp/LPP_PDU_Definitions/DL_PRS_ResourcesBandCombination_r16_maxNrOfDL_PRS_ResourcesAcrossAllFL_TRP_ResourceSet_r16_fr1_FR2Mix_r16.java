/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SEQUENCE";
   }

   protected DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16 fr1_r16 = null;
   protected DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16 fr2_r16 = null;
   protected Asn1OpenExt extElem1;

   public DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16 (
      DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16 fr1_r16_,
      DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16 fr2_r16_
   ) {
      super();
      setFr1_r16 (fr1_r16_);
      setFr2_r16 (fr2_r16_);
   }

   public void init () {
      fr1_r16 = null;
      fr2_r16 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16) ) return false;

      DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16 rhs = (DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16) obj;

      if (fr1_r16 == null) {
         if (rhs.fr1_r16 != null) return false;
      }
      else {
         if (!fr1_r16.equals(rhs.fr1_r16)) {
            return false;
         }
      }

      if (fr2_r16 == null) {
         if (rhs.fr2_r16 != null) return false;
      }
      else {
         if (!fr2_r16.equals(rhs.fr2_r16)) {
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

      if (fr1_r16 != null) __code = 31*__code + fr1_r16.hashCode();
      if (fr2_r16 != null) __code = 31*__code + fr2_r16.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Fr1_r16
    */
   public DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16 getFr1_r16 () {
      return fr1_r16;
   }

   public void setFr1_r16 (DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16 value) {
      this.fr1_r16 = value;
   }

   /**
    * Accessor/mutator methods for Fr2_r16
    */
   public DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16 getFr2_r16 () {
      return fr2_r16;
   }

   public void setFr2_r16 (DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16 value) {
      this.fr2_r16 = value;
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
         case 0: return fr1_r16;
         case 1: return fr2_r16;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "fr1-r16";
         case 1: return "fr2-r16";
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

      // decode fr1_r16

      buffer.getContext().eventDispatcher.startElement("fr1_r16", -1);

      {
         int tval = DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16.decodeEnumValue (buffer);
         fr1_r16 = DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr1_r16.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("fr1_r16", -1);

      // decode fr2_r16

      buffer.getContext().eventDispatcher.startElement("fr2_r16", -1);

      {
         int tval = DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16.decodeEnumValue (buffer);
         fr2_r16 = DL_PRS_ResourcesBandCombination_r16_maxNrOfDL_PRS_ResourcesAcrossAllFL_TRP_ResourceSet_r16_fr1_FR2Mix_r16_fr2_r16.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("fr2_r16", -1);

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

      // encode fr1_r16

      if (fr1_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("fr1_r16", -1);

         fr1_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("fr1_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("fr1_r16");

      // encode fr2_r16

      if (fr2_r16 != null) {
         buffer.getContext().eventDispatcher.startElement("fr2_r16", -1);

         fr2_r16.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("fr2_r16", -1);
      }
      else throw new Asn1MissingRequiredException ("fr2_r16");

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
