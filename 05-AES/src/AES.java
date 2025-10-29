/**
 * AES.java
 * 
 * Implementa el xifrat i desxifrat de text utilitzant AES-256 en mode CBC amb padding PKCS5.
 * Es fa servir SHA-256 per generar la clau a partir d'una contrasenya i un IV de 16 bytes.
 */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Chulin";

    public static void main(String[] args) {
        String msgs[]= {"Lorem ipsum dicet",
                    "Hola Andrés cómo está tu cuñado",
                    "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = XifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

        System.out.println("--------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
        }
        
    }

    // Métode que xifra un text amb AES i retorna els bytes xifrats.
    public static byte[] XifraAES(String msg, String clau) throws Exception{
        // Obtenir els bytes de l'String
        byte[] byteArray = msg.getBytes();
        
        // Genera ivParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // ara l'array global 'iv' te valors aleatoris
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = messageDigest.digest(CLAU.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);

        // Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] missatgeXifrat = cipher.doFinal(byteArray);

        // Combinar IV i part xifrada
        byte[] resultat = new byte[iv.length + missatgeXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(missatgeXifrat, 0, resultat, iv.length, missatgeXifrat.length);
        
        // return iv+msgxifrat
        return resultat;
    }

    // Métode que desxifra els bytes xifrats i retorna el text original.
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV
        byte[] ivExtraido = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, ivExtraido, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtraido);

        // Extreure la part xifrada
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);

        // Fer hash de la clau
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = messageDigest.digest(clau.getBytes());
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);
        
        // Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] missatgeDesxifrat = cipher.doFinal(msgXifrat);

        // return String desxifrat
        return new String(missatgeDesxifrat);
    }

}
