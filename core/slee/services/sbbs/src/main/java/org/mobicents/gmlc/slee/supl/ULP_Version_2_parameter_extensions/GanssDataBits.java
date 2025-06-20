/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Version_2_parameter_extensions;

import com.objsys.asn1j.runtime.*;

public class GanssDataBits extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_Version_2_parameter_extensionsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_Version_2_parameter_extensionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GanssDataBits";
   }

   protected Asn1Integer ganssTODmin;
   protected ReqDataBitAssistanceList reqDataBitAssistanceList;
   protected Asn1OpenExt extElem1;

   public GanssDataBits () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GanssDataBits (
      Asn1Integer ganssTODmin_,
      ReqDataBitAssistanceList reqDataBitAssistanceList_
   ) throws Asn1Exception {
      super();
      setGanssTODmin (ganssTODmin_);
      setReqDataBitAssistanceList (reqDataBitAssistanceList_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GanssDataBits (long ganssTODmin_,
      ReqDataBitAssistanceList reqDataBitAssistanceList_
   ) throws Asn1Exception {
      super();
      setGanssTODmin (ganssTODmin_);
      setReqDataBitAssistanceList (reqDataBitAssistanceList_);
   }

   public void init () {
      ganssTODmin = null;
      reqDataBitAssistanceList = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GanssDataBits) ) return false;

      GanssDataBits rhs = (GanssDataBits) obj;

      if (ganssTODmin == null) {
         if (rhs.ganssTODmin != null) return false;
      }
      else {
         if (!ganssTODmin.equals(rhs.ganssTODmin)) {
            return false;
         }
      }

      if (reqDataBitAssistanceList == null) {
         if (rhs.reqDataBitAssistanceList != null) return false;
      }
      else {
         if (!reqDataBitAssistanceList.equals(rhs.reqDataBitAssistanceList)) {
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

      if (ganssTODmin != null) __code = 31*__code + ganssTODmin.hashCode();
      if (reqDataBitAssistanceList != null) __code = 31*__code + reqDataBitAssistanceList.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for GanssTODmin
    */
   public Asn1Integer getGanssTODmin () {
      return ganssTODmin;
   }

   public void setGanssTODmin (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 59))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ganssTODmin = value;
   }

   public void setGanssTODmin (long value) 
      throws Asn1Exception
   {
      setGanssTODmin (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for ReqDataBitAssistanceList
    */
   public ReqDataBitAssistanceList getReqDataBitAssistanceList () {
      return reqDataBitAssistanceList;
   }

   public void setReqDataBitAssistanceList (ReqDataBitAssistanceList value) {
      this.reqDataBitAssistanceList = value;
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
         case 0: return ganssTODmin;
         case 1: return reqDataBitAssistanceList;
         case 2: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "ganssTODmin";
         case 1: return "reqDataBitAssistanceList";
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

      // decode ganssTODmin

      buffer.getContext().eventDispatcher.startElement("ganssTODmin", -1);

      ganssTODmin = new Asn1Integer();
      ganssTODmin.decode (buffer, 0, 59);
      buffer.getContext().eventDispatcher.endElement("ganssTODmin", -1);

      // decode reqDataBitAssistanceList

      buffer.getContext().eventDispatcher.startElement("reqDataBitAssistanceList", -1);

      reqDataBitAssistanceList = new ReqDataBitAssistanceList();
      reqDataBitAssistanceList.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("reqDataBitAssistanceList", -1);

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

      // encode ganssTODmin

      if (ganssTODmin != null) {
         buffer.getContext().eventDispatcher.startElement("ganssTODmin", -1);

         ganssTODmin.encode (buffer, 0, 59);

         buffer.getContext().eventDispatcher.endElement("ganssTODmin", -1);
      }
      else throw new Asn1MissingRequiredException ("ganssTODmin");

      // encode reqDataBitAssistanceList

      if (reqDataBitAssistanceList != null) {
         buffer.getContext().eventDispatcher.startElement("reqDataBitAssistanceList", -1);

         reqDataBitAssistanceList.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("reqDataBitAssistanceList", -1);
      }
      else throw new Asn1MissingRequiredException ("reqDataBitAssistanceList");

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
