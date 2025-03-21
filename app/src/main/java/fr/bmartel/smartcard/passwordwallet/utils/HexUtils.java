package fr.bmartel.smartcard.passwordwallet.utils;

/**
 * Some functions used to manage hex
 *
 * @author Bertrand Martel
 */
public class HexUtils {

    /**
     * https://stackoverflow.com/a/18714790/2614364
     *
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.forDigit(s.charAt(i), 16) << 4) + Character.forDigit(s.charAt(i + 1), 16));
        }
        return data;
    }

    final protected static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * https://stackoverflow.com/a/18714790/2614364
     *
     * @param bytes
     * @return
     */
    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;

        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}