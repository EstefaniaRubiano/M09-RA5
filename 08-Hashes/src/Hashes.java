import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Hashes {

    // Comptador de contrasenyes probades durant la força bruta
    public int npass = 0;

    /**
     * getSHA512AmbSalt
     * Calcula SHA-512 de (pw + salt)
     */
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        // Per obtenir el hash
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String combined = pw + salt;
        byte [] hashBytes = md.digest(combined.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashBytes);
    }

    /**
     * getPBKDF2AmbSalt
     * Calcula PBKDF2 (Password-Based Key Derivation Function) 
     */
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iterations = 1000;
        int keyLength = 128; // en bits (128 bits = 16 bytes = 32 hex chars)

        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1"); 

        byte[] hashBytes = factory.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashBytes);
    }

    /**
     * pwTrobat
     * Mètode auxiliar que retorna la contrasenya trobada (String) o null si no coincideix
     */
    private String pwTrobat(String alg, char[] aPw, int pos, char ch, String hash, String salt) throws Exception {
        aPw[pos] = ch; // col·loca el caràcter a probar a la posició
        String currentPw = new String(aPw, 0, pos + 1); // construeix una contrasenya parcial
        npass ++; // incrementa el comptado d'intents

        String testHash;
        if (alg.equals("SHA-512")) {
            testHash = getSHA512AmbSalt(currentPw, salt);
        } else { // PBKDF2
            testHash = getPBKDF2AmbSalt(currentPw, salt);
        }
        
        if (testHash.equals(hash)) {
            return currentPw;
        }
        
        return null;
    }

    /**
     * forcaBruta
     * Implementa força bruta provant totes les contrasenyes de longitud 1..6
     * usant el conjunt de caràcters (charset)
     */
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String charset = "abcdefABCDEF1234567890!"; // conjunt de caràcters a probar
        char[] aPw = new char[6]; // construir la contrasenya (màx 6)
        npass = 0; // reiniciar comptador 

        String pw;

        for (int i0 = 0; i0 < charset.length(); i0++) {
            if ((pw = pwTrobat(alg, aPw, 0, charset.charAt(i0), hash, salt)) != null)
                return pw;

            for (int i1 = 0; i1 < charset.length(); i1++) {
                if ((pw = pwTrobat(alg, aPw, 1, charset.charAt(i1), hash, salt)) != null)
                    return pw;

                for (int i2 = 0; i2 < charset.length(); i2++) {
                    if ((pw = pwTrobat(alg, aPw, 2, charset.charAt(i2), hash, salt)) != null)
                        return pw;

                    for (int i3 = 0; i3 < charset.length(); i3++) {
                        if ((pw = pwTrobat(alg, aPw, 3, charset.charAt(i3), hash, salt)) != null)
                            return pw;

                        for (int i4 = 0; i4 < charset.length(); i4++) {
                            if ((pw = pwTrobat(alg, aPw, 4, charset.charAt(i4), hash, salt)) != null)
                                return pw;

                            for (int i5 = 0; i5 < charset.length(); i5++) {
                                if ((pw = pwTrobat(alg, aPw, 5, charset.charAt(i5), hash, salt)) != null)
                                    return pw;
                            }
                        }
                    }
                }
            }
        }

        return null; // no s'ha trobat 
    }

    /**
     * getInterval
     * Retorna un string amb la diferència entre t2 i t1 en dies/hores/minuts/segons/millis.
     * Útil per mostrar el temps de la força bruta.
     */
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;

        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (diff % (1000 * 60)) / 1000;
        long millis = diff % 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", 
                        days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {

        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt) };
        
        String pwTrobat = null;
        
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("==============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("------------------------------\n\n");
        }

    }
}
