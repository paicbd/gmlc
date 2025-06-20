/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class UTC_ModelSet1 extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "UTC-ModelSet1";
   }

   protected Asn1Integer gnss_Utc_A1;
   protected Asn1Integer gnss_Utc_A0;
   protected Asn1Integer gnss_Utc_Tot;
   protected Asn1Integer gnss_Utc_WNt;
   protected Asn1Integer gnss_Utc_DeltaTls;
   protected Asn1Integer gnss_Utc_WNlsf;
   protected Asn1Integer gnss_Utc_DN;
   protected Asn1Integer gnss_Utc_DeltaTlsf;
   protected Asn1OpenExt extElem1;

   public UTC_ModelSet1 () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public UTC_ModelSet1 (
      Asn1Integer gnss_Utc_A1_,
      Asn1Integer gnss_Utc_A0_,
      Asn1Integer gnss_Utc_Tot_,
      Asn1Integer gnss_Utc_WNt_,
      Asn1Integer gnss_Utc_DeltaTls_,
      Asn1Integer gnss_Utc_WNlsf_,
      Asn1Integer gnss_Utc_DN_,
      Asn1Integer gnss_Utc_DeltaTlsf_
   ) throws Asn1Exception {
      super();
      setGnss_Utc_A1 (gnss_Utc_A1_);
      setGnss_Utc_A0 (gnss_Utc_A0_);
      setGnss_Utc_Tot (gnss_Utc_Tot_);
      setGnss_Utc_WNt (gnss_Utc_WNt_);
      setGnss_Utc_DeltaTls (gnss_Utc_DeltaTls_);
      setGnss_Utc_WNlsf (gnss_Utc_WNlsf_);
      setGnss_Utc_DN (gnss_Utc_DN_);
      setGnss_Utc_DeltaTlsf (gnss_Utc_DeltaTlsf_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public UTC_ModelSet1 (long gnss_Utc_A1_,
      long gnss_Utc_A0_,
      long gnss_Utc_Tot_,
      long gnss_Utc_WNt_,
      long gnss_Utc_DeltaTls_,
      long gnss_Utc_WNlsf_,
      long gnss_Utc_DN_,
      long gnss_Utc_DeltaTlsf_
   ) throws Asn1Exception {
      super();
      setGnss_Utc_A1 (gnss_Utc_A1_);
      setGnss_Utc_A0 (gnss_Utc_A0_);
      setGnss_Utc_Tot (gnss_Utc_Tot_);
      setGnss_Utc_WNt (gnss_Utc_WNt_);
      setGnss_Utc_DeltaTls (gnss_Utc_DeltaTls_);
      setGnss_Utc_WNlsf (gnss_Utc_WNlsf_);
      setGnss_Utc_DN (gnss_Utc_DN_);
      setGnss_Utc_DeltaTlsf (gnss_Utc_DeltaTlsf_);
   }

   public void init () {
      gnss_Utc_A1 = null;
      gnss_Utc_A0 = null;
      gnss_Utc_Tot = null;
      gnss_Utc_WNt = null;
      gnss_Utc_DeltaTls = null;
      gnss_Utc_WNlsf = null;
      gnss_Utc_DN = null;
      gnss_Utc_DeltaTlsf = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof UTC_ModelSet1) ) return false;

      UTC_ModelSet1 rhs = (UTC_ModelSet1) obj;

      if (gnss_Utc_A1 == null) {
         if (rhs.gnss_Utc_A1 != null) return false;
      }
      else {
         if (!gnss_Utc_A1.equals(rhs.gnss_Utc_A1)) {
            return false;
         }
      }

      if (gnss_Utc_A0 == null) {
         if (rhs.gnss_Utc_A0 != null) return false;
      }
      else {
         if (!gnss_Utc_A0.equals(rhs.gnss_Utc_A0)) {
            return false;
         }
      }

      if (gnss_Utc_Tot == null) {
         if (rhs.gnss_Utc_Tot != null) return false;
      }
      else {
         if (!gnss_Utc_Tot.equals(rhs.gnss_Utc_Tot)) {
            return false;
         }
      }

      if (gnss_Utc_WNt == null) {
         if (rhs.gnss_Utc_WNt != null) return false;
      }
      else {
         if (!gnss_Utc_WNt.equals(rhs.gnss_Utc_WNt)) {
            return false;
         }
      }

      if (gnss_Utc_DeltaTls == null) {
         if (rhs.gnss_Utc_DeltaTls != null) return false;
      }
      else {
         if (!gnss_Utc_DeltaTls.equals(rhs.gnss_Utc_DeltaTls)) {
            return false;
         }
      }

      if (gnss_Utc_WNlsf == null) {
         if (rhs.gnss_Utc_WNlsf != null) return false;
      }
      else {
         if (!gnss_Utc_WNlsf.equals(rhs.gnss_Utc_WNlsf)) {
            return false;
         }
      }

      if (gnss_Utc_DN == null) {
         if (rhs.gnss_Utc_DN != null) return false;
      }
      else {
         if (!gnss_Utc_DN.equals(rhs.gnss_Utc_DN)) {
            return false;
         }
      }

      if (gnss_Utc_DeltaTlsf == null) {
         if (rhs.gnss_Utc_DeltaTlsf != null) return false;
      }
      else {
         if (!gnss_Utc_DeltaTlsf.equals(rhs.gnss_Utc_DeltaTlsf)) {
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

      if (gnss_Utc_A1 != null) __code = 31*__code + gnss_Utc_A1.hashCode();
      if (gnss_Utc_A0 != null) __code = 31*__code + gnss_Utc_A0.hashCode();
      if (gnss_Utc_Tot != null) __code = 31*__code + gnss_Utc_Tot.hashCode();
      if (gnss_Utc_WNt != null) __code = 31*__code + gnss_Utc_WNt.hashCode();
      if (gnss_Utc_DeltaTls != null) __code = 31*__code + gnss_Utc_DeltaTls.hashCode();
      if (gnss_Utc_WNlsf != null) __code = 31*__code + gnss_Utc_WNlsf.hashCode();
      if (gnss_Utc_DN != null) __code = 31*__code + gnss_Utc_DN.hashCode();
      if (gnss_Utc_DeltaTlsf != null) __code = 31*__code + gnss_Utc_DeltaTlsf.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_A1
    */
   public Asn1Integer getGnss_Utc_A1 () {
      return gnss_Utc_A1;
   }

   public void setGnss_Utc_A1 (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -8388608 && value.value <= 8388607))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_A1 = value;
   }

   public void setGnss_Utc_A1 (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_A1 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_A0
    */
   public Asn1Integer getGnss_Utc_A0 () {
      return gnss_Utc_A0;
   }

   public void setGnss_Utc_A0 (Asn1Integer value) 
      throws Asn1Exception
   {
      this.gnss_Utc_A0 = value;
   }

   public void setGnss_Utc_A0 (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_A0 (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_Tot
    */
   public Asn1Integer getGnss_Utc_Tot () {
      return gnss_Utc_Tot;
   }

   public void setGnss_Utc_Tot (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 255))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_Tot = value;
   }

   public void setGnss_Utc_Tot (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_Tot (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_WNt
    */
   public Asn1Integer getGnss_Utc_WNt () {
      return gnss_Utc_WNt;
   }

   public void setGnss_Utc_WNt (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 255))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_WNt = value;
   }

   public void setGnss_Utc_WNt (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_WNt (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_DeltaTls
    */
   public Asn1Integer getGnss_Utc_DeltaTls () {
      return gnss_Utc_DeltaTls;
   }

   public void setGnss_Utc_DeltaTls (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -128 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_DeltaTls = value;
   }

   public void setGnss_Utc_DeltaTls (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_DeltaTls (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_WNlsf
    */
   public Asn1Integer getGnss_Utc_WNlsf () {
      return gnss_Utc_WNlsf;
   }

   public void setGnss_Utc_WNlsf (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 255))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_WNlsf = value;
   }

   public void setGnss_Utc_WNlsf (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_WNlsf (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_DN
    */
   public Asn1Integer getGnss_Utc_DN () {
      return gnss_Utc_DN;
   }

   public void setGnss_Utc_DN (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -128 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_DN = value;
   }

   public void setGnss_Utc_DN (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_DN (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for Gnss_Utc_DeltaTlsf
    */
   public Asn1Integer getGnss_Utc_DeltaTlsf () {
      return gnss_Utc_DeltaTlsf;
   }

   public void setGnss_Utc_DeltaTlsf (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= -128 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.gnss_Utc_DeltaTlsf = value;
   }

   public void setGnss_Utc_DeltaTlsf (long value) 
      throws Asn1Exception
   {
      setGnss_Utc_DeltaTlsf (new Asn1Integer(value));
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
         case 0: return gnss_Utc_A1;
         case 1: return gnss_Utc_A0;
         case 2: return gnss_Utc_Tot;
         case 3: return gnss_Utc_WNt;
         case 4: return gnss_Utc_DeltaTls;
         case 5: return gnss_Utc_WNlsf;
         case 6: return gnss_Utc_DN;
         case 7: return gnss_Utc_DeltaTlsf;
         case 8: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "gnss-Utc-A1";
         case 1: return "gnss-Utc-A0";
         case 2: return "gnss-Utc-Tot";
         case 3: return "gnss-Utc-WNt";
         case 4: return "gnss-Utc-DeltaTls";
         case 5: return "gnss-Utc-WNlsf";
         case 6: return "gnss-Utc-DN";
         case 7: return "gnss-Utc-DeltaTlsf";
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

      // decode gnss_Utc_A1

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_A1", -1);

      gnss_Utc_A1 = new Asn1Integer();
      gnss_Utc_A1.decode (buffer, -8388608, 8388607);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_A1", -1);

      // decode gnss_Utc_A0

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_A0", -1);

      gnss_Utc_A0 = new Asn1Integer();
      gnss_Utc_A0.decode (buffer, -2147483648, 2147483647);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_A0", -1);

      // decode gnss_Utc_Tot

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_Tot", -1);

      gnss_Utc_Tot = new Asn1Integer();
      gnss_Utc_Tot.decode (buffer, 0, 255);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_Tot", -1);

      // decode gnss_Utc_WNt

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_WNt", -1);

      gnss_Utc_WNt = new Asn1Integer();
      gnss_Utc_WNt.decode (buffer, 0, 255);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_WNt", -1);

      // decode gnss_Utc_DeltaTls

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_DeltaTls", -1);

      gnss_Utc_DeltaTls = new Asn1Integer();
      gnss_Utc_DeltaTls.decode (buffer, -128, 127);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_DeltaTls", -1);

      // decode gnss_Utc_WNlsf

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_WNlsf", -1);

      gnss_Utc_WNlsf = new Asn1Integer();
      gnss_Utc_WNlsf.decode (buffer, 0, 255);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_WNlsf", -1);

      // decode gnss_Utc_DN

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_DN", -1);

      gnss_Utc_DN = new Asn1Integer();
      gnss_Utc_DN.decode (buffer, -128, 127);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_DN", -1);

      // decode gnss_Utc_DeltaTlsf

      buffer.getContext().eventDispatcher.startElement("gnss_Utc_DeltaTlsf", -1);

      gnss_Utc_DeltaTlsf = new Asn1Integer();
      gnss_Utc_DeltaTlsf.decode (buffer, -128, 127);
      buffer.getContext().eventDispatcher.endElement("gnss_Utc_DeltaTlsf", -1);

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

      // encode gnss_Utc_A1

      if (gnss_Utc_A1 != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_A1", -1);

         gnss_Utc_A1.encode (buffer, -8388608, 8388607);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_A1", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_A1");

      // encode gnss_Utc_A0

      if (gnss_Utc_A0 != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_A0", -1);

         gnss_Utc_A0.encode (buffer, -2147483648, 2147483647);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_A0", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_A0");

      // encode gnss_Utc_Tot

      if (gnss_Utc_Tot != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_Tot", -1);

         gnss_Utc_Tot.encode (buffer, 0, 255);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_Tot", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_Tot");

      // encode gnss_Utc_WNt

      if (gnss_Utc_WNt != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_WNt", -1);

         gnss_Utc_WNt.encode (buffer, 0, 255);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_WNt", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_WNt");

      // encode gnss_Utc_DeltaTls

      if (gnss_Utc_DeltaTls != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_DeltaTls", -1);

         gnss_Utc_DeltaTls.encode (buffer, -128, 127);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_DeltaTls", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_DeltaTls");

      // encode gnss_Utc_WNlsf

      if (gnss_Utc_WNlsf != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_WNlsf", -1);

         gnss_Utc_WNlsf.encode (buffer, 0, 255);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_WNlsf", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_WNlsf");

      // encode gnss_Utc_DN

      if (gnss_Utc_DN != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_DN", -1);

         gnss_Utc_DN.encode (buffer, -128, 127);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_DN", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_DN");

      // encode gnss_Utc_DeltaTlsf

      if (gnss_Utc_DeltaTlsf != null) {
         buffer.getContext().eventDispatcher.startElement("gnss_Utc_DeltaTlsf", -1);

         gnss_Utc_DeltaTlsf.encode (buffer, -128, 127);

         buffer.getContext().eventDispatcher.endElement("gnss_Utc_DeltaTlsf", -1);
      }
      else throw new Asn1MissingRequiredException ("gnss_Utc_DeltaTlsf");

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
