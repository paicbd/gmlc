/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class ServCellNR extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ServCellNR";
   }

   protected PhysCellIdNR physCellId;
   protected ARFCN_NR arfcn_NR;
   protected CellGlobalIdNR cellGlobalId;
   protected TrackingAreaCodeNR trackingAreaCode;
   protected NR_Measurements ssb_Measurements;  // optional
   protected NR_Measurements csi_rs_Measurements;  // optional
   protected Asn1Integer ta;  // optional
   protected ServCellNR_arfcn_type arfcn_type = null;  // optional
   protected Asn1BitString systemFrameNumber;  // optional
   protected SSB_IndexList_Measurements ssb_IndexList_Measurements;  // optional
   protected CSI_RS_IndexList_Measurements csi_rs_IndexList_Measurements;  // optional
   protected Asn1OpenExt extElem1;

   public ServCellNR () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public ServCellNR (
      PhysCellIdNR physCellId_,
      ARFCN_NR arfcn_NR_,
      CellGlobalIdNR cellGlobalId_,
      TrackingAreaCodeNR trackingAreaCode_,
      NR_Measurements ssb_Measurements_,
      NR_Measurements csi_rs_Measurements_,
      Asn1Integer ta_,
      ServCellNR_arfcn_type arfcn_type_,
      Asn1BitString systemFrameNumber_,
      SSB_IndexList_Measurements ssb_IndexList_Measurements_,
      CSI_RS_IndexList_Measurements csi_rs_IndexList_Measurements_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setArfcn_NR (arfcn_NR_);
      setCellGlobalId (cellGlobalId_);
      setTrackingAreaCode (trackingAreaCode_);
      setSsb_Measurements (ssb_Measurements_);
      setCsi_rs_Measurements (csi_rs_Measurements_);
      setTa (ta_);
      setArfcn_type (arfcn_type_);
      setSystemFrameNumber (systemFrameNumber_);
      setSsb_IndexList_Measurements (ssb_IndexList_Measurements_);
      setCsi_rs_IndexList_Measurements (csi_rs_IndexList_Measurements_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public ServCellNR (
      PhysCellIdNR physCellId_,
      ARFCN_NR arfcn_NR_,
      CellGlobalIdNR cellGlobalId_,
      TrackingAreaCodeNR trackingAreaCode_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setArfcn_NR (arfcn_NR_);
      setCellGlobalId (cellGlobalId_);
      setTrackingAreaCode (trackingAreaCode_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public ServCellNR (long physCellId_,
      long arfcn_NR_,
      CellGlobalIdNR cellGlobalId_,
      TrackingAreaCodeNR trackingAreaCode_,
      NR_Measurements ssb_Measurements_,
      NR_Measurements csi_rs_Measurements_,
      long ta_,
      ServCellNR_arfcn_type arfcn_type_,
      Asn1BitString systemFrameNumber_,
      SSB_IndexList_Measurements ssb_IndexList_Measurements_,
      CSI_RS_IndexList_Measurements csi_rs_IndexList_Measurements_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setArfcn_NR (arfcn_NR_);
      setCellGlobalId (cellGlobalId_);
      setTrackingAreaCode (trackingAreaCode_);
      setSsb_Measurements (ssb_Measurements_);
      setCsi_rs_Measurements (csi_rs_Measurements_);
      setTa (ta_);
      setArfcn_type (arfcn_type_);
      setSystemFrameNumber (systemFrameNumber_);
      setSsb_IndexList_Measurements (ssb_IndexList_Measurements_);
      setCsi_rs_IndexList_Measurements (csi_rs_IndexList_Measurements_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public ServCellNR (
      long physCellId_,
      long arfcn_NR_,
      CellGlobalIdNR cellGlobalId_,
      TrackingAreaCodeNR trackingAreaCode_
   ) throws Asn1Exception {
      super();
      setPhysCellId (physCellId_);
      setArfcn_NR (arfcn_NR_);
      setCellGlobalId (cellGlobalId_);
      setTrackingAreaCode (trackingAreaCode_);
   }

   public void init () {
      physCellId = null;
      arfcn_NR = null;
      cellGlobalId = null;
      trackingAreaCode = null;
      ssb_Measurements = null;
      csi_rs_Measurements = null;
      ta = null;
      arfcn_type = null;
      systemFrameNumber = null;
      ssb_IndexList_Measurements = null;
      csi_rs_IndexList_Measurements = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof ServCellNR) ) return false;

      ServCellNR rhs = (ServCellNR) obj;

      if (physCellId == null) {
         if (rhs.physCellId != null) return false;
      }
      else {
         if (!physCellId.equals(rhs.physCellId)) {
            return false;
         }
      }

      if (arfcn_NR == null) {
         if (rhs.arfcn_NR != null) return false;
      }
      else {
         if (!arfcn_NR.equals(rhs.arfcn_NR)) {
            return false;
         }
      }

      if (cellGlobalId == null) {
         if (rhs.cellGlobalId != null) return false;
      }
      else {
         if (!cellGlobalId.equals(rhs.cellGlobalId)) {
            return false;
         }
      }

      if (trackingAreaCode == null) {
         if (rhs.trackingAreaCode != null) return false;
      }
      else {
         if (!trackingAreaCode.equals(rhs.trackingAreaCode)) {
            return false;
         }
      }

      if (ssb_Measurements == null) {
         if (rhs.ssb_Measurements != null) return false;
      }
      else {
         if (!ssb_Measurements.equals(rhs.ssb_Measurements)) {
            return false;
         }
      }

      if (csi_rs_Measurements == null) {
         if (rhs.csi_rs_Measurements != null) return false;
      }
      else {
         if (!csi_rs_Measurements.equals(rhs.csi_rs_Measurements)) {
            return false;
         }
      }

      if (ta == null) {
         if (rhs.ta != null) return false;
      }
      else {
         if (!ta.equals(rhs.ta)) {
            return false;
         }
      }

      if (arfcn_type == null) {
         if (rhs.arfcn_type != null) return false;
      }
      else {
         if (!arfcn_type.equals(rhs.arfcn_type)) {
            return false;
         }
      }

      if (systemFrameNumber == null) {
         if (rhs.systemFrameNumber != null) return false;
      }
      else {
         if (!systemFrameNumber.equals(rhs.systemFrameNumber)) {
            return false;
         }
      }

      if (ssb_IndexList_Measurements == null) {
         if (rhs.ssb_IndexList_Measurements != null) return false;
      }
      else {
         if (!ssb_IndexList_Measurements.equals(rhs.ssb_IndexList_Measurements)) {
            return false;
         }
      }

      if (csi_rs_IndexList_Measurements == null) {
         if (rhs.csi_rs_IndexList_Measurements != null) return false;
      }
      else {
         if (!csi_rs_IndexList_Measurements.equals(rhs.csi_rs_IndexList_Measurements)) {
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
      if (arfcn_NR != null) __code = 31*__code + arfcn_NR.hashCode();
      if (cellGlobalId != null) __code = 31*__code + cellGlobalId.hashCode();
      if (trackingAreaCode != null) __code = 31*__code + trackingAreaCode.hashCode();
      if (ssb_Measurements != null) __code = 31*__code + ssb_Measurements.hashCode();
      if (csi_rs_Measurements != null) __code = 31*__code + csi_rs_Measurements.hashCode();
      if (ta != null) __code = 31*__code + ta.hashCode();
      if (arfcn_type != null) __code = 31*__code + arfcn_type.hashCode();
      if (systemFrameNumber != null) __code = 31*__code + systemFrameNumber.hashCode();
      if (ssb_IndexList_Measurements != null) __code = 31*__code + ssb_IndexList_Measurements.hashCode();
      if (csi_rs_IndexList_Measurements != null) __code = 31*__code + csi_rs_IndexList_Measurements.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for PhysCellId
    */
   public PhysCellIdNR getPhysCellId () {
      return physCellId;
   }

   public void setPhysCellId (PhysCellIdNR value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 1007))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.physCellId = value;
   }

   public void setPhysCellId (long value) 
      throws Asn1Exception
   {
      setPhysCellId (new PhysCellIdNR(value));
   }

   /**
    * Accessor/mutator methods for Arfcn_NR
    */
   public ARFCN_NR getArfcn_NR () {
      return arfcn_NR;
   }

   public void setArfcn_NR (ARFCN_NR value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 3279165))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.arfcn_NR = value;
   }

   public void setArfcn_NR (long value) 
      throws Asn1Exception
   {
      setArfcn_NR (new ARFCN_NR(value));
   }

   /**
    * Accessor/mutator methods for CellGlobalId
    */
   public CellGlobalIdNR getCellGlobalId () {
      return cellGlobalId;
   }

   public void setCellGlobalId (CellGlobalIdNR value) {
      this.cellGlobalId = value;
   }

   /**
    * Accessor/mutator methods for TrackingAreaCode
    */
   public TrackingAreaCodeNR getTrackingAreaCode () {
      return trackingAreaCode;
   }

   public void setTrackingAreaCode (TrackingAreaCodeNR value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 24)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.trackingAreaCode = value;
   }

   /**
    * Accessor/mutator methods for Ssb_Measurements
    */
   public NR_Measurements getSsb_Measurements () {
      return ssb_Measurements;
   }

   public void setSsb_Measurements (NR_Measurements value) {
      this.ssb_Measurements = value;
   }

   public boolean hasSsb_Measurements () {
      return (ssb_Measurements != null);
   }

   /**
    * Accessor/mutator methods for Csi_rs_Measurements
    */
   public NR_Measurements getCsi_rs_Measurements () {
      return csi_rs_Measurements;
   }

   public void setCsi_rs_Measurements (NR_Measurements value) {
      this.csi_rs_Measurements = value;
   }

   public boolean hasCsi_rs_Measurements () {
      return (csi_rs_Measurements != null);
   }

   /**
    * Accessor/mutator methods for Ta
    */
   public Asn1Integer getTa () {
      return ta;
   }

   public void setTa (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 3846))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.ta = value;
   }

   public void setTa (long value) 
      throws Asn1Exception
   {
      setTa (new Asn1Integer(value));
   }
   public boolean hasTa () {
      return (ta != null);
   }

   /**
    * Accessor/mutator methods for Arfcn_type
    */
   public ServCellNR_arfcn_type getArfcn_type () {
      return arfcn_type;
   }

   public void setArfcn_type (ServCellNR_arfcn_type value) {
      this.arfcn_type = value;
   }

   public boolean hasArfcn_type () {
      return (arfcn_type != null);
   }

   /**
    * Accessor/mutator methods for SystemFrameNumber
    */
   public Asn1BitString getSystemFrameNumber () {
      return systemFrameNumber;
   }

   public void setSystemFrameNumber (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 10)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.systemFrameNumber = value;
   }

   public boolean hasSystemFrameNumber () {
      return (systemFrameNumber != null);
   }

   /**
    * Accessor/mutator methods for Ssb_IndexList_Measurements
    */
   public SSB_IndexList_Measurements getSsb_IndexList_Measurements () {
      return ssb_IndexList_Measurements;
   }

   public void setSsb_IndexList_Measurements (SSB_IndexList_Measurements value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 1 && value.getElements().length <= 64))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.ssb_IndexList_Measurements = value;
   }

   public boolean hasSsb_IndexList_Measurements () {
      return (ssb_IndexList_Measurements != null);
   }

   /**
    * Accessor/mutator methods for Csi_rs_IndexList_Measurements
    */
   public CSI_RS_IndexList_Measurements getCsi_rs_IndexList_Measurements () {
      return csi_rs_IndexList_Measurements;
   }

   public void setCsi_rs_IndexList_Measurements (CSI_RS_IndexList_Measurements value) 
      throws Asn1Exception
   {
      if (!((value.getElements().length >= 1 && value.getElements().length <= 64))) {
         throw new Asn1ConsVioException ("value.getElements().length", value.getElements().length);
      }

      this.csi_rs_IndexList_Measurements = value;
   }

   public boolean hasCsi_rs_IndexList_Measurements () {
      return (csi_rs_IndexList_Measurements != null);
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

   public int getElementCount() { return 12; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return physCellId;
         case 1: return arfcn_NR;
         case 2: return cellGlobalId;
         case 3: return trackingAreaCode;
         case 4: return ssb_Measurements;
         case 5: return csi_rs_Measurements;
         case 6: return ta;
         case 7: return arfcn_type;
         case 8: return systemFrameNumber;
         case 9: return ssb_IndexList_Measurements;
         case 10: return csi_rs_IndexList_Measurements;
         case 11: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "physCellId";
         case 1: return "arfcn-NR";
         case 2: return "cellGlobalId";
         case 3: return "trackingAreaCode";
         case 4: return "ssb-Measurements";
         case 5: return "csi-rs-Measurements";
         case 6: return "ta";
         case 7: return "arfcn-type";
         case 8: return "systemFrameNumber";
         case 9: return "ssb-IndexList-Measurements";
         case 10: return "csi-rs-IndexList-Measurements";
         case 11: return null;
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

      boolean ssb_MeasurementsPresent = buffer.decodeBit ("ssb_MeasurementsPresent");
      boolean csi_rs_MeasurementsPresent = buffer.decodeBit ("csi_rs_MeasurementsPresent");
      boolean taPresent = buffer.decodeBit ("taPresent");

      // decode physCellId

      buffer.getContext().eventDispatcher.startElement("physCellId", -1);

      physCellId = new PhysCellIdNR();
      physCellId.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("physCellId", -1);

      // decode arfcn_NR

      buffer.getContext().eventDispatcher.startElement("arfcn_NR", -1);

      arfcn_NR = new ARFCN_NR();
      arfcn_NR.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("arfcn_NR", -1);

      // decode cellGlobalId

      buffer.getContext().eventDispatcher.startElement("cellGlobalId", -1);

      cellGlobalId = new CellGlobalIdNR();
      cellGlobalId.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("cellGlobalId", -1);

      // decode trackingAreaCode

      buffer.getContext().eventDispatcher.startElement("trackingAreaCode", -1);

      trackingAreaCode = new TrackingAreaCodeNR();
      trackingAreaCode.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("trackingAreaCode", -1);

      // decode ssb_Measurements

      if (ssb_MeasurementsPresent) {
         buffer.getContext().eventDispatcher.startElement("ssb_Measurements", -1);

         ssb_Measurements = new NR_Measurements();
         ssb_Measurements.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("ssb_Measurements", -1);
      }
      else {
         ssb_Measurements = null;
      }

      // decode csi_rs_Measurements

      if (csi_rs_MeasurementsPresent) {
         buffer.getContext().eventDispatcher.startElement("csi_rs_Measurements", -1);

         csi_rs_Measurements = new NR_Measurements();
         csi_rs_Measurements.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("csi_rs_Measurements", -1);
      }
      else {
         csi_rs_Measurements = null;
      }

      // decode ta

      if (taPresent) {
         buffer.getContext().eventDispatcher.startElement("ta", -1);

         ta = new Asn1Integer();
         ta.decode (buffer, 0, 3846);
         buffer.getContext().eventDispatcher.endElement("ta", -1);
      }
      else {
         ta = null;
      }

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("arfcn_typePresent");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("systemFrameNumberPresent");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("ssb_IndexList_MeasurementsPresent");
         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("csi_rs_IndexList_MeasurementsPresent");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode arfcn_type

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("arfcn_type", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            {
               int tval = ServCellNR_arfcn_type.decodeEnumValue (buffer);
               arfcn_type = ServCellNR_arfcn_type.valueOf (tval);
               buffer.moveBitCursor (bitidx);
            }
            buffer.getContext().eventDispatcher.endElement("arfcn_type", -1);

         }
         else {
            arfcn_type = null;
         }

         // decode systemFrameNumber

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("systemFrameNumber", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            systemFrameNumber = new Asn1BitString();
            systemFrameNumber.decode (buffer, 10, 10);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("systemFrameNumber", -1);

         }
         else {
            systemFrameNumber = null;
         }

         // decode ssb_IndexList_Measurements

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("ssb_IndexList_Measurements", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            ssb_IndexList_Measurements = new SSB_IndexList_Measurements();
            ssb_IndexList_Measurements.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("ssb_IndexList_Measurements", -1);

         }
         else {
            ssb_IndexList_Measurements = null;
         }

         // decode csi_rs_IndexList_Measurements

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("csi_rs_IndexList_Measurements", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            csi_rs_IndexList_Measurements = new CSI_RS_IndexList_Measurements();
            csi_rs_IndexList_Measurements.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("csi_rs_IndexList_Measurements", -1);

         }
         else {
            csi_rs_IndexList_Measurements = null;
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

      boolean extbit = ((arfcn_type != null) ||
      (systemFrameNumber != null) ||
      (ssb_IndexList_Measurements != null) ||
      (csi_rs_IndexList_Measurements != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode optional elements bit mask

      buffer.encodeBit ((ssb_Measurements != null), null);
      buffer.encodeBit ((csi_rs_Measurements != null), null);
      buffer.encodeBit ((ta != null), null);

      // encode physCellId

      if (physCellId != null) {
         buffer.getContext().eventDispatcher.startElement("physCellId", -1);

         physCellId.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("physCellId", -1);
      }
      else throw new Asn1MissingRequiredException ("physCellId");

      // encode arfcn_NR

      if (arfcn_NR != null) {
         buffer.getContext().eventDispatcher.startElement("arfcn_NR", -1);

         arfcn_NR.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("arfcn_NR", -1);
      }
      else throw new Asn1MissingRequiredException ("arfcn_NR");

      // encode cellGlobalId

      if (cellGlobalId != null) {
         buffer.getContext().eventDispatcher.startElement("cellGlobalId", -1);

         cellGlobalId.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("cellGlobalId", -1);
      }
      else throw new Asn1MissingRequiredException ("cellGlobalId");

      // encode trackingAreaCode

      if (trackingAreaCode != null) {
         buffer.getContext().eventDispatcher.startElement("trackingAreaCode", -1);

         trackingAreaCode.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("trackingAreaCode", -1);
      }
      else throw new Asn1MissingRequiredException ("trackingAreaCode");

      // encode ssb_Measurements

      if (ssb_Measurements != null) {
         buffer.getContext().eventDispatcher.startElement("ssb_Measurements", -1);

         ssb_Measurements.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("ssb_Measurements", -1);
      }

      // encode csi_rs_Measurements

      if (csi_rs_Measurements != null) {
         buffer.getContext().eventDispatcher.startElement("csi_rs_Measurements", -1);

         csi_rs_Measurements.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("csi_rs_Measurements", -1);
      }

      // encode ta

      if (ta != null) {
         buffer.getContext().eventDispatcher.startElement("ta", -1);

         ta.encode (buffer, 0, 3846);

         buffer.getContext().eventDispatcher.endElement("ta", -1);
      }

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 4;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((arfcn_type != null), null);
         buffer.encodeBit ((systemFrameNumber != null), null);
         buffer.encodeBit ((ssb_IndexList_Measurements != null), null);
         buffer.encodeBit ((csi_rs_IndexList_Measurements != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // arfcn_type

         if (arfcn_type != null) {
            buffer.reset();
            arfcn_type.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("arfcn_type", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("arfcn_type", -1);
         }

         // systemFrameNumber

         if (systemFrameNumber != null) {
            buffer.reset();
            systemFrameNumber.encode (buffer, 10, 10);
            buffer.getContext().eventDispatcher.startElement("systemFrameNumber", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("systemFrameNumber", -1);
         }

         // ssb_IndexList_Measurements

         if (ssb_IndexList_Measurements != null) {
            buffer.reset();
            ssb_IndexList_Measurements.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("ssb_IndexList_Measurements", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("ssb_IndexList_Measurements", -1);
         }

         // csi_rs_IndexList_Measurements

         if (csi_rs_IndexList_Measurements != null) {
            buffer.reset();
            csi_rs_IndexList_Measurements.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("csi_rs_IndexList_Measurements", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("csi_rs_IndexList_Measurements", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
