/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 extends Asn1BitString {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "BIT STRING";
   }

   public final static int rssi = 0;

   /**
    * This constructor creates an empty bit string that can be used in 
    * a decode method call to receive a bit string value.
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 () {
      super();
      value = new byte [1];
      trimZeroBits = true;
   }

   /**
    * This constructor initializes the bit string value with the 
    * given bytes, using all 8 bits of every byte.
    *
    * @param data       Binary bit string contents
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 (byte[] data) {
      this( data.length * 8, data);
   }

   /**
    * This constructor initializes the bit string value with the 
    * given number of bits and data.
    *
    * @param numbits_   Number of bits
    * @param data       Binary bit string contents
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 (int numbits_, byte[] data) 
      throws Asn1Exception
   {
      super (numbits_, data);
      if (!((getLength() >= 1 && getLength() <= 8))) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

      trimZeroBits = true;
   }

   /**
    * This constructor initializes the bit string value from the given 
    * boolean array.  Each array position corresponds to a bit in the 
    * bit string.
    *
    * @param bitValues  The boolean array
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 (boolean[] bitValues) 
      throws Asn1Exception
   {
      super (bitValues);
      if (!((getLength() >= 1 && getLength() <= 8))) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

      trimZeroBits = true;
   }

   /**
    * This constructor parses the given ASN.1 value text (either a 
    * binary or hex data string) and assigns the values to the internal
    * bit string.
    *
    * Examples of valid value formats are as follows:
    * Binary string:    "'11010010111001'B"
    * Hex string:       "'0fa56920014abc'H"
    *
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 (String value_) 
      throws Asn1Exception
   {
      super (value_);
      if (!((getLength() >= 1 && getLength() <= 8))) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

      trimZeroBits = true;
   }

   /**
    * This constructor initializes the bit string value from the given 
    * BitSet object.  The logical length of the BitSet is used 
    * (i.e. position of last set bit).
    *
    * @param bitSet  Java BitSet object
    */
   public TBS_RequestLocationInformation_r13_mbsRequestedMeasurements_r14 (java.util.BitSet bitSet) 
      throws Asn1Exception
   {
      super (bitSet);
      if (!((getLength() >= 1 && getLength() <= 8))) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

      trimZeroBits = true;
   }

   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      super.decode (buffer, 1, 8);
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      super.encode (buffer, 1, 8);
   }

}
