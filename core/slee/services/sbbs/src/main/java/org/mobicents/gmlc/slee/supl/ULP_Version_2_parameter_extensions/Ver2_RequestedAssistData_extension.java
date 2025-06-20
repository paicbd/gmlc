/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Version_2_parameter_extensions;

import com.objsys.asn1j.runtime.*;

public class Ver2_RequestedAssistData_extension extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_Version_2_parameter_extensionsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_Version_2_parameter_extensionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "Ver2-RequestedAssistData-extension";
   }

   protected GanssRequestedCommonAssistanceDataList ganssRequestedCommonAssistanceDataList;  // optional
   protected GanssRequestedGenericAssistanceDataList ganssRequestedGenericAssistanceDataList;  // optional
   protected ExtendedEphemeris extendedEphemeris;  // optional
   protected ExtendedEphCheck extendedEphemerisCheck;  // optional
   protected Asn1OpenExt extElem1;

   public Ver2_RequestedAssistData_extension () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public Ver2_RequestedAssistData_extension (
      GanssRequestedCommonAssistanceDataList ganssRequestedCommonAssistanceDataList_,
      GanssRequestedGenericAssistanceDataList ganssRequestedGenericAssistanceDataList_,
      ExtendedEphemeris extendedEphemeris_,
      ExtendedEphCheck extendedEphemerisCheck_
   ) throws Asn1Exception {
      super();
      setGanssRequestedCommonAssistanceDataList (ganssRequestedCommonAssistanceDataList_);
      setGanssRequestedGenericAssistanceDataList (ganssRequestedGenericAssistanceDataList_);
      setExtendedEphemeris (extendedEphemeris_);
      setExtendedEphemerisCheck (extendedEphemerisCheck_);
   }

   public void init () {
      ganssRequestedCommonAssistanceDataList = null;
      ganssRequestedGenericAssistanceDataList = null;
      extendedEphemeris = null;
      extendedEphemerisCheck = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof Ver2_RequestedAssistData_extension) ) return false;

      Ver2_RequestedAssistData_extension rhs = (Ver2_RequestedAssistData_extension) obj;

      if (ganssRequestedCommonAssistanceDataList == null) {
         if (rhs.ganssRequestedCommonAssistanceDataList != null) return false;
      }
      else {
         if (!ganssRequestedCommonAssistanceDataList.equals(rhs.ganssRequestedCommonAssistanceDataList)) {
            return false;
         }
      }

      if (ganssRequestedGenericAssistanceDataList == null) {
         if (rhs.ganssRequestedGenericAssistanceDataList != null) return false;
      }
      else {
         if (!ganssRequestedGenericAssistanceDataList.equals(rhs.ganssRequestedGenericAssistanceDataList)) {
            return false;
         }
      }

      if (extendedEphemeris == null) {
         if (rhs.extendedEphemeris != null) return false;
      }
      else {
         if (!extendedEphemeris.equals(rhs.extendedEphemeris)) {
            return false;
         }
      }

      if (extendedEphemerisCheck == null) {
         if (rhs.extendedEphemerisCheck != null) return false;
      }
      else {
         if (!extendedEphemerisCheck.equals(rhs.extendedEphemerisCheck)) {
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

      if (ganssRequestedCommonAssistanceDataList != null) __code = 31*__code + ganssRequestedCommonAssistanceDataList.hashCode();
      if (ganssRequestedGenericAssistanceDataList != null) __code = 31*__code + ganssRequestedGenericAssistanceDataList.hashCode();
      if (extendedEphemeris != null) __code = 31*__code + extendedEphemeris.hashCode();
      if (extendedEphemerisCheck != null) __code = 31*__code + extendedEphemerisCheck.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for GanssRequestedCommonAssistanceDataList
    */
   public GanssRequestedCommonAssistanceDataList getGanssRequestedCommonAssistanceDataList () {
      return ganssRequestedCommonAssistanceDataList;
   }

   public void setGanssRequestedCommonAssistanceDataList (GanssRequestedCommonAssistanceDataList value) {
      this.ganssRequestedCommonAssistanceDataList = value;
   }

   public boolean hasGanssRequestedCommonAssistanceDataList () {
      return (ganssRequestedCommonAssistanceDataList != null);
   }

   /**
    * Accessor/mutator methods for GanssRequestedGenericAssistanceDataList
    */
   public GanssRequestedGenericAssistanceDataList getGanssRequestedGenericAssistanceDataList () {
      return ganssRequestedGenericAssistanceDataList;
   }

   public void setGanssRequestedGenericAssistanceDataList (GanssRequestedGenericAssistanceDataList value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 1 && value.getElements().length <= 16))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.ganssRequestedGenericAssistanceDataList = value;
   }

   public boolean hasGanssRequestedGenericAssistanceDataList () {
      return (ganssRequestedGenericAssistanceDataList != null);
   }

   /**
    * Accessor/mutator methods for ExtendedEphemeris
    */
   public ExtendedEphemeris getExtendedEphemeris () {
      return extendedEphemeris;
   }

   public void setExtendedEphemeris (ExtendedEphemeris value) {
      this.extendedEphemeris = value;
   }

   public boolean hasExtendedEphemeris () {
      return (extendedEphemeris != null);
   }

   /**
    * Accessor/mutator methods for ExtendedEphemerisCheck
    */
   public ExtendedEphCheck getExtendedEphemerisCheck () {
      return extendedEphemerisCheck;
   }

   public void setExtendedEphemerisCheck (ExtendedEphCheck value) {
      this.extendedEphemerisCheck = value;
   }

   public boolean hasExtendedEphemerisCheck () {
      return (extendedEphemerisCheck != null);
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
         case 0: return ganssRequestedCommonAssistanceDataList;
         case 1: return ganssRequestedGenericAssistanceDataList;
         case 2: return extendedEphemeris;
         case 3: return extendedEphemerisCheck;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "ganssRequestedCommonAssistanceDataList";
         case 1: return "ganssRequestedGenericAssistanceDataList";
         case 2: return "extendedEphemeris";
         case 3: return "extendedEphemerisCheck";
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

      boolean ganssRequestedCommonAssistanceDataListPresent = buffer.decodeBit ("ganssRequestedCommonAssistanceDataListPresent");
      boolean ganssRequestedGenericAssistanceDataListPresent = buffer.decodeBit ("ganssRequestedGenericAssistanceDataListPresent");
      boolean extendedEphemerisPresent = buffer.decodeBit ("extendedEphemerisPresent");
      boolean extendedEphemerisCheckPresent = buffer.decodeBit ("extendedEphemerisCheckPresent");

      // decode ganssRequestedCommonAssistanceDataList

      if (ganssRequestedCommonAssistanceDataListPresent) {
         buffer.getContext().eventDispatcher.startElement("ganssRequestedCommonAssistanceDataList", -1);

         ganssRequestedCommonAssistanceDataList = new GanssRequestedCommonAssistanceDataList();
         ganssRequestedCommonAssistanceDataList.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("ganssRequestedCommonAssistanceDataList", -1);
      }
      else {
         ganssRequestedCommonAssistanceDataList = null;
      }

      // decode ganssRequestedGenericAssistanceDataList

      if (ganssRequestedGenericAssistanceDataListPresent) {
         buffer.getContext().eventDispatcher.startElement("ganssRequestedGenericAssistanceDataList", -1);

         ganssRequestedGenericAssistanceDataList = new GanssRequestedGenericAssistanceDataList();
         ganssRequestedGenericAssistanceDataList.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("ganssRequestedGenericAssistanceDataList", -1);
      }
      else {
         ganssRequestedGenericAssistanceDataList = null;
      }

      // decode extendedEphemeris

      if (extendedEphemerisPresent) {
         buffer.getContext().eventDispatcher.startElement("extendedEphemeris", -1);

         extendedEphemeris = new ExtendedEphemeris();
         extendedEphemeris.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("extendedEphemeris", -1);
      }
      else {
         extendedEphemeris = null;
      }

      // decode extendedEphemerisCheck

      if (extendedEphemerisCheckPresent) {
         buffer.getContext().eventDispatcher.startElement("extendedEphemerisCheck", -1);

         extendedEphemerisCheck = new ExtendedEphCheck();
         extendedEphemerisCheck.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("extendedEphemerisCheck", -1);
      }
      else {
         extendedEphemerisCheck = null;
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

      buffer.encodeBit ((ganssRequestedCommonAssistanceDataList != null), null);
      buffer.encodeBit ((ganssRequestedGenericAssistanceDataList != null), null);
      buffer.encodeBit ((extendedEphemeris != null), null);
      buffer.encodeBit ((extendedEphemerisCheck != null), null);

      // encode ganssRequestedCommonAssistanceDataList

      if (ganssRequestedCommonAssistanceDataList != null) {
         buffer.getContext().eventDispatcher.startElement("ganssRequestedCommonAssistanceDataList", -1);

         ganssRequestedCommonAssistanceDataList.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("ganssRequestedCommonAssistanceDataList", -1);
      }

      // encode ganssRequestedGenericAssistanceDataList

      if (ganssRequestedGenericAssistanceDataList != null) {
         buffer.getContext().eventDispatcher.startElement("ganssRequestedGenericAssistanceDataList", -1);

         ganssRequestedGenericAssistanceDataList.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("ganssRequestedGenericAssistanceDataList", -1);
      }

      // encode extendedEphemeris

      if (extendedEphemeris != null) {
         buffer.getContext().eventDispatcher.startElement("extendedEphemeris", -1);

         extendedEphemeris.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("extendedEphemeris", -1);
      }

      // encode extendedEphemerisCheck

      if (extendedEphemerisCheck != null) {
         buffer.getContext().eventDispatcher.startElement("extendedEphemerisCheck", -1);

         extendedEphemerisCheck.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("extendedEphemerisCheck", -1);
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
