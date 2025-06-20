/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.lpp.LPP_PDU_Definitions;

import com.objsys.asn1j.runtime.*;

public class ProvideAssistanceData extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_LPP_PDU_DefinitionsRtkey._rtkey);
      Asn1Type._setLicLocation(_LPP_PDU_DefinitionsRtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "ProvideAssistanceData";
   }

   protected ProvideAssistanceData_criticalExtensions criticalExtensions;

   public ProvideAssistanceData () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public ProvideAssistanceData (
      ProvideAssistanceData_criticalExtensions criticalExtensions_
   ) {
      super();
      setCriticalExtensions (criticalExtensions_);
   }

   public void init () {
      criticalExtensions = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof ProvideAssistanceData) ) return false;

      ProvideAssistanceData rhs = (ProvideAssistanceData) obj;

      if (criticalExtensions == null) {
         if (rhs.criticalExtensions != null) return false;
      }
      else {
         if (!criticalExtensions.equals(rhs.criticalExtensions)) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      int __code = 1;

      if (criticalExtensions != null) __code = 31*__code + criticalExtensions.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for CriticalExtensions
    */
   public ProvideAssistanceData_criticalExtensions getCriticalExtensions () {
      return criticalExtensions;
   }

   public void setCriticalExtensions (ProvideAssistanceData_criticalExtensions value) {
      this.criticalExtensions = value;
   }

   public int getElementCount() { return 1; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return criticalExtensions;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "criticalExtensions";
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // decode criticalExtensions

      buffer.getContext().eventDispatcher.startElement("criticalExtensions", -1);

      criticalExtensions = new ProvideAssistanceData_criticalExtensions();
      criticalExtensions.decode (buffer);
      buffer.getContext().eventDispatcher.endElement("criticalExtensions", -1);

   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // encode criticalExtensions

      if (criticalExtensions != null) {
         buffer.getContext().eventDispatcher.startElement("criticalExtensions", -1);

         criticalExtensions.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("criticalExtensions", -1);
      }
      else throw new Asn1MissingRequiredException ("criticalExtensions");

   }

}
