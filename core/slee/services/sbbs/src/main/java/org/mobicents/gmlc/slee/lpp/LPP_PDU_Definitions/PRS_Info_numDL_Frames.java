/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class PRS_Info_numDL_Frames extends Asn1Enumerated {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ENUMERATED";
   }

   // Integer constants for switch-case
   public static final int _SF_1 = 0;
   public static final int _SF_2 = 1;
   public static final int _SF_4 = 2;
   public static final int _SF_6 = 3;
   public static final int _SF_ADD_V1420 = 4;

   // Singleton instances of PRS_Info_numDL_Frames
   protected static final PRS_Info_numDL_Frames _sf_1 = new PRS_Info_numDL_Frames(0);
   protected static final PRS_Info_numDL_Frames _sf_2 = new PRS_Info_numDL_Frames(1);
   protected static final PRS_Info_numDL_Frames _sf_4 = new PRS_Info_numDL_Frames(2);
   protected static final PRS_Info_numDL_Frames _sf_6 = new PRS_Info_numDL_Frames(3);
   protected static final PRS_Info_numDL_Frames _sf_add_v1420 = new PRS_Info_numDL_Frames(4);
   private static PRS_Info_numDL_Frames __undefined = new PRS_Info_numDL_Frames(-999);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    *   4
    */
   protected PRS_Info_numDL_Frames (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for sf_1.
    */
   public static PRS_Info_numDL_Frames sf_1() {
      return _sf_1;
   }

   /**
    * Singleton accessor method for sf_2.
    */
   public static PRS_Info_numDL_Frames sf_2() {
      return _sf_2;
   }

   /**
    * Singleton accessor method for sf_4.
    */
   public static PRS_Info_numDL_Frames sf_4() {
      return _sf_4;
   }

   /**
    * Singleton accessor method for sf_6.
    */
   public static PRS_Info_numDL_Frames sf_6() {
      return _sf_6;
   }

   /**
    * Singleton accessor method for sf_add_v1420.
    */
   public static PRS_Info_numDL_Frames sf_add_v1420() {
      return _sf_add_v1420;
   }

   /** Undefined value. */
   protected static PRS_Info_numDL_Frames __undefined_() { return __undefined; }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return PRS_Info_numDL_Frames.valueOf( PRS_Info_numDL_Frames.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static PRS_Info_numDL_Frames valueOf (int _value)
   {
      switch ((int)_value) {
         case 0: return sf_1();
         case 1: return sf_2();
         case 2: return sf_4();
         case 3: return sf_6();
         case 4: return sf_add_v1420();
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
         case 0: ui = 0; /* sf_1 */break;
         case 1: ui = 1; /* sf_2 */break;
         case 2: ui = 2; /* sf_4 */break;
         case 3: ui = 3; /* sf_6 */break;
         case 4: ui = 0; /* sf_add_v1420 */extbit = true; break;
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
