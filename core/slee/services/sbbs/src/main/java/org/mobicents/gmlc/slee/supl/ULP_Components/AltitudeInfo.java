/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Components;

import com.objsys.asn1j.runtime.*;

public class AltitudeInfo extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "AltitudeInfo";
   }

   protected AltitudeInfo_altitudeDirection altitudeDirection = null;
   protected Asn1Integer altitude;
   protected Asn1Integer altUncertainty;
   protected Asn1OpenExt extElem1;

   public AltitudeInfo () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public AltitudeInfo (
      AltitudeInfo_altitudeDirection altitudeDirection_,
      Asn1Integer altitude_,
      Asn1Integer altUncertainty_
   ) throws Asn1Exception {
      super();
      setAltitudeDirection (altitudeDirection_);
      setAltitude (altitude_);
      setAltUncertainty (altUncertainty_);
   }

   /**
    * This constructor allows primitive data to be passed for all 
    * primitive elements.  It will create new object wrappers for 
    * the primitive data and set other elements to references to 
    * the given objects 
    */
   public AltitudeInfo (AltitudeInfo_altitudeDirection altitudeDirection_,
      long altitude_,
      long altUncertainty_
   ) throws Asn1Exception {
      super();
      setAltitudeDirection (altitudeDirection_);
      setAltitude (altitude_);
      setAltUncertainty (altUncertainty_);
   }

   public void init () {
      altitudeDirection = null;
      altitude = null;
      altUncertainty = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof AltitudeInfo) ) return false;

      AltitudeInfo rhs = (AltitudeInfo) obj;

      if (altitudeDirection == null) {
         if (rhs.altitudeDirection != null) return false;
      }
      else {
         if (!altitudeDirection.equals(rhs.altitudeDirection)) {
            return false;
         }
      }

      if (altitude == null) {
         if (rhs.altitude != null) return false;
      }
      else {
         if (!altitude.equals(rhs.altitude)) {
            return false;
         }
      }

      if (altUncertainty == null) {
         if (rhs.altUncertainty != null) return false;
      }
      else {
         if (!altUncertainty.equals(rhs.altUncertainty)) {
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

      if (altitudeDirection != null) __code = 31*__code + altitudeDirection.hashCode();
      if (altitude != null) __code = 31*__code + altitude.hashCode();
      if (altUncertainty != null) __code = 31*__code + altUncertainty.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for AltitudeDirection
    */
   public AltitudeInfo_altitudeDirection getAltitudeDirection () {
      return altitudeDirection;
   }

   public void setAltitudeDirection (AltitudeInfo_altitudeDirection value) {
      this.altitudeDirection = value;
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
      if (!((value.value >= 0 && value.value <= 32767))) {
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
    * Accessor/mutator methods for AltUncertainty
    */
   public Asn1Integer getAltUncertainty () {
      return altUncertainty;
   }

   public void setAltUncertainty (Asn1Integer value) 
      throws Asn1Exception
   {
      if (!((value.value >= 0 && value.value <= 127))) {
         throw new Asn1ConsVioException ("value.value", value.value);
      }

      this.altUncertainty = value;
   }

   public void setAltUncertainty (long value) 
      throws Asn1Exception
   {
      setAltUncertainty (new Asn1Integer(value));
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
         case 0: return altitudeDirection;
         case 1: return altitude;
         case 2: return altUncertainty;
         case 3: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "altitudeDirection";
         case 1: return "altitude";
         case 2: return "altUncertainty";
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

      // decode altitudeDirection

      buffer.getContext().eventDispatcher.startElement("altitudeDirection", -1);

      {
         int tval = AltitudeInfo_altitudeDirection.decodeEnumValue (buffer);
         altitudeDirection = AltitudeInfo_altitudeDirection.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("altitudeDirection", -1);

      // decode altitude

      buffer.getContext().eventDispatcher.startElement("altitude", -1);

      altitude = new Asn1Integer();
      altitude.decode (buffer, 0, 32767);
      buffer.getContext().eventDispatcher.endElement("altitude", -1);

      // decode altUncertainty

      buffer.getContext().eventDispatcher.startElement("altUncertainty", -1);

      altUncertainty = new Asn1Integer();
      altUncertainty.decode (buffer, 0, 127);
      buffer.getContext().eventDispatcher.endElement("altUncertainty", -1);

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

      // encode altitudeDirection

      if (altitudeDirection != null) {
         buffer.getContext().eventDispatcher.startElement("altitudeDirection", -1);

         altitudeDirection.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("altitudeDirection", -1);
      }
      else throw new Asn1MissingRequiredException ("altitudeDirection");

      // encode altitude

      if (altitude != null) {
         buffer.getContext().eventDispatcher.startElement("altitude", -1);

         altitude.encode (buffer, 0, 32767);

         buffer.getContext().eventDispatcher.endElement("altitude", -1);
      }
      else throw new Asn1MissingRequiredException ("altitude");

      // encode altUncertainty

      if (altUncertainty != null) {
         buffer.getContext().eventDispatcher.startElement("altUncertainty", -1);

         altUncertainty.encode (buffer, 0, 127);

         buffer.getContext().eventDispatcher.endElement("altUncertainty", -1);
      }
      else throw new Asn1MissingRequiredException ("altUncertainty");

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
