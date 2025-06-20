/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class TBS_ProvideAssistanceData_r14 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "TBS-ProvideAssistanceData-r14";
   }

   protected TBS_AssistanceDataList_r14 tbs_AssistanceDataList_r14;  // optional
   protected TBS_Error_r13 tbs_Error_r14;  // optional
   protected Asn1OpenExt extElem1;

   public TBS_ProvideAssistanceData_r14 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public TBS_ProvideAssistanceData_r14 (
      TBS_AssistanceDataList_r14 tbs_AssistanceDataList_r14_,
      TBS_Error_r13 tbs_Error_r14_
   ) {
      super();
      setTbs_AssistanceDataList_r14 (tbs_AssistanceDataList_r14_);
      setTbs_Error_r14 (tbs_Error_r14_);
   }

   public void init () {
      tbs_AssistanceDataList_r14 = null;
      tbs_Error_r14 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof TBS_ProvideAssistanceData_r14) ) return false;

      TBS_ProvideAssistanceData_r14 rhs = (TBS_ProvideAssistanceData_r14) obj;

      if (tbs_AssistanceDataList_r14 == null) {
         if (rhs.tbs_AssistanceDataList_r14 != null) return false;
      }
      else {
         if (!tbs_AssistanceDataList_r14.equals(rhs.tbs_AssistanceDataList_r14)) {
            return false;
         }
      }

      if (tbs_Error_r14 == null) {
         if (rhs.tbs_Error_r14 != null) return false;
      }
      else {
         if (!tbs_Error_r14.equals(rhs.tbs_Error_r14)) {
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

      if (tbs_AssistanceDataList_r14 != null) __code = 31*__code + tbs_AssistanceDataList_r14.hashCode();
      if (tbs_Error_r14 != null) __code = 31*__code + tbs_Error_r14.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Tbs_AssistanceDataList_r14
    */
   public TBS_AssistanceDataList_r14 getTbs_AssistanceDataList_r14 () {
      return tbs_AssistanceDataList_r14;
   }

   public void setTbs_AssistanceDataList_r14 (TBS_AssistanceDataList_r14 value) {
      this.tbs_AssistanceDataList_r14 = value;
   }

   public boolean hasTbs_AssistanceDataList_r14 () {
      return (tbs_AssistanceDataList_r14 != null);
   }

   /**
    * Accessor/mutator methods for Tbs_Error_r14
    */
   public TBS_Error_r13 getTbs_Error_r14 () {
      return tbs_Error_r14;
   }

   public void setTbs_Error_r14 (TBS_Error_r13 value) {
      this.tbs_Error_r14 = value;
   }

   public boolean hasTbs_Error_r14 () {
      return (tbs_Error_r14 != null);
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
         case 0: return tbs_AssistanceDataList_r14;
         case 1: return tbs_Error_r14;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "tbs-AssistanceDataList-r14";
         case 1: return "tbs-Error-r14";
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

      // optional bits

      boolean tbs_AssistanceDataList_r14Present = buffer.decodeBit ("tbs_AssistanceDataList_r14Present");
      boolean tbs_Error_r14Present = buffer.decodeBit ("tbs_Error_r14Present");

      // decode tbs_AssistanceDataList_r14

      if (tbs_AssistanceDataList_r14Present) {
         buffer.getContext().eventDispatcher.startElement("tbs_AssistanceDataList_r14", -1);

         tbs_AssistanceDataList_r14 = new TBS_AssistanceDataList_r14();
         tbs_AssistanceDataList_r14.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("tbs_AssistanceDataList_r14", -1);
      }
      else {
         tbs_AssistanceDataList_r14 = null;
      }

      // decode tbs_Error_r14

      if (tbs_Error_r14Present) {
         buffer.getContext().eventDispatcher.startElement("tbs_Error_r14", -1);

         tbs_Error_r14 = new TBS_Error_r13();
         tbs_Error_r14.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("tbs_Error_r14", -1);
      }
      else {
         tbs_Error_r14 = null;
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

      buffer.encodeBit ((tbs_AssistanceDataList_r14 != null), null);
      buffer.encodeBit ((tbs_Error_r14 != null), null);

      // encode tbs_AssistanceDataList_r14

      if (tbs_AssistanceDataList_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("tbs_AssistanceDataList_r14", -1);

         tbs_AssistanceDataList_r14.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("tbs_AssistanceDataList_r14", -1);
      }

      // encode tbs_Error_r14

      if (tbs_Error_r14 != null) {
         buffer.getContext().eventDispatcher.startElement("tbs_Error_r14", -1);

         tbs_Error_r14.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("tbs_Error_r14", -1);
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
