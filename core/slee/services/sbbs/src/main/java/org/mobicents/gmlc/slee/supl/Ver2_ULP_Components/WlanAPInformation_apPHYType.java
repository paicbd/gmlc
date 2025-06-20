/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.Ver2_ULP_Components;

import com.objsys.asn1j.runtime.*;

public class WlanAPInformation_apPHYType extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_Ver2_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_Ver2_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _UNKNOWN = 0;
   public static final int _ANY = 1;
   public static final int _FHSS = 2;
   public static final int _DSSS = 3;
   public static final int _IRBASEBAND = 4;
   public static final int _OFDM = 5;
   public static final int _HRDSSS = 6;
   public static final int _ERP = 7;
   public static final int _HT = 8;
   public static final int _IHV = 9;

   // Singleton instances of WlanAPInformation_apPHYType
   protected static final WlanAPInformation_apPHYType _unknown = new WlanAPInformation_apPHYType(0);
   protected static final WlanAPInformation_apPHYType _any = new WlanAPInformation_apPHYType(1);
   protected static final WlanAPInformation_apPHYType _fhss = new WlanAPInformation_apPHYType(2);
   protected static final WlanAPInformation_apPHYType _dsss = new WlanAPInformation_apPHYType(3);
   protected static final WlanAPInformation_apPHYType _irbaseband = new WlanAPInformation_apPHYType(4);
   protected static final WlanAPInformation_apPHYType _ofdm = new WlanAPInformation_apPHYType(5);
   protected static final WlanAPInformation_apPHYType _hrdsss = new WlanAPInformation_apPHYType(6);
   protected static final WlanAPInformation_apPHYType _erp = new WlanAPInformation_apPHYType(7);
   protected static final WlanAPInformation_apPHYType _ht = new WlanAPInformation_apPHYType(8);
   protected static final WlanAPInformation_apPHYType _ihv = new WlanAPInformation_apPHYType(9);
   private static WlanAPInformation_apPHYType __undefined = new WlanAPInformation_apPHYType(-999);

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
    *   7
    *   8
    *   9
    */
   protected WlanAPInformation_apPHYType (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for unknown.
    */
   public static WlanAPInformation_apPHYType unknown() {
      return _unknown;
   }

   /**
    * Singleton accessor method for any.
    */
   public static WlanAPInformation_apPHYType any() {
      return _any;
   }

   /**
    * Singleton accessor method for fhss.
    */
   public static WlanAPInformation_apPHYType fhss() {
      return _fhss;
   }

   /**
    * Singleton accessor method for dsss.
    */
   public static WlanAPInformation_apPHYType dsss() {
      return _dsss;
   }

   /**
    * Singleton accessor method for irbaseband.
    */
   public static WlanAPInformation_apPHYType irbaseband() {
      return _irbaseband;
   }

   /**
    * Singleton accessor method for ofdm.
    */
   public static WlanAPInformation_apPHYType ofdm() {
      return _ofdm;
   }

   /**
    * Singleton accessor method for hrdsss.
    */
   public static WlanAPInformation_apPHYType hrdsss() {
      return _hrdsss;
   }

   /**
    * Singleton accessor method for erp.
    */
   public static WlanAPInformation_apPHYType erp() {
      return _erp;
   }

   /**
    * Singleton accessor method for ht.
    */
   public static WlanAPInformation_apPHYType ht() {
      return _ht;
   }

   /**
    * Singleton accessor method for ihv.
    */
   public static WlanAPInformation_apPHYType ihv() {
      return _ihv;
   }

   /** Undefined value. */
   protected static WlanAPInformation_apPHYType __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return WlanAPInformation_apPHYType.valueOf( WlanAPInformation_apPHYType.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static WlanAPInformation_apPHYType valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return unknown();
         case 1: return any();
         case 2: return fhss();
         case 3: return dsss();
         case 4: return irbaseband();
         case 5: return ofdm();
         case 6: return hrdsss();
         case 7: return erp();
         case 8: return ht();
         case 9: return ihv();
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
         ui = (int) buffer.decodeConsWholeNumber (10);

         switch (ui) {
            case 0: ret =  0; break;
            case 1: ret =  1; break;
            case 2: ret =  2; break;
            case 3: ret =  3; break;
            case 4: ret =  4; break;
            case 5: ret =  5; break;
            case 6: ret =  6; break;
            case 7: ret =  7; break;
            case 8: ret =  8; break;
            case 9: ret =  9; break;
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
         case 0: ui = 0; /* unknown */break;
         case 1: ui = 1; /* any */break;
         case 2: ui = 2; /* fhss */break;
         case 3: ui = 3; /* dsss */break;
         case 4: ui = 4; /* irbaseband */break;
         case 5: ui = 5; /* ofdm */break;
         case 6: ui = 6; /* hrdsss */break;
         case 7: ui = 7; /* erp */break;
         case 8: ui = 8; /* ht */break;
         case 9: ui = 9; /* ihv */break;
         default:
            ui = (int) value;
            extbit = true;
      }

      buffer.encodeBit (extbit, "extbit");

      if (extbit) {
         buffer.encodeSmallNonNegWholeNumber (ui);
      }
      else {
         buffer.encodeConsWholeNumber (ui, 10);
      }
   }

}
