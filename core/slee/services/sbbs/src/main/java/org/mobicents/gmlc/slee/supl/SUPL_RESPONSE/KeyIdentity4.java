/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.SUPL_RESPONSE;

import com.objsys.asn1j.runtime.*;

public class KeyIdentity4 extends Asn1BitString {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_SUPL_RESPONSERtkey._rtkey);
      Asn1Type._setLicLocation(_SUPL_RESPONSERtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "KeyIdentity4";
   }

   /**
    * This constructor creates an empty bit string that can be used in 
    * a decode method call to receive a bit string value.
    */
   public KeyIdentity4 () {
      super();
      value = new byte [16];
   }

   /**
    * This constructor initializes the bit string value with the 
    * given bytes, using all 8 bits of every byte.
    *
    * @param data       Binary bit string contents
    */
   public KeyIdentity4 (byte[] data) {
      this( data.length * 8, data);
   }

   /**
    * This constructor initializes the bit string value with the 
    * given number of bits and data.
    *
    * @param numbits_   Number of bits
    * @param data       Binary bit string contents
    */
   public KeyIdentity4 (int numbits_, byte[] data) 
      throws Asn1Exception
   {
      super (numbits_, data);
      if (!(getLength() == 128)) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

   }

   /**
    * This constructor initializes the bit string value from the given 
    * boolean array.  Each array position corresponds to a bit in the 
    * bit string.
    *
    * @param bitValues  The boolean array
    */
   public KeyIdentity4 (boolean[] bitValues) 
      throws Asn1Exception
   {
      super (bitValues);
      if (!(getLength() == 128)) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

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
   public KeyIdentity4 (String value_) 
      throws Asn1Exception
   {
      super (value_);
      if (!(getLength() == 128)) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

   }

   /**
    * This constructor initializes the bit string value from the given 
    * BitSet object.  The logical length of the BitSet is used 
    * (i.e. position of last set bit).
    *
    * @param bitSet  Java BitSet object
    */
   public KeyIdentity4 (java.util.BitSet bitSet) 
      throws Asn1Exception
   {
      super (bitSet);
      if (!(getLength() == 128)) {
         throw new Asn1ConsVioException ("getLength()", getLength());
      }

   }

   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      super.decode (buffer, 128, 128);
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      super.encode (buffer, 128, 128);
   }

}
