/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class LocationFailureCause extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "LocationFailureCause";
   }

   // Integer constants for switch-case
   public static final int _UNDEFINED_ = 0;
   public static final int _REQUESTEDMETHODNOTSUPPORTED = 1;
   public static final int _POSITIONMETHODFAILURE = 2;
   public static final int _PERIODICLOCATIONMEASUREMENTSNOTAVAILABLE = 3;

   // Singleton instances of LocationFailureCause
   protected static final LocationFailureCause _undefined_ = new LocationFailureCause(0);
   protected static final LocationFailureCause _requestedMethodNotSupported = new LocationFailureCause(1);
   protected static final LocationFailureCause _positionMethodFailure = new LocationFailureCause(2);
   protected static final LocationFailureCause _periodicLocationMeasurementsNotAvailable = new LocationFailureCause(3);
   private static LocationFailureCause __undefined = new LocationFailureCause(-999);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    */
   protected LocationFailureCause (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for undefined_.
    */
   public static LocationFailureCause undefined_() {
      return _undefined_;
   }

   /**
    * Singleton accessor method for requestedMethodNotSupported.
    */
   public static LocationFailureCause requestedMethodNotSupported() {
      return _requestedMethodNotSupported;
   }

   /**
    * Singleton accessor method for positionMethodFailure.
    */
   public static LocationFailureCause positionMethodFailure() {
      return _positionMethodFailure;
   }

   /**
    * Singleton accessor method for periodicLocationMeasurementsNotAvailable.
    */
   public static LocationFailureCause periodicLocationMeasurementsNotAvailable() {
      return _periodicLocationMeasurementsNotAvailable;
   }

   /** Undefined value. */
   protected static LocationFailureCause __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return LocationFailureCause.valueOf( LocationFailureCause.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static LocationFailureCause valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return undefined_();
         case 1: return requestedMethodNotSupported();
         case 2: return positionMethodFailure();
         case 3: return periodicLocationMeasurementsNotAvailable();
         default: return __undefined_();
      }
   }

   public static int decodeEnumValue (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int ui;  // enum index
      int ret; // return value
      boolean extbit = buffer.decodeBit ("extbit");

      if (extbit) {
         ui = buffer.decodeSmallNonNegWholeNumber ();

         return 0x7fffffff;
      }
      else {
         ui = (int) buffer.decodeConsWholeNumber (4);

         switch (ui) {
            case 0: ret =  0; break;
            case 1: ret =  1; break;
            case 2: ret =  2; break;
            case 3: ret =  3; break;
            default: throw new Asn1InvalidEnumException (ui);
         }
      }

      buffer.setTypeCode (Asn1Type.ENUMERATED);
      return ret;
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int ui;  // enum index
      boolean extbit = false;

      switch ((int)value) {
         case 0: ui = 0; /* undefined_ */break;
         case 1: ui = 1; /* requestedMethodNotSupported */break;
         case 2: ui = 2; /* positionMethodFailure */break;
         case 3: ui = 3; /* periodicLocationMeasurementsNotAvailable */break;
         default:
            ui = (int) value;
            extbit = true;
      }

      buffer.encodeBit (extbit, "extbit");

      if (extbit) {
         buffer.encodeSmallNonNegWholeNumber (ui);
      }
      else {
         buffer.encodeConsWholeNumber (ui, 4);
      }
   }

}
