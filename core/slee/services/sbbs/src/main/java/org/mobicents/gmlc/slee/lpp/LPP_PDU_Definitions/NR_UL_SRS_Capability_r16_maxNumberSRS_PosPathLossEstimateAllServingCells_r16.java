/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 extends Asn1Enumerated {
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
   public static final int _N4 = 1;
   public static final int _N8 = 2;
   public static final int _N16 = 3;

   // Singleton instances of NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16
   protected static final NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 _n1 = new NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16(0);
   protected static final NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 _n4 = new NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16(1);
   protected static final NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 _n8 = new NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16(2);
   protected static final NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 _n16 = new NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16(3);

   /**
    * Enumerated constructor: this object must be initialized with one 
    * of the following values:
    *   0
    *   1
    *   2
    *   3
    */
   protected NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 (int value_) {
      super (value_);
   }

   /**
    * Singleton accessor method for n1.
    */
   public static NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 n1() {
      return _n1;
   }

   /**
    * Singleton accessor method for n4.
    */
   public static NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 n4() {
      return _n4;
   }

   /**
    * Singleton accessor method for n8.
    */
   public static NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 n8() {
      return _n8;
   }

   /**
    * Singleton accessor method for n16.
    */
   public static NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 n16() {
      return _n16;
   }

   public static final Decoder DECODER = new Decoder();

   public static class Decoder implements Asn1PerDecoder{

      public Asn1Type decode(Asn1PerDecodeBuffer buffer) throws Asn1Exception, java.io.IOException
      {
         return NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16.valueOf( NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16.decodeEnumValue( buffer ) );
      }
   }

   /**
    * Returns the instance associated with the value passed.
    * If the value passed is invalid, an exception is thrown.
    */
   public static NR_UL_SRS_Capability_r16_maxNumberSRS_PosPathLossEstimateAllServingCells_r16 valueOf (int _value)
      throws Asn1InvalidEnumException
   {
      switch ((int)_value) {
         case 0: return n1();
         case 1: return n4();
         case 2: return n8();
         case 3: return n16();
         default: throw new Asn1InvalidEnumException (_value);
      }
   }

   public static int decodeEnumValue (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      int ui;  // enum index
      int ret; // return value
      ui = (int) buffer.decodeConsWholeNumber (4);

      switch (ui) {
         case 0: ret =  0; break;
         case 1: ret =  1; break;
         case 2: ret =  2; break;
         case 3: ret =  3; break;
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
         case 1: ui = 1; /* n4 */break;
         case 2: ui = 2; /* n8 */break;
         case 3: ui = 3; /* n16 */break;
         default:
            throw new Asn1InvalidEnumException (value);
      }

      buffer.encodeConsWholeNumber (ui, 4);
   }

}
