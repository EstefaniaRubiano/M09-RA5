package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class ClauPublica {

    private static final String algorisme = "RSA";

    /**
     * Genera un parell de claus RSA (pública + privada)
     * @return KeyPair amb les claus pública i privada
     */
    public KeyPair generaParellClausRSA() throws Exception{
        // Creem un generador de claus RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorisme);
        // Creem un generador de números aleatoris
        SecureRandom random = new SecureRandom();
        // Inicialitzem el generador 
        keyGen.initialize(2048, random);
        // Generem i retornem el parell de claus
        return keyGen.generateKeyPair();
    }

    /**
     * Xifra un missatge usant la clau pública RSA
     * @return Array de bytes amb el missatge xifrat
     */
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        // Creem un objecte Cipher per l'algorisme RSA
        Cipher cipher = Cipher.getInstance(algorisme);
        // Inicialitzem el xifrador
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        // Convertim el missatge a bytes i el xifrem
        byte[] msgXifrat = cipher.doFinal(msg.getBytes()); // Convertimos el cifrado de string a byte
        // Retornem el missatge xifrat
        return msgXifrat;
    }

    /**
     * Desxifra un missatge xifrat usant la clau privada RSA
     * @return Missatge original en text
     */
    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        // Creem un objecte Cipher per l'algorisme RSA
        Cipher cipher = Cipher.getInstance(algorisme);
        // Inicialitzem el xifrador
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        // Desxifrem el missatge xifrat
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
        // Convertim els bytes desxifrats a String i retornem
        return new String(msgDesxifrat);
    }
}
