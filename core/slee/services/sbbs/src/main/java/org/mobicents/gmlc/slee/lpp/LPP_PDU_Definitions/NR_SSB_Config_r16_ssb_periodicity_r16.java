/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_SSB_Config_r16_ssb_periodicity_r16 extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _MS5 = 0;
   public static final int _MS10 = 1;
   public static final int _MS20 = 2;
   public static final int _MS40 = 3;
   public static final int _MS80 = 4;
   public static final int _MS160 = 5;

   // Singleton instances of NR_SSB_Config_r16_ssb_periodicity_r16
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms5 = new NR_SSB_Config_r16_ssb_periodicity_r16(0);
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms10 = new NR_SSB_Config_r16_ssb_periodicity_r16(1);
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms20 = new NR_SSB_Config_r16_ssb_periodicity_r16(2);
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms40 = new NR_SSB_Config_r16_ssb_periodicity_r16(3);
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms80 = new NR_SSB_Config_r16_ssb_periodicity_r16(4);
   protected static final NR_SSB_Config_r16_ssb_periodicity_r16 _ms160 = new NR_SSB_Config_r16_ssb_periodicity_r16(5);
   private static NR_SSB_Config_r16_ssb_periodicity_r16 __undefined = new NR_SSB_Config_r16_ssb_periodicity_r16(-999);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    *   4
    *   5
    */
   protected NR_SSB_Config_r16_ssb_periodicity_r16 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for ms5.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms5() {
      return _ms5;
   }

   /**
    * Singleton accessor method for ms10.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms10() {
      return _ms10;
   }

   /**
    * Singleton accessor method for ms20.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms20() {
      return _ms20;
   }

   /**
    * Singleton accessor method for ms40.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms40() {
      return _ms40;
   }

   /**
    * Singleton accessor method for ms80.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms80() {
      return _ms80;
   }

   /**
    * Singleton accessor method for ms160.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 ms160() {
      return _ms160;
   }

   /** Undefined value. */
   protected static NR_SSB_Config_r16_ssb_periodicity_r16 __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return NR_SSB_Config_r16_ssb_periodicity_r16.valueOf( NR_SSB_Config_r16_ssb_periodicity_r16.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static NR_SSB_Config_r16_ssb_periodicity_r16 valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return ms5();
         case 1: return ms10();
         case 2: return ms20();
         case 3: return ms40();
         case 4: return ms80();
         case 5: return ms160();
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
         ui = (int) buffer.decodeConsWholeNumber (6);

         switch (ui) {
            case 0: ret =  0; break;
            case 1: ret =  1; break;
            case 2: ret =  2; break;
            case 3: ret =  3; break;
            case 4: ret =  4; break;
            case 5: ret =  5; break;
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
         case 0: ui = 0; /* ms5 */break;
         case 1: ui = 1; /* ms10 */break;
         case 2: ui = 2; /* ms20 */break;
         case 3: ui = 3; /* ms40 */break;
         case 4: ui = 4; /* ms80 */break;
         case 5: ui = 5; /* ms160 */break;
         default:
            ui = (int) value;
            extbit = true;
      }

      buffer.encodeBit (extbit, "extbit");

      if (extbit) {
         buffer.encodeSmallNonNegWholeNumber (ui);
      }
      else {
         buffer.encodeConsWholeNumber (ui, 6);
      }
   }

}
