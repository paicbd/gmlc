/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class DBDS_CorrectionList_r12 extends Asn1SeqOf {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "DBDS-CorrectionList-r12";
   }

   protected DBDS_CorrectionElement_r12[] elements;

   public DBDS_CorrectionList_r12 () {
      elements = null;
   }

   /**
    * This constructor initializes the internal array to hold the 
    * given number of elements.  The element values must be manually 
    * populated.
    */
   public DBDS_CorrectionList_r12 (int numRecords) {
      elements = new DBDS_CorrectionElement_r12 [numRecords];
   }

   /**
    * This constructor initializes the internal array to hold the 
    * given the array.  
    */
   public DBDS_CorrectionList_r12 (DBDS_CorrectionElement_r12[] elements_) {
      elements = elements_;
   }

   /** Returns the number of elements in the SEQUENCE OF. */
   public int getLength() {
      return elements.length;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof DBDS_CorrectionList_r12) ) return false;
      DBDS_CorrectionList_r12 rhs = (DBDS_CorrectionList_r12) obj;
      DBDS_CorrectionElement_r12[] rhsElements = rhs.elements;

      if (elements == null && rhsElements != null) return false;

      if(elements != null && rhsElements == null) return false;

      if(elements == null && rhsElements == null) return true;

      if(elements.length != rhsElements.length) return false;

      for(int i = 0; i< elements.length; i++) {
         if (elements[i] == null && rhsElements[i] != null) return false;
         if (rhsElements[i] == null && elements[i] != null) return false;
         if (rhsElements[i] == null && elements[i] == null) continue;

         if(!elements[i].equals(rhsElements[i])) return false;
      }
      return true;
   }

   public int hashCode() {
      int __code = 1;

      if (elements != null) {
         for (int i = 0; i < elements.length; i ++) {
            if (elements[i] != null)__code = 31*__code + elements[i].hashCode();
         }
      }

      return __code;
   }

   /**
    * Accessor/mutator methods for elements
    */
   public DBDS_CorrectionElement_r12[] getElements () {
      return elements;
   }
   public void setElements (DBDS_CorrectionElement_r12[] value) {
      elements = value;
   }

   public Asn1Type[] getElementValues() { return elements; }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // decode length determinant

      buffer.setSizeConstraint (1, 64);
      int numElements = (int) buffer.decodeLength();

      elements = new DBDS_CorrectionElement_r12 [numElements];

      // decode elements

      for (int i = 0; i < elements.length; i++) {
         buffer.getContext().eventDispatcher.startElement("elements", i);

         elements[i] = new DBDS_CorrectionElement_r12();
         elements[i].decode (buffer);
         buffer.getContext().eventDispatcher.endElement("elements", i);

      }

   }

   public void decodeContent (Asn1PerDecodeBuffer buffer, long numElements)
      throws Asn1Exception, java.io.IOException
   {
   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // encode length determinant

      buffer.encodeLength (elements.length, 1, 64);

      // encode elements

      for (int i = 0; i < elements.length; i++) {
         buffer.getContext().eventDispatcher.startElement("element", i);

         elements[i].encode (buffer);

         buffer.getContext().eventDispatcher.endElement("element", i);
      }
   }

}
