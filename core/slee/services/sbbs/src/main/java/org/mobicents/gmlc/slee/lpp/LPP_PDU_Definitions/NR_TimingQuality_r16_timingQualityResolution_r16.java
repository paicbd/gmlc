/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_TimingQuality_r16_timingQualityResolution_r16 extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _MDOT1 = 0;
   public static final int _M1 = 1;
   public static final int _M10 = 2;
   public static final int _M30 = 3;

   // Singleton instances of NR_TimingQuality_r16_timingQualityResolution_r16
   protected static final NR_TimingQuality_r16_timingQualityResolution_r16 _mdot1 = new NR_TimingQuality_r16_timingQualityResolution_r16(0);
   protected static final NR_TimingQuality_r16_timingQualityResolution_r16 _m1 = new NR_TimingQuality_r16_timingQualityResolution_r16(1);
   protected static final NR_TimingQuality_r16_timingQualityResolution_r16 _m10 = new NR_TimingQuality_r16_timingQualityResolution_r16(2);
   protected static final NR_TimingQuality_r16_timingQualityResolution_r16 _m30 = new NR_TimingQuality_r16_timingQualityResolution_r16(3);
   private static NR_TimingQuality_r16_timingQualityResolution_r16 __undefined = new NR_TimingQuality_r16_timingQualityResolution_r16(-999);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    */
   protected NR_TimingQuality_r16_timingQualityResolution_r16 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for mdot1.
    */
   public static NR_TimingQuality_r16_timingQualityResolution_r16 mdot1() {
      return _mdot1;
   }

   /**
    * Singleton accessor method for m1.
    */
   public static NR_TimingQuality_r16_timingQualityResolution_r16 m1() {
      return _m1;
   }

   /**
    * Singleton accessor method for m10.
    */
   public static NR_TimingQuality_r16_timingQualityResolution_r16 m10() {
      return _m10;
   }

   /**
    * Singleton accessor method for m30.
    */
   public static NR_TimingQuality_r16_timingQualityResolution_r16 m30() {
      return _m30;
   }

   /** Undefined value. */
   protected static NR_TimingQuality_r16_timingQualityResolution_r16 __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return NR_TimingQuality_r16_timingQualityResolution_r16.valueOf( NR_TimingQuality_r16_timingQualityResolution_r16.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static NR_TimingQuality_r16_timingQualityResolution_r16 valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return mdot1();
         case 1: return m1();
         case 2: return m10();
         case 3: return m30();
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
         case 0: ui = 0; /* mdot1 */break;
         case 1: ui = 1; /* m1 */break;
         case 2: ui = 2; /* m10 */break;
         case 3: ui = 3; /* m30 */break;
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
