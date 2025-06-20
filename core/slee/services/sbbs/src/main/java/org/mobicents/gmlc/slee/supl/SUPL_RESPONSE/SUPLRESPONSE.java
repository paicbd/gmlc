/**
 * This file was generated by the Objective Systems ASN1C Compiler
 * (http://www.obj-sys.com).  Version: 7.5.0, Date: 07-May-2021.
 */
package org.mobicents.gmlc.slee.supl.SUPL_RESPONSE;

import com.objsys.asn1j.runtime.*;
import org.mobicents.gmlc.slee.supl.ULP_Components.PosMethod;
import org.mobicents.gmlc.slee.supl.ULP_Components.SLPAddress;
import org.mobicents.gmlc.slee.supl.ULP_Version_2_message_extensions.Ver2_SUPL_RESPONSE_extension;

public class SUPLRESPONSE extends Asn1Seq {
   private static final long serialVersionUID = 55;
   static {
      _setKey (_SUPL_RESPONSERtkey._rtkey);
      Asn1Type._setLicLocation(_SUPL_RESPONSERtkey._licLocation);
   }

   public String getAsn1TypeName()  {
      return "SUPLRESPONSE";
   }

   protected PosMethod posMethod = null;
   protected SLPAddress sLPAddress;  // optional
   protected SETAuthKey sETAuthKey;  // optional
   protected KeyIdentity4 keyIdentity4;  // optional
   protected Ver2_SUPL_RESPONSE_extension ver2_SUPL_RESPONSE_extension;  // optional
   protected Asn1OpenExt extElem1;

   public SUPLRESPONSE () {
      super();
      init();
   }

   /**
    * This constructor sets all elements to references to the 
    * given objects
    */
   public SUPLRESPONSE (
      PosMethod posMethod_,
      SLPAddress sLPAddress_,
      SETAuthKey sETAuthKey_,
      KeyIdentity4 keyIdentity4_,
      Ver2_SUPL_RESPONSE_extension ver2_SUPL_RESPONSE_extension_
   ) throws Asn1Exception {
      super();
      setPosMethod (posMethod_);
      setSLPAddress (sLPAddress_);
      setSETAuthKey (sETAuthKey_);
      setKeyIdentity4 (keyIdentity4_);
      setVer2_SUPL_RESPONSE_extension (ver2_SUPL_RESPONSE_extension_);
   }

   /**
    * This constructor is for required elements only.  It sets 
    * all elements to references to the given objects
    */
   public SUPLRESPONSE (
      PosMethod posMethod_
   ) throws Asn1Exception {
      super();
      setPosMethod (posMethod_);
   }

   public void init () {
      posMethod = null;
      sLPAddress = null;
      sETAuthKey = null;
      keyIdentity4 = null;
      ver2_SUPL_RESPONSE_extension = null;
      extElem1 = null;
   }

   public boolean equals( Object obj ) {
      if ( this == obj ) return true;
      if ( !(obj instanceof SUPLRESPONSE) ) return false;

      SUPLRESPONSE rhs = (SUPLRESPONSE) obj;

      if (posMethod == null) {
         if (rhs.posMethod != null) return false;
      }
      else {
         if (!posMethod.equals(rhs.posMethod)) {
            return false;
         }
      }

      if (sLPAddress == null) {
         if (rhs.sLPAddress != null) return false;
      }
      else {
         if (!sLPAddress.equals(rhs.sLPAddress)) {
            return false;
         }
      }

      if (sETAuthKey == null) {
         if (rhs.sETAuthKey != null) return false;
      }
      else {
         if (!sETAuthKey.equals(rhs.sETAuthKey)) {
            return false;
         }
      }

      if (keyIdentity4 == null) {
         if (rhs.keyIdentity4 != null) return false;
      }
      else {
         if (!keyIdentity4.equals(rhs.keyIdentity4)) {
            return false;
         }
      }

      if (ver2_SUPL_RESPONSE_extension == null) {
         if (rhs.ver2_SUPL_RESPONSE_extension != null) return false;
      }
      else {
         if (!ver2_SUPL_RESPONSE_extension.equals(rhs.ver2_SUPL_RESPONSE_extension)) {
            return false;
         }
      }

      if (extElem1 == null) {
         if (rhs.extElem1 != null) return false;
      }
      else {
         if (!extElem1.equals(rhs.extElem1)) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      int __code = 1;

      if (posMethod != null) __code = 31*__code + posMethod.hashCode();
      if (sLPAddress != null) __code = 31*__code + sLPAddress.hashCode();
      if (sETAuthKey != null) __code = 31*__code + sETAuthKey.hashCode();
      if (keyIdentity4 != null) __code = 31*__code + keyIdentity4.hashCode();
      if (ver2_SUPL_RESPONSE_extension != null) __code = 31*__code + ver2_SUPL_RESPONSE_extension.hashCode();
      if (extElem1 != null) __code = 31*__code + extElem1.hashCode();

      return __code;
   }

   /**
    * Accessor/mutator methods for PosMethod
    */
   public PosMethod getPosMethod () {
      return posMethod;
   }

   public void setPosMethod (PosMethod value) {
      this.posMethod = value;
   }

   /**
    * Accessor/mutator methods for SLPAddress
    */
   public SLPAddress getSLPAddress () {
      return sLPAddress;
   }

   public void setSLPAddress (SLPAddress value) {
      this.sLPAddress = value;
   }

   public boolean hasSLPAddress () {
      return (sLPAddress != null);
   }

   /**
    * Accessor/mutator methods for SETAuthKey
    */
   public SETAuthKey getSETAuthKey () {
      return sETAuthKey;
   }

   public void setSETAuthKey (SETAuthKey value) {
      this.sETAuthKey = value;
   }

   public boolean hasSETAuthKey () {
      return (sETAuthKey != null);
   }

   /**
    * Accessor/mutator methods for KeyIdentity4
    */
   public KeyIdentity4 getKeyIdentity4 () {
      return keyIdentity4;
   }

   public void setKeyIdentity4 (KeyIdentity4 value) 
      throws Asn1Exception
   {
      if (!(value.getLength() == 128)) {
         throw new Asn1ConsVioException ("value.getLength()", value.getLength());
      }

      this.keyIdentity4 = value;
   }

   public boolean hasKeyIdentity4 () {
      return (keyIdentity4 != null);
   }

   /**
    * Accessor/mutator methods for Ver2_SUPL_RESPONSE_extension
    */
   public Ver2_SUPL_RESPONSE_extension getVer2_SUPL_RESPONSE_extension () {
      return ver2_SUPL_RESPONSE_extension;
   }

   public void setVer2_SUPL_RESPONSE_extension (Ver2_SUPL_RESPONSE_extension value) {
      this.ver2_SUPL_RESPONSE_extension = value;
   }

   public boolean hasVer2_SUPL_RESPONSE_extension () {
      return (ver2_SUPL_RESPONSE_extension != null);
   }

   /**
    * Accessor/mutator methods for ExtElem1
    */
   public Asn1OpenExt getExtElem1 () {
      return extElem1;
   }

   public void setExtElem1 (Asn1OpenExt value) {
      this.extElem1 = value;
   }

   public int getElementCount() { return 6; }


   public Object getElementValue(int index){
      switch(index)  {
         case 0: return posMethod;
         case 1: return sLPAddress;
         case 2: return sETAuthKey;
         case 3: return keyIdentity4;
         case 4: return ver2_SUPL_RESPONSE_extension;
         case 5: return extElem1;
         default: return null;
      }
   }


   public String getElementName(int index){
      switch(index)  {
         case 0: return "posMethod";
         case 1: return "sLPAddress";
         case 2: return "sETAuthKey";
         case 3: return "keyIdentity4";
         case 4: return "ver2-SUPL-RESPONSE-extension";
         case 5: return null;
         default: return null;
      }
   }


   public void decode (Asn1PerDecodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      init ();

      // extension bit

      boolean extbit = buffer.decodeBit ("extbit");

      // optional bits

      boolean sLPAddressPresent = buffer.decodeBit ("sLPAddressPresent");
      boolean sETAuthKeyPresent = buffer.decodeBit ("sETAuthKeyPresent");
      boolean keyIdentity4Present = buffer.decodeBit ("keyIdentity4Present");

      // decode posMethod

      buffer.getContext().eventDispatcher.startElement("posMethod", -1);

      {
         int tval = PosMethod.decodeEnumValue (buffer);
         posMethod = PosMethod.valueOf (tval);
      }
      buffer.getContext().eventDispatcher.endElement("posMethod", -1);

      // decode sLPAddress

      if (sLPAddressPresent) {
         buffer.getContext().eventDispatcher.startElement("sLPAddress", -1);

         sLPAddress = new SLPAddress();
         sLPAddress.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("sLPAddress", -1);
      }
      else {
         sLPAddress = null;
      }

      // decode sETAuthKey

      if (sETAuthKeyPresent) {
         buffer.getContext().eventDispatcher.startElement("sETAuthKey", -1);

         sETAuthKey = new SETAuthKey();
         sETAuthKey.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("sETAuthKey", -1);
      }
      else {
         sETAuthKey = null;
      }

      // decode keyIdentity4

      if (keyIdentity4Present) {
         buffer.getContext().eventDispatcher.startElement("keyIdentity4", -1);

         keyIdentity4 = new KeyIdentity4();
         keyIdentity4.decode (buffer);
         buffer.getContext().eventDispatcher.endElement("keyIdentity4", -1);
      }
      else {
         keyIdentity4 = null;
      }

      if (extbit) {

         int bitcnt = buffer.decodeSmallLength(), i = 0;
         long bitidx = buffer.getBitOffset() + bitcnt;
         boolean[] bitmap = new boolean [bitcnt];

         if (i < bitcnt) bitmap[i++] = buffer.decodeBit ("ver2_SUPL_RESPONSE_extensionPresent");

         while (i < bitcnt) {
            bitmap[i++] = buffer.decodeBit ("unknown");
         }

         i = 0;

         // decode ver2_SUPL_RESPONSE_extension

         if (i < bitcnt && bitmap[i++]) {
            buffer.getContext().eventDispatcher.startElement("ver2_SUPL_RESPONSE_extension", -1);

            long _curidx = buffer.getBitOffset();
            bitidx = buffer.decodeExtLength ();
            if (bitidx - _curidx >= 131088)  {
               throw new Asn1Exception ("PER fragmentation not enabled: add -perindef to decode");
            }

            ver2_SUPL_RESPONSE_extension = new Ver2_SUPL_RESPONSE_extension();
            ver2_SUPL_RESPONSE_extension.decode (buffer);

            buffer.moveBitCursor (bitidx);
            buffer.getContext().eventDispatcher.endElement("ver2_SUPL_RESPONSE_extension", -1);

         }
         else {
            ver2_SUPL_RESPONSE_extension = null;
         }

         // decode unknown extension elements

         if (i < bitcnt) {
            Asn1OpenType openType = null;
            extElem1 = new Asn1OpenExt();
            int j = 0;
            while (i < bitcnt) {
               if (bitmap[i]) {
                  buffer.getContext().eventDispatcher.startElement("...", -1);

                  openType = extElem1.decodeOpenType (buffer, true, j++);
               }
               else {
                  extElem1.setOpenType (null, j++);
               }
               i++;
            }
         }
         buffer.byteAlign ();

      }

   }

   public void encode (Asn1PerEncodeBuffer buffer)
      throws Asn1Exception, java.io.IOException
   {
      // extension bit

      boolean extbit = ((ver2_SUPL_RESPONSE_extension != null) ||
      ((extElem1 != null) && (!extElem1.value.isEmpty()))
      );

      buffer.encodeBit (extbit, "extbit");

      // encode optional elements bit mask

      buffer.encodeBit ((sLPAddress != null), null);
      buffer.encodeBit ((sETAuthKey != null), null);
      buffer.encodeBit ((keyIdentity4 != null), null);

      // encode posMethod

      if (posMethod != null) {
         buffer.getContext().eventDispatcher.startElement("posMethod", -1);

         posMethod.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("posMethod", -1);
      }
      else throw new Asn1MissingRequiredException ("posMethod");

      // encode sLPAddress

      if (sLPAddress != null) {
         buffer.getContext().eventDispatcher.startElement("sLPAddress", -1);

         sLPAddress.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("sLPAddress", -1);
      }

      // encode sETAuthKey

      if (sETAuthKey != null) {
         buffer.getContext().eventDispatcher.startElement("sETAuthKey", -1);

         sETAuthKey.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("sETAuthKey", -1);
      }

      // encode keyIdentity4

      if (keyIdentity4 != null) {
         buffer.getContext().eventDispatcher.startElement("keyIdentity4", -1);

         keyIdentity4.encode (buffer);

         buffer.getContext().eventDispatcher.endElement("keyIdentity4", -1);
      }

      if (extbit) {

         // encode extension optional bits length

         int bitcnt = 1;
         if (extElem1 != null) bitcnt += extElem1.value.size();
         buffer.encodeSmallLength (bitcnt);

         // encode optional bits

         buffer.encodeBit ((ver2_SUPL_RESPONSE_extension != null), null);

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encodeExtBits (buffer);
         }

         // encode extension elements

         Asn1PerEncodeBuffer mainBuffer = buffer;
         buffer = new Asn1PerEncodeBuffer (buffer.isAligned());

         // ver2_SUPL_RESPONSE_extension

         if (ver2_SUPL_RESPONSE_extension != null) {
            buffer.reset();
            ver2_SUPL_RESPONSE_extension.encode (buffer);
            buffer.getContext().eventDispatcher.startElement("ver2_SUPL_RESPONSE_extension", -1);

            mainBuffer.encodeOpenType (buffer, null);

            buffer.getContext().eventDispatcher.endElement("ver2_SUPL_RESPONSE_extension", -1);
         }

         buffer = mainBuffer;

         if (extElem1 != null && extElem1.value.size() > 0) {
            extElem1.encode (buffer);
         }
      }
   }

}
