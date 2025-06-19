package org.mobicents.gmlc.slee.utils;

import com.objsys.asn1j.runtime.Asn1Integer;
import com.objsys.asn1j.runtime.Asn1PerEncodeBuffer;
import org.mobicents.gmlc.slee.supl.SUPL_INIT.SUPLINIT;
import org.mobicents.gmlc.slee.supl.ULP.ULP_PDU;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Random;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ByteUtils {

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    /* Hex chars */
    private static final byte[] HEX_CHAR = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /*
     * Helper functions that dumps an array of bytes in the hexadecimal format.
     */
    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        if (in != null) {
            for (byte b : in) {
                builder.append(String.format("%02x", b));
            }
        }
        return builder.toString();
    }

    public static final String dumpBytes(byte[] buffer) {
        if ( buffer == null ) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            sb.append("0x").append((char) ( HEX_CHAR[(buffer[i] & 0x00F0 ) >> 4])).append((char) (HEX_CHAR[buffer[i] & 0x000F])).append(" ");
        }
        return sb.toString();
    }

    public static final String dumpBytesToHexString(byte[] buffer) {
        if ( buffer == null ) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++) {
            sb.append((char) (HEX_CHAR[(buffer[i] & 0x00F0 ) >> 4])).append((char) (HEX_CHAR[buffer[i] & 0x000F]));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len;
        byte[] data = null;
        if (s != null) {
            len = s.length();
            data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
            }
        }
        return data;
    }

    public static int[] readOctet(byte[] b, int position, int[] intArray) {
        int x = 7;
        int value, index = position-1;
        for (int c=position; c<8; c++) {
            int i = x / 8;
            int j = x % 8;
            value = (b[i] >> j) & 1;
            System.out.print(value);
            intArray[index] = value;
            x--;
            index--;
        }
        return intArray;
    }

    public static int convertBitsToIntValue(int[] intArray) {
        int intValue = 0;
        for (int x=0; x<(intArray.length); x++) {
            intValue = (int) (intValue + intArray[x]*(Math.pow(2, x)));
        }
        return intValue;
    }

    public static double hex2Double(String s) {
        return (int)Long.parseLong(s, 16) / 1e7;
    }

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
                "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }

    public static String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }

    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }


    /**
     * Return a new byte array containing a sub-portion of the source array
     *
     * @param srcBegin
     *          The beginning index (inclusive)
     * @return The new, populated byte array
     */
    public static byte[] subBytes(byte[] source, int srcBegin) {
        return subBytes(source, srcBegin, source.length);
    }

    /**
     * Return a new byte array containing a sub-portion of the source array
     *
     * @param srcBegin
     *          The beginning index (inclusive)
     * @param srcEnd
     *          The ending index (exclusive)
     * @return The new, populated byte array
     */
    public static byte[] subBytes(byte[] source, int srcBegin, int srcEnd) {
        byte destination[];

        destination = new byte[srcEnd - srcBegin];
        getBytes(source, srcBegin, srcEnd, destination, 0);

        return destination;
    }

    /**
     * Copies bytes from the source byte array to the destination array
     *
     * @param source
     *          The source array
     * @param srcBegin
     *          Index of the first source byte to copy
     * @param srcEnd
     *          Index after the last source byte to copy
     * @param destination
     *          The destination array
     * @param dstBegin
     *          The starting offset in the destination array
     */
    public static void getBytes(byte[] source, int srcBegin, int srcEnd, byte[] destination,
                                int dstBegin) {
        System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd - srcBegin);
    }

    public static String getMNC(byte[] plmnId) {
        if (plmnId == null || plmnId.length != 3)
            throw new IllegalArgumentException(String.format("Wrong plmnId %s", Arrays.toString(plmnId)));

        int a1 = plmnId[2] & 0x0F;
        int a2 = (plmnId[2] & 0xF0) >> 4;
        int a3 = (plmnId[1] & 0xF0) >> 4;

        if (a3 == 15)
            return "" + a1 + a2;
        else
            return "" + a1 + a2 + a3;
    }

    public static String generateRandomStringByLength(int length, boolean numericOnly) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = numericOnly ? 57 : 122 ; // 122 letter 'z', 57 number 9.
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static byte[] encodeUlp(ULP_PDU message) {
        try {
            Asn1PerEncodeBuffer outputStream = new Asn1PerEncodeBuffer(false);
            message.setLength(new Asn1Integer(0));
            message.encode(outputStream);
            ByteBuffer buffer = ByteBuffer.wrap(outputStream.getBuffer());
            buffer.order(ByteOrder.BIG_ENDIAN);
            buffer.putShort((short) outputStream.getBuffer().length);
            byte[] bytes = buffer.array();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encodeSUPL(SUPLINIT message) {
        try {
            Asn1PerEncodeBuffer outputStream = new Asn1PerEncodeBuffer(false);
            //message.(new Asn1Integer(0));
            message.encode(outputStream);
            ByteBuffer buffer = ByteBuffer.wrap(outputStream.getBuffer());
            buffer.order(ByteOrder.BIG_ENDIAN);
            buffer.putShort((short) outputStream.getBuffer().length);
            byte[] bytes = buffer.array();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
