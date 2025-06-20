/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _N1 = 0;
   public static final int _N2 = 1;
   public static final int _N4 = 2;
   public static final int _N8 = 3;
   public static final int _N16 = 4;
   public static final int _N32 = 5;
   public static final int _N64 = 6;

   // Singleton instances of SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n1 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(0);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n2 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(1);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n4 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(2);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n8 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(3);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n16 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(4);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n32 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(5);
   protected static final SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 _n64 = new SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16(6);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    *   4
    *   5
    *   6
    */
   protected SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for n1.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n1() {
      return _n1;
   }

   /**
    * Singleton accessor method for n2.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n2() {
      return _n2;
   }

   /**
    * Singleton accessor method for n4.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n4() {
      return _n4;
   }

   /**
    * Singleton accessor method for n8.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n8() {
      return _n8;
   }

   /**
    * Singleton accessor method for n16.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n16() {
      return _n16;
   }

   /**
    * Singleton accessor method for n32.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n32() {
      return _n32;
   }

   /**
    * Singleton accessor method for n64.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 n64() {
      return _n64;
   }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16.valueOf( SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static SRS_PosResourcesPerBand_r16_maxNumberAP_SRS_PosResourcesPerBWP_r16 valueOf (int _value)
      throws Asn1InvalidEnumException
   {
      switch ((int)_value) {
         case 0: return n1();
         case 1: return n2();
         case 2: return n4();
         case 3: return n8();
         case 4: return n16();
         case 5: return n32();
         case 6: return n64();
         default: throw new Asn1InvalidEnumException (_value);
      }
   }

   public static int decodeEnumValue (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int ui;  // enum index
      int ret; // return value
      ui = (int) buffer.decodeConsWholeNumber (7);

      switch (ui) {
         case 0: ret =  0; break;
         case 1: ret =  1; break;
         case 2: ret =  2; break;
         case 3: ret =  3; break;
         case 4: ret =  4; break;
         case 5: ret =  5; break;
         case 6: ret =  6; break;
         default: throw new Asn1InvalidEnumException (ui);
      }

      buffer.setTypeCode (Asn1Type.ENUMERATED);
      return ret;
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int ui;  // enum index
      switch ((int)value) {
         case 0: ui = 0; /* n1 */break;
         case 1: ui = 1; /* n2 */break;
         case 2: ui = 2; /* n4 */break;
         case 3: ui = 3; /* n8 */break;
         case 4: ui = 4; /* n16 */break;
         case 5: ui = 5; /* n32 */break;
         case 6: ui = 6; /* n64 */break;
         default:
            throw new Asn1InvalidEnumException (value);
      }

      buffer.encodeConsWholeNumber (ui, 7);
   }

}
