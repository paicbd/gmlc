/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class SSR_OrbitCorrectionSatelliteElement_r15 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SSR-OrbitCorrectionSatelliteElement-r15";
   }

   protected SV_ID svID_r15;
   protected Asn1BitString iod_r15;
   protected Asn1Integer delta_radial_r15;
   protected Asn1Integer delta_AlongTrack_r15;
   protected Asn1Integer delta_CrossTrack_r15;
   protected Asn1Integer dot_delta_radial_r15;  // optional
   protected Asn1Integer dot_delta_AlongTrack_r15;  // optional
   protected Asn1Integer dot_delta_CrossTrack_r15;  // optional
   protected Asn1OpenExt extElem1;

   public SSR_OrbitCorrectionSatelliteElement_r15 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SSR_OrbitCorrectionSatelliteElement_r15 (
      SV_ID svID_r15_,
      Asn1BitString iod_r15_,
      Asn1Integer delta_radial_r15_,
      Asn1Integer delta_AlongTrack_r15_,
      Asn1Integer delta_CrossTrack_r15_,
      Asn1Integer dot_delta_radial_r15_,
      Asn1Integer dot_delta_AlongTrack_r15_,
      Asn1Integer dot_delta_CrossTrack_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setIod_r15 (iod_r15_);
      setDelta_radial_r15 (delta_radial_r15_);
      setDelta_AlongTrack_r15 (delta_AlongTrack_r15_);
      setDelta_CrossTrack_r15 (delta_CrossTrack_r15_);
      setDot_delta_radial_r15 (dot_delta_radial_r15_);
      setDot_delta_AlongTrack_r15 (dot_delta_AlongTrack_r15_);
      setDot_delta_CrossTrack_r15 (dot_delta_CrossTrack_r15_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public SSR_OrbitCorrectionSatelliteElement_r15 (
      SV_ID svID_r15_,
      Asn1BitString iod_r15_,
      Asn1Integer delta_radial_r15_,
      Asn1Integer delta_AlongTrack_r15_,
      Asn1Integer delta_CrossTrack_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setIod_r15 (iod_r15_);
      setDelta_radial_r15 (delta_radial_r15_);
      setDelta_AlongTrack_r15 (delta_AlongTrack_r15_);
      setDelta_CrossTrack_r15 (delta_CrossTrack_r15_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public SSR_OrbitCorrectionSatelliteElement_r15 (SV_ID svID_r15_,
      Asn1BitString iod_r15_,
      long delta_radial_r15_,
      long delta_AlongTrack_r15_,
      long delta_CrossTrack_r15_,
      long dot_delta_radial_r15_,
      long dot_delta_AlongTrack_r15_,
      long dot_delta_CrossTrack_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setIod_r15 (iod_r15_);
      setDelta_radial_r15 (delta_radial_r15_);
      setDelta_AlongTrack_r15 (delta_AlongTrack_r15_);
      setDelta_CrossTrack_r15 (delta_CrossTrack_r15_);
      setDot_delta_radial_r15 (dot_delta_radial_r15_);
      setDot_delta_AlongTrack_r15 (dot_delta_AlongTrack_r15_);
      setDot_delta_CrossTrack_r15 (dot_delta_CrossTrack_r15_);
   }

   /**
    * This constructor is for required elements only.  It allows 
    * primitive data to be passed for all primitive elements.  
    * It will create new object wrappers for the primitive data 
    * and set other elements to references to the given objects. 
    */
   public SSR_OrbitCorrectionSatelliteElement_r15 (
      SV_ID svID_r15_,
      Asn1BitString iod_r15_,
      long delta_radial_r15_,
      long delta_AlongTrack_r15_,
      long delta_CrossTrack_r15_
   ) throws Asn1Exception {
      super();
      setSvID_r15 (svID_r15_);
      setIod_r15 (iod_r15_);
      setDelta_radial_r15 (delta_radial_r15_);
      setDelta_AlongTrack_r15 (delta_AlongTrack_r15_);
      setDelta_CrossTrack_r15 (delta_CrossTrack_r15_);
   }

   public void init () {
      svID_r15 = null;
      iod_r15 = null;
      delta_radial_r15 = null;
      delta_AlongTrack_r15 = null;
      delta_CrossTrack_r15 = null;
      dot_delta_radial_r15 = null;
      dot_delta_AlongTrack_r15 = null;
      dot_delta_CrossTrack_r15 = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SSR_OrbitCorrectionSatelliteElement_r15) ) return false;

      SSR_OrbitCorrectionSatelliteElement_r15 rhs = (SSR_OrbitCorrectionSatelliteElement_r15) obj;

      if (svID_r15 == null) {
         if (rhs.svID_r15 != null) return false;
      }
      else {
         if (!svID_r15.equals(rhs.svID_r15)) {
            return false;
         }
      }

      if (iod_r15 == null) {
         if (rhs.iod_r15 != null) return false;
      }
      else {
         if (!iod_r15.equals(rhs.iod_r15)) {
            return false;
         }
      }

      if (delta_radial_r15 == null) {
         if (rhs.delta_radial_r15 != null) return false;
      }
      else {
         if (!delta_radial_r15.equals(rhs.delta_radial_r15)) {
            return false;
         }
      }

      if (delta_AlongTrack_r15 == null) {
         if (rhs.delta_AlongTrack_r15 != null) return false;
      }
      else {
         if (!delta_AlongTrack_r15.equals(rhs.delta_AlongTrack_r15)) {
            return false;
         }
      }

      if (delta_CrossTrack_r15 == null) {
         if (rhs.delta_CrossTrack_r15 != null) return false;
      }
      else {
         if (!delta_CrossTrack_r15.equals(rhs.delta_CrossTrack_r15)) {
            return false;
         }
      }

      if (dot_delta_radial_r15 == null) {
         if (rhs.dot_delta_radial_r15 != null) return false;
      }
      else {
         if (!dot_delta_radial_r15.equals(rhs.dot_delta_radial_r15)) {
            return false;
         }
      }

      if (dot_delta_AlongTrack_r15 == null) {
         if (rhs.dot_delta_AlongTrack_r15 != null) return false;
      }
      else {
         if (!dot_delta_AlongTrack_r15.equals(rhs.dot_delta_AlongTrack_r15)) {
            return false;
         }
      }

      if (dot_delta_CrossTrack_r15 == null) {
         if (rhs.dot_delta_CrossTrack_r15 != null) return false;
      }
      else {
         if (!dot_delta_CrossTrack_r15.equals(rhs.dot_delta_CrossTrack_r15)) {
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

      if (svID_r15 != null) __code = 31*__code + svID_r15.hashCode();
      if (iod_r15 != null) __code = 31*__code + iod_r15.hashCode();
      if (delta_radial_r15 != null) __code = 31*__code + delta_radial_r15.hashCode();
      if (delta_AlongTrack_r15 != null) __code = 31*__code + delta_AlongTrack_r15.hashCode();
      if (delta_CrossTrack_r15 != null) __code = 31*__code + delta_CrossTrack_r15.hashCode();
      if (dot_delta_radial_r15 != null) __code = 31*__code + dot_delta_radial_r15.hashCode();
      if (dot_delta_AlongTrack_r15 != null) __code = 31*__code + dot_delta_AlongTrack_r15.hashCode();
      if (dot_delta_CrossTrack_r15 != null) __code = 31*__code + dot_delta_CrossTrack_r15.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for SvID_r15
    */
   public SV_ID getSvID_r15 () {
      return svID_r15;
   }

   public void setSvID_r15 (SV_ID value) {
      this.svID_r15 = value;
   }

   /**
    * Accessor/mutator methods for Iod_r15
    */
   public Asn1BitString getIod_r15 () {
      return iod_r15;
   }

   public void setIod_r15 (Asn1BitString value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 11)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.iod_r15 = value;
   }

   /**
    * Accessor/mutator methods for Delta_radial_r15
    */
   public Asn1Integer getDelta_radial_r15 () {
      return delta_radial_r15;
   }

   public void setDelta_radial_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -2097152 && value.value <= 2097151))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.delta_radial_r15 = value;
   }

   public void setDelta_radial_r15 (long value) 
      throws Asn1Exception
   {
      setDelta_radial_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Delta_AlongTrack_r15
    */
   public Asn1Integer getDelta_AlongTrack_r15 () {
      return delta_AlongTrack_r15;
   }

   public void setDelta_AlongTrack_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -524288 && value.value <= 524287))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.delta_AlongTrack_r15 = value;
   }

   public void setDelta_AlongTrack_r15 (long value) 
      throws Asn1Exception
   {
      setDelta_AlongTrack_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Delta_CrossTrack_r15
    */
   public Asn1Integer getDelta_CrossTrack_r15 () {
      return delta_CrossTrack_r15;
   }

   public void setDelta_CrossTrack_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -524288 && value.value <= 524287))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.delta_CrossTrack_r15 = value;
   }

   public void setDelta_CrossTrack_r15 (long value) 
      throws Asn1Exception
   {
      setDelta_CrossTrack_r15 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Dot_delta_radial_r15
    */
   public Asn1Integer getDot_delta_radial_r15 () {
      return dot_delta_radial_r15;
   }

   public void setDot_delta_radial_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -1048576 && value.value <= 1048575))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dot_delta_radial_r15 = value;
   }

   public void setDot_delta_radial_r15 (long value) 
      throws Asn1Exception
   {
      setDot_delta_radial_r15 (new Asn1Integer(value));
   }
   public boolean hasDot_delta_radial_r15 () {
      return (dot_delta_radial_r15 != null);
   }

   /**
    * Accessor/mutator methods for Dot_delta_AlongTrack_r15
    */
   public Asn1Integer getDot_delta_AlongTrack_r15 () {
      return dot_delta_AlongTrack_r15;
   }

   public void setDot_delta_AlongTrack_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -262144 && value.value <= 262143))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dot_delta_AlongTrack_r15 = value;
   }

   public void setDot_delta_AlongTrack_r15 (long value) 
      throws Asn1Exception
   {
      setDot_delta_AlongTrack_r15 (new Asn1Integer(value));
   }
   public boolean hasDot_delta_AlongTrack_r15 () {
      return (dot_delta_AlongTrack_r15 != null);
   }

   /**
    * Accessor/mutator methods for Dot_delta_CrossTrack_r15
    */
   public Asn1Integer getDot_delta_CrossTrack_r15 () {
      return dot_delta_CrossTrack_r15;
   }

   public void setDot_delta_CrossTrack_r15 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -262144 && value.value <= 262143))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.dot_delta_CrossTrack_r15 = value;
   }

   public void setDot_delta_CrossTrack_r15 (long value) 
      throws Asn1Exception
   {
      setDot_delta_CrossTrack_r15 (new Asn1Integer(value));
   }
   public boolean hasDot_delta_CrossTrack_r15 () {
      return (dot_delta_CrossTrack_r15 != null);
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

   public int getElementCount() { return 9; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return svID_r15;
         case 1: return iod_r15;
         case 2: return delta_radial_r15;
         case 3: return delta_AlongTrack_r15;
         case 4: return delta_CrossTrack_r15;
         case 5: return dot_delta_radial_r15;
         case 6: return dot_delta_AlongTrack_r15;
         case 7: return dot_delta_CrossTrack_r15;
         case 8: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "svID-r15";
         case 1: return "iod-r15";
         case 2: return "delta-radial-r15";
         case 3: return "delta-AlongTrack-r15";
         case 4: return "delta-CrossTrack-r15";
         case 5: return "dot-delta-radial-r15";
         case 6: return "dot-delta-AlongTrack-r15";
         case 7: return "dot-delta-CrossTrack-r15";
         case 8: return null;
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

      boolean dot_delta_radial_r15Present = buffer.decodeBit ("dot_delta_radial_r15Present");
      boolean dot_delta_AlongTrack_r15Present = buffer.decodeBit ("dot_delta_AlongTrack_r15Present");
      boolean dot_delta_CrossTrack_r15Present = buffer.decodeBit ("dot_delta_CrossTrack_r15Present");

      // decode svID_r15

      buffer.getContext().eventDispatcher.startElement("svID_r15", -1);

      svID_r15 = new SV_ID();
      svID_r15.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("svID_r15", -1);

      // decode iod_r15

      buffer.getContext().eventDispatcher.startElement("iod_r15", -1);

      iod_r15 = new Asn1BitString();
      iod_r15.decode (buffer, 11, 11);
      buffer.getContext().eventDispatcher.endElement("iod_r15", -1);

      // decode delta_radial_r15

      buffer.getContext().eventDispatcher.startElement("delta_radial_r15", -1);

      delta_radial_r15 = new Asn1Integer();
      delta_radial_r15.decode (buffer, -2097152, 2097151);
      buffer.getContext().eventDispatcher.endElement("delta_radial_r15", -1);

      // decode delta_AlongTrack_r15

      buffer.getContext().eventDispatcher.startElement("delta_AlongTrack_r15", -1);

      delta_AlongTrack_r15 = new Asn1Integer();
      delta_AlongTrack_r15.decode (buffer, -524288, 524287);
      buffer.getContext().eventDispatcher.endElement("delta_AlongTrack_r15", -1);

      // decode delta_CrossTrack_r15

      buffer.getContext().eventDispatcher.startElement("delta_CrossTrack_r15", -1);

      delta_CrossTrack_r15 = new Asn1Integer();
      delta_CrossTrack_r15.decode (buffer, -524288, 524287);
      buffer.getContext().eventDispatcher.endElement("delta_CrossTrack_r15", -1);

      // decode dot_delta_radial_r15

      if (dot_delta_radial_r15Present) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_radial_r15", -1);

         dot_delta_radial_r15 = new Asn1Integer();
         dot_delta_radial_r15.decode (buffer, -1048576, 1048575);
         buffer.getContext().eventDispatcher.endElement("dot_delta_radial_r15", -1);
      }
      else {
         dot_delta_radial_r15 = null;
      }

      // decode dot_delta_AlongTrack_r15

      if (dot_delta_AlongTrack_r15Present) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_AlongTrack_r15", -1);

         dot_delta_AlongTrack_r15 = new Asn1Integer();
         dot_delta_AlongTrack_r15.decode (buffer, -262144, 262143);
         buffer.getContext().eventDispatcher.endElement("dot_delta_AlongTrack_r15", -1);
      }
      else {
         dot_delta_AlongTrack_r15 = null;
      }

      // decode dot_delta_CrossTrack_r15

      if (dot_delta_CrossTrack_r15Present) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_CrossTrack_r15", -1);

         dot_delta_CrossTrack_r15 = new Asn1Integer();
         dot_delta_CrossTrack_r15.decode (buffer, -262144, 262143);
         buffer.getContext().eventDispatcher.endElement("dot_delta_CrossTrack_r15", -1);
      }
      else {
         dot_delta_CrossTrack_r15 = null;
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

      buffer.encodeBit ((dot_delta_radial_r15 != null), null);
      buffer.encodeBit ((dot_delta_AlongTrack_r15 != null), null);
      buffer.encodeBit ((dot_delta_CrossTrack_r15 != null), null);

      // encode svID_r15

      if (svID_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("svID_r15", -1);

         svID_r15.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("svID_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("svID_r15");

      // encode iod_r15

      if (iod_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("iod_r15", -1);

         iod_r15.encode (buffer, 11, 11);

         buffer.getContext().eventDispatcher.endElement("iod_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("iod_r15");

      // encode delta_radial_r15

      if (delta_radial_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("delta_radial_r15", -1);

         delta_radial_r15.encode (buffer, -2097152, 2097151);

         buffer.getContext().eventDispatcher.endElement("delta_radial_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("delta_radial_r15");

      // encode delta_AlongTrack_r15

      if (delta_AlongTrack_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("delta_AlongTrack_r15", -1);

         delta_AlongTrack_r15.encode (buffer, -524288, 524287);

         buffer.getContext().eventDispatcher.endElement("delta_AlongTrack_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("delta_AlongTrack_r15");

      // encode delta_CrossTrack_r15

      if (delta_CrossTrack_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("delta_CrossTrack_r15", -1);

         delta_CrossTrack_r15.encode (buffer, -524288, 524287);

         buffer.getContext().eventDispatcher.endElement("delta_CrossTrack_r15", -1);
      }
      else throw new Asn1MissingRequiredException ("delta_CrossTrack_r15");

      // encode dot_delta_radial_r15

      if (dot_delta_radial_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_radial_r15", -1);

         dot_delta_radial_r15.encode (buffer, -1048576, 1048575);

         buffer.getContext().eventDispatcher.endElement("dot_delta_radial_r15", -1);
      }

      // encode dot_delta_AlongTrack_r15

      if (dot_delta_AlongTrack_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_AlongTrack_r15", -1);

         dot_delta_AlongTrack_r15.encode (buffer, -262144, 262143);

         buffer.getContext().eventDispatcher.endElement("dot_delta_AlongTrack_r15", -1);
      }

      // encode dot_delta_CrossTrack_r15

      if (dot_delta_CrossTrack_r15 != null) {
         buffer.getContext().eventDispatcher.startElement("dot_delta_CrossTrack_r15", -1);

         dot_delta_CrossTrack_r15.encode (buffer, -262144, 262143);

         buffer.getContext().eventDispatcher.endElement("dot_delta_CrossTrack_r15", -1);
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
