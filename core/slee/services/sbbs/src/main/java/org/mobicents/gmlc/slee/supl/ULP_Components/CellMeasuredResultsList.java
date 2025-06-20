/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.ULP_Components;

import com.objsys.asn1j.runtime.*;

public class CellMeasuredResultsList extends Asn1SeqOf {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_ULP_ComponentsRtkey._rtkey);
      Asn1Type._setLicLocation(_ULP_ComponentsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "CellMeasuredResultsList";
   }

   protected CellMeasuredResults[] elements;

   public CellMeasuredResultsList () {
      elements = null;
   }

   /**
    * This constructor initializes the internal array to hold the 
    * given number of elements.  The element values must be manually 
    * populated.
    */
   public CellMeasuredResultsList (int numRecords) {
      elements = new CellMeasuredResults [numRecords];
   }

   /**
    * This constructor initializes the internal array to hold the 
    * given the array.  
    */
   public CellMeasuredResultsList (CellMeasuredResults[] elements_) {
      elements = elements_;
   }

   /** Returns the number of elements in the SEQUENCE OF. */
   public int getLength() {
      return elements.length;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof CellMeasuredResultsList) ) return false;
      CellMeasuredResultsList rhs = (CellMeasuredResultsList) obj;
      CellMeasuredResults[] rhsElements = rhs.elements;

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
   public CellMeasuredResults[] getElements () {
      return elements;
   }
   public void setElements (CellMeasuredResults[] value) {
      elements = value;
   }

   public Asn1Type[] getElementValues() { return elements; }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // decode length determinant

      buffer.setSizeConstraint (1, 32);
      int numElements = (int) buffer.decodeLength();

      elements = new CellMeasuredResults [numElements];

      // decode elements

      for (int i = 0; i < elements.length; i++) {
         buffer.getContext().eventDispatcher.startElement("elements", i);

         elements[i] = new CellMeasuredResults();
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

      buffer.encodeLength (elements.length, 1, 32);

      // encode elements

      for (int i = 0; i < elements.length; i++) {
         buffer.getContext().eventDispatcher.startElement("element", i);

         elements[i].encode (buffer);

         buffer.getContext().eventDispatcher.endElement("element", i);
      }
   }

}
