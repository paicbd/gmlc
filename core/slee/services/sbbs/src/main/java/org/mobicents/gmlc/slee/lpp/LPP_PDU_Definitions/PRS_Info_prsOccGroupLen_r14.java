/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class PRS_Info_prsOccGroupLen_r14 extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _G2 = 0;
   public static final int _G4 = 1;
   public static final int _G8 = 2;
   public static final int _G16 = 3;
   public static final int _G32 = 4;
   public static final int _G64 = 5;
   public static final int _G128 = 6;

   // Singleton instances of PRS_Info_prsOccGroupLen_r14
   protected static final PRS_Info_prsOccGroupLen_r14 _g2 = new PRS_Info_prsOccGroupLen_r14(0);
   protected static final PRS_Info_prsOccGroupLen_r14 _g4 = new PRS_Info_prsOccGroupLen_r14(1);
   protected static final PRS_Info_prsOccGroupLen_r14 _g8 = new PRS_Info_prsOccGroupLen_r14(2);
   protected static final PRS_Info_prsOccGroupLen_r14 _g16 = new PRS_Info_prsOccGroupLen_r14(3);
   protected static final PRS_Info_prsOccGroupLen_r14 _g32 = new PRS_Info_prsOccGroupLen_r14(4);
   protected static final PRS_Info_prsOccGroupLen_r14 _g64 = new PRS_Info_prsOccGroupLen_r14(5);
   protected static final PRS_Info_prsOccGroupLen_r14 _g128 = new PRS_Info_prsOccGroupLen_r14(6);
   private static PRS_Info_prsOccGroupLen_r14 __undefined = new PRS_Info_prsOccGroupLen_r14(-999);

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
   protected PRS_Info_prsOccGroupLen_r14 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for g2.
    */
   public static PRS_Info_prsOccGroupLen_r14 g2() {
      return _g2;
   }

   /**
    * Singleton accessor method for g4.
    */
   public static PRS_Info_prsOccGroupLen_r14 g4() {
      return _g4;
   }

   /**
    * Singleton accessor method for g8.
    */
   public static PRS_Info_prsOccGroupLen_r14 g8() {
      return _g8;
   }

   /**
    * Singleton accessor method for g16.
    */
   public static PRS_Info_prsOccGroupLen_r14 g16() {
      return _g16;
   }

   /**
    * Singleton accessor method for g32.
    */
   public static PRS_Info_prsOccGroupLen_r14 g32() {
      return _g32;
   }

   /**
    * Singleton accessor method for g64.
    */
   public static PRS_Info_prsOccGroupLen_r14 g64() {
      return _g64;
   }

   /**
    * Singleton accessor method for g128.
    */
   public static PRS_Info_prsOccGroupLen_r14 g128() {
      return _g128;
   }

   /** Undefined value. */
   protected static PRS_Info_prsOccGroupLen_r14 __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return PRS_Info_prsOccGroupLen_r14.valueOf( PRS_Info_prsOccGroupLen_r14.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static PRS_Info_prsOccGroupLen_r14 valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return g2();
         case 1: return g4();
         case 2: return g8();
         case 3: return g16();
         case 4: return g32();
         case 5: return g64();
         case 6: return g128();
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
         case 0: ui = 0; /* g2 */break;
         case 1: ui = 1; /* g4 */break;
         case 2: ui = 2; /* g8 */break;
         case 3: ui = 3; /* g16 */break;
         case 4: ui = 4; /* g32 */break;
         case 5: ui = 5; /* g64 */break;
         case 6: ui = 6; /* g128 */break;
         default:
            ui = (int) value;
            extbit = true;
      }

      buffer.encodeBit (extbit, "extbit");

      if (extbit) {
         buffer.encodeSmallNonNegWholeNumber (ui);
      }
      else {
         buffer.encodeConsWholeNumber (ui, 7);
      }
   }

}
