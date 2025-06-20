/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NPRS_Info_r14_partB_r14_nprs_Period_r14 extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _MS160 = 0;
   public static final int _MS320 = 1;
   public static final int _MS640 = 2;
   public static final int _MS1280 = 3;
   public static final int _MS2560_V1510 = 4;

   // Singleton instances of NPRS_Info_r14_partB_r14_nprs_Period_r14
   protected static final NPRS_Info_r14_partB_r14_nprs_Period_r14 _ms160 = new NPRS_Info_r14_partB_r14_nprs_Period_r14(0);
   protected static final NPRS_Info_r14_partB_r14_nprs_Period_r14 _ms320 = new NPRS_Info_r14_partB_r14_nprs_Period_r14(1);
   protected static final NPRS_Info_r14_partB_r14_nprs_Period_r14 _ms640 = new NPRS_Info_r14_partB_r14_nprs_Period_r14(2);
   protected static final NPRS_Info_r14_partB_r14_nprs_Period_r14 _ms1280 = new NPRS_Info_r14_partB_r14_nprs_Period_r14(3);
   protected static final NPRS_Info_r14_partB_r14_nprs_Period_r14 _ms2560_v1510 = new NPRS_Info_r14_partB_r14_nprs_Period_r14(4);
   private static NPRS_Info_r14_partB_r14_nprs_Period_r14 __undefined = new NPRS_Info_r14_partB_r14_nprs_Period_r14(-999);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    *   4
    */
   protected NPRS_Info_r14_partB_r14_nprs_Period_r14 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for ms160.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 ms160() {
      return _ms160;
   }

   /**
    * Singleton accessor method for ms320.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 ms320() {
      return _ms320;
   }

   /**
    * Singleton accessor method for ms640.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 ms640() {
      return _ms640;
   }

   /**
    * Singleton accessor method for ms1280.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 ms1280() {
      return _ms1280;
   }

   /**
    * Singleton accessor method for ms2560_v1510.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 ms2560_v1510() {
      return _ms2560_v1510;
   }

   /** Undefined value. */
   protected static NPRS_Info_r14_partB_r14_nprs_Period_r14 __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return NPRS_Info_r14_partB_r14_nprs_Period_r14.valueOf( NPRS_Info_r14_partB_r14_nprs_Period_r14.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static NPRS_Info_r14_partB_r14_nprs_Period_r14 valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return ms160();
         case 1: return ms320();
         case 2: return ms640();
         case 3: return ms1280();
         case 4: return ms2560_v1510();
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

         switch (ui) {
            case 0: ret = 4; break;
            default: ret = 0x7fffffff; break;
         }
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
         case 0: ui = 0; /* ms160 */break;
         case 1: ui = 1; /* ms320 */break;
         case 2: ui = 2; /* ms640 */break;
         case 3: ui = 3; /* ms1280 */break;
         case 4: ui = 0; /* ms2560_v1510 */extbit = true; break;
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
