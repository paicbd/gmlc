/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class HighAccuracyAltitudeInfo extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "HighAccuracyAltitudeInfo";
   }

   protected Asn1Integer altitude;
   protected Asn1Integer uncertaintyAltitude;
   protected Asn1Integer verticalConfidence;
   protected Asn1OpenExt extElem1;

   public HighAccuracyAltitudeInfo () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public HighAccuracyAltitudeInfo (
      Asn1Integer altitude_,
      Asn1Integer uncertaintyAltitude_,
      Asn1Integer verticalConfidence_
   ) throws Asn1Exception {
      super();
      setAltitude (altitude_);
      setUncertaintyAltitude (uncertaintyAltitude_);
      setVerticalConfidence (verticalConfidence_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public HighAccuracyAltitudeInfo (long altitude_,
      long uncertaintyAltitude_,
      long verticalConfidence_
   ) throws Asn1Exception {
      super();
      setAltitude (altitude_);
      setUncertaintyAltitude (uncertaintyAltitude_);
      setVerticalConfidence (verticalConfidence_);
   }

   public void init () {
      altitude = null;
      uncertaintyAltitude = null;
      verticalConfidence = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof HighAccuracyAltitudeInfo) ) return false;

      HighAccuracyAltitudeInfo rhs = (HighAccuracyAltitudeInfo) obj;

      if (altitude == null) {
         if (rhs.altitude != null) return false;
      }
      else {
         if (!altitude.equals(rhs.altitude)) {
            return false;
         }
      }

      if (uncertaintyAltitude == null) {
         if (rhs.uncertaintyAltitude != null) return false;
      }
      else {
         if (!uncertaintyAltitude.equals(rhs.uncertaintyAltitude)) {
            return false;
         }
      }

      if (verticalConfidence == null) {
         if (rhs.verticalConfidence != null) return false;
      }
      else {
         if (!verticalConfidence.equals(rhs.verticalConfidence)) {
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

      if (altitude != null) __code = 31*__code + altitude.hashCode();
      if (uncertaintyAltitude != null) __code = 31*__code + uncertaintyAltitude.hashCode();
      if (verticalConfidence != null) __code = 31*__code + verticalConfidence.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for Altitude
    */
   public Asn1Integer getAltitude () {
      return altitude;
   }

   public void setAltitude (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 64000 && value.value <= 1280000))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.altitude = value;
   }

   public void setAltitude (long value) 
      throws Asn1Exception
   {
      setAltitude (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for UncertaintyAltitude
    */
   public Asn1Integer getUncertaintyAltitude () {
      return uncertaintyAltitude;
   }

   public void setUncertaintyAltitude (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 255))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.uncertaintyAltitude = value;
   }

   public void setUncertaintyAltitude (long value) 
      throws Asn1Exception
   {
      setUncertaintyAltitude (new Asn1Integer(value));
   }

   /**
    * Accessor/mutator methods for VerticalConfidence
    */
   public Asn1Integer getVerticalConfidence () {
      return verticalConfidence;
   }

   public void setVerticalConfidence (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 100))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.verticalConfidence = value;
   }

   public void setVerticalConfidence (long value) 
      throws Asn1Exception
   {
      setVerticalConfidence (new Asn1Integer(value));
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
         case 0: return altitude;
         case 1: return uncertaintyAltitude;
         case 2: return verticalConfidence;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "altitude";
         case 1: return "uncertaintyAltitude";
         case 2: return "verticalConfidence";
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

      // decode altitude

      buffer.getContext().eventDispatcher.startElement("altitude", -1);

      altitude = new Asn1Integer();
      altitude.decode (buffer, 64000, 1280000);
      buffer.getContext().eventDispatcher.endElement("altitude", -1);

      // decode uncertaintyAltitude

      buffer.getContext().eventDispatcher.startElement("uncertaintyAltitude", -1);

      uncertaintyAltitude = new Asn1Integer();
      uncertaintyAltitude.decode (buffer, 0, 255);
      buffer.getContext().eventDispatcher.endElement("uncertaintyAltitude", -1);

      // decode verticalConfidence

      buffer.getContext().eventDispatcher.startElement("verticalConfidence", -1);

      verticalConfidence = new Asn1Integer();
      verticalConfidence.decode (buffer, 0, 100);
      buffer.getContext().eventDispatcher.endElement("verticalConfidence", -1);

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

      // encode altitude

      if (altitude != null) {
         buffer.getContext().eventDispatcher.startElement("altitude", -1);

         altitude.encode (buffer, 64000, 1280000);

         buffer.getContext().eventDispatcher.endElement("altitude", -1);
      }
      else throw new Asn1MissingRequiredException ("altitude");

      // encode uncertaintyAltitude

      if (uncertaintyAltitude != null) {
         buffer.getContext().eventDispatcher.startElement("uncertaintyAltitude", -1);

         uncertaintyAltitude.encode (buffer, 0, 255);

         buffer.getContext().eventDispatcher.endElement("uncertaintyAltitude", -1);
      }
      else throw new Asn1MissingRequiredException ("uncertaintyAltitude");

      // encode verticalConfidence

      if (verticalConfidence != null) {
         buffer.getContext().eventDispatcher.startElement("verticalConfidence", -1);

         verticalConfidence.encode (buffer, 0, 100);

         buffer.getContext().eventDispatcher.endElement("verticalConfidence", -1);
      }
      else throw new Asn1MissingRequiredException ("verticalConfidence");

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
