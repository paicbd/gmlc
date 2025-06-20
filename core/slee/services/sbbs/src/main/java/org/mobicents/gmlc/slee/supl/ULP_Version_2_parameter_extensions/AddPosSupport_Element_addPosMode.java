/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Version_2_parameter_extensions;

import com.objsys.asn1j.runtime.*;

public class AddPosSupport_Element_addPosMode extends Asn1BitString {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_Version_2_parameter_extensionsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_Version_2_parameter_extensionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "BIT STRING";
   }

   public final static int standalone = 0;
   public final static int setBased = 1;
   public final static int setAssisted = 2;

   /**
    * This constructor creates an empty bit string that can be used in 
    * a decode method call to receive a bit string value.
    */
   public AddPosSupport_Element_addPosMode () {
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
   public AddPosSupport_Element_addPosMode (byte[] data) {
      this( data.length * 8, data);
   }

   /**
    * This constructor initializes the bit string value with the 
    * given number of bits and data.
    *
    * @param numbits_   Number of bits
    * @param data       Binary bit string contents
    */
   public AddPosSupport_Element_addPosMode (int numbits_, byte[] data) 
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
   public AddPosSupport_Element_addPosMode (boolean[] bitValues) 
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
   public AddPosSupport_Element_addPosMode (String value_) 
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
   public AddPosSupport_Element_addPosMode (java.util.BitSet bitSet) 
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
