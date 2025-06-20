/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Version_2_parameter_extensions;

import com.objsys.asn1j.runtime.*;

public class GanssNavigationModelData extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_Version_2_parameter_extensionsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_Version_2_parameter_extensionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "GanssNavigationModelData";
   }

   protected Asn1Integer ganssWeek;
   protected Asn1Integer ganssToe;
   protected Asn1Integer t_toeLimit;
   protected SatellitesListRelatedDataList satellitesListRelatedDataList;  // optional
   protected Asn1OpenExt extElem1;

   public GanssNavigationModelData () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public GanssNavigationModelData (
      Asn1Integer ganssWeek_,
      Asn1Integer ganssToe_,
      Asn1Integer t_toeLimit_,
      SatellitesListRelatedDataList satellitesListRelatedDataList_
   ) throws Asn1Exception {
      super();
      setGanssWeek (ganssWeek_);
      setGanssToe (ganssToe_);
      setT_toeLimit (t_toeLimit_);
      setSatellitesListRelatedDataList (satellitesListRelatedDataList_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public GanssNavigationModelData (
      Asn1Integer ganssWeek_,
      Asn1Integer ganssToe_,
      Asn1Integer t_toeLimit_
   ) throws Asn1Exception {
      super();
      setGanssWeek (ganssWeek_);
      setGanssToe (ganssToe_);
      setT_toeLimit (t_toeLimit_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public GanssNavigationModelData (long ganssWeek_,
      long ganssToe_,
      long t_toeLimit_,
      SatellitesListRelatedDataList satellitesListRelatedDataList_
   ) throws Asn1Exception {
      super();
      setGanssWeek (ganssWeek_);
      setGanssToe (ganssToe_);
      setT_toeLimit (t_toeLimit_);
      setSatellitesListRelatedDataList (satellitesListRelatedDataList_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public GanssNavigationModelData (
      long ganssWeek_,
      long ganssToe_,
      long t_toeLimit_
   ) throws Asn1Exception {
      super();
      setGanssWeek (ganssWeek_);
      setGanssToe (ganssToe_);
      setT_toeLimit (t_toeLimit_);
   }

   public void init () {
      ganssWeek = null;
      ganssToe = null;
      t_toeLimit = null;
      satellitesListRelatedDataList = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof GanssNavigationModelData) ) return false;

      GanssNavigationModelData rhs = (GanssNavigationModelData) obj;

      if (ganssWeek == null) {
         if (rhs.ganssWeek != null) return false;
      }
      else {
         if (!ganssWeek.equals(rhs.ganssWeek)) {
            return false;
         }
      }

      if (ganssToe == null) {
         if (rhs.ganssToe != null) return false;
      }
      else {
         if (!ganssToe.equals(rhs.ganssToe)) {
            return false;
         }
      }

      if (t_toeLimit == null) {
         if (rhs.t_toeLimit != null) return false;
      }
      else {
         if (!t_toeLimit.equals(rhs.t_toeLimit)) {
            return false;
         }
      }

      if (satellitesListRelatedDataList == null) {
         if (rhs.satellitesListRelatedDataList != null) return false;
      }
      else {
         if (!satellitesListRelatedDataList.equals(rhs.satellitesListRelatedDataList)) {
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

      if (ganssWeek != null) __code = 31*__code + ganssWeek.hashCode();
      if (ganssToe != null) __code = 31*__code + ganssToe.hashCode();
      if (t_toeLimit != null) __code = 31*__code + t_toeLimit.hashCode();
      if (satellitesListRelatedDataList != null) __code = 31*__code + satellitesListRelatedDataList.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for GanssWeek
    */
   public Asn1Integer getGanssWeek () {
      return ganssWeek;
   }

   public void setGanssWeek (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 4095))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ganssWeek = value;
   }

   public void setGanssWeek (long value) 
      throws Asn1Exception
   {
      setGanssWeek (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for GanssToe
    */
   public Asn1Integer getGanssToe () {
      return ganssToe;
   }

   public void setGanssToe (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 167))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ganssToe = value;
   }

   public void setGanssToe (long value) 
      throws Asn1Exception
   {
      setGanssToe (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for T_toeLimit
    */
   public Asn1Integer getT_toeLimit () {
      return t_toeLimit;
   }

   public void setT_toeLimit (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 15))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.t_toeLimit = value;
   }

   public void setT_toeLimit (long value) 
      throws Asn1Exception
   {
      setT_toeLimit (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for SatellitesListRelatedDataList
    */
   public SatellitesListRelatedDataList getSatellitesListRelatedDataList () {
      return satellitesListRelatedDataList;
   }

   public void setSatellitesListRelatedDataList (SatellitesListRelatedDataList value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 0 && value.getElements().length <= 32))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.satellitesListRelatedDataList = value;
   }

   public boolean hasSatellitesListRelatedDataList () {
      return (satellitesListRelatedDataList != null);
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
         case 0: return ganssWeek;
         case 1: return ganssToe;
         case 2: return t_toeLimit;
         case 3: return satellitesListRelatedDataList;
         case 4: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "ganssWeek";
         case 1: return "ganssToe";
         case 2: return "t-toeLimit";
         case 3: return "satellitesListRelatedDataList";
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

      boolean satellitesListRelatedDataListPresent = buffer.decodeBit ("satellitesListRelatedDataListPresent");

      // decode ganssWeek

      buffer.getContext().eventDispatcher.startElement("ganssWeek", -1);

      ganssWeek = new Asn1Integer();
      ganssWeek.decode (buffer, 0, 4095);
      buffer.getContext().eventDispatcher.endElement("ganssWeek", -1);

      // decode ganssToe

      buffer.getContext().eventDispatcher.startElement("ganssToe", -1);

      ganssToe = new Asn1Integer();
      ganssToe.decode (buffer, 0, 167);
      buffer.getContext().eventDispatcher.endElement("ganssToe", -1);

      // decode t_toeLimit

      buffer.getContext().eventDispatcher.startElement("t_toeLimit", -1);

      t_toeLimit = new Asn1Integer();
      t_toeLimit.decode (buffer, 0, 15);
      buffer.getContext().eventDispatcher.endElement("t_toeLimit", -1);

      // decode satellitesListRelatedDataList

      if (satellitesListRelatedDataListPresent) {
         buffer.getContext().eventDispatcher.startElement("satellitesListRelatedDataList", -1);

         satellitesListRelatedDataList = new SatellitesListRelatedDataList();
         satellitesListRelatedDataList.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("satellitesListRelatedDataList", -1);
      }
      else {
         satellitesListRelatedDataList = null;
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

      buffer.encodeBit ((satellitesListRelatedDataList != null), null);

      // encode ganssWeek

      if (ganssWeek != null) {
         buffer.getContext().eventDispatcher.startElement("ganssWeek", -1);

         ganssWeek.encode (buffer, 0, 4095);

         buffer.getContext().eventDispatcher.endElement("ganssWeek", -1);
      }
      else throw new Asn1MissingRequiredException ("ganssWeek");

      // encode ganssToe

      if (ganssToe != null) {
         buffer.getContext().eventDispatcher.startElement("ganssToe", -1);

         ganssToe.encode (buffer, 0, 167);

         buffer.getContext().eventDispatcher.endElement("ganssToe", -1);
      }
      else throw new Asn1MissingRequiredException ("ganssToe");

      // encode t_toeLimit

      if (t_toeLimit != null) {
         buffer.getContext().eventDispatcher.startElement("t_toeLimit", -1);

         t_toeLimit.encode (buffer, 0, 15);

         buffer.getContext().eventDispatcher.endElement("t_toeLimit", -1);
      }
      else throw new Asn1MissingRequiredException ("t_toeLimit");

      // encode satellitesListRelatedDataList

      if (satellitesListRelatedDataList != null) {
         buffer.getContext().eventDispatcher.startElement("satellitesListRelatedDataList", -1);

         satellitesListRelatedDataList.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("satellitesListRelatedDataList", -1);
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
