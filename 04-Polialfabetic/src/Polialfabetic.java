/*
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    static char[] arrayAlfabet = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    static char[] alfabetPermutat = new char[arrayAlfabet.length];
    private final static long CLAU_SECRETA = 1234;
    private static Random rnd = null;
    
    public static void main(String[] args) {
        String msgs[]={"Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU_SECRETA);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU_SECRETA);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(long clau){
        rnd = new Random(CLAU_SECRETA);
    }

    // Métode que genera una permutació de l'alfabet donat 
    public static void permutaAlfabet(char[] alfabet) {

        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        Collections.shuffle(llista, rnd);

        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
 
    }

    // Métode que xifra la cadena passada com a paràmetre
    // amb xifratge polialfabètic
    public static String xifraPoliAlfa(String msg) {

        String textXifrat = "";

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);

            permutaAlfabet(arrayAlfabet);

            boolean esMinuscula = Character.isLowerCase(c);
            char lletraMaj = Character.toUpperCase(c);

            // Busca la posició a l'alfabet original (majúscules)
            int posCaracter = buscaPosicio(arrayAlfabet, lletraMaj);

            if (posCaracter != -1){
                if (esMinuscula) {
                    textXifrat += Character.toLowerCase(alfabetPermutat[posCaracter]);
                } else {
                    textXifrat += alfabetPermutat[posCaracter];
                }
            } else {
                // Si no és una lletra del alfabet, la deixem igual
                textXifrat += c;
            }
            
        }

        return textXifrat;
        
    }

    // Métode que desxifra la cadena del paràmetre i torna una cadena 
    // desxifrada amb polialfabètic
    public static String desxifraPoliAlfa(String msgXifrat) {

        String textDesxifrat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);

            permutaAlfabet(arrayAlfabet);

            boolean esMinuscula = Character.isLowerCase(c);
            char lletraMaj = Character.toUpperCase(c);

            // Busca la posició a l'alfabet PERMUTAT (majúscules)
            int posCaracter = buscaPosicio(alfabetPermutat, lletraMaj);

            if (posCaracter != -1){
                if (esMinuscula) {
                    textDesxifrat += Character.toLowerCase(arrayAlfabet[posCaracter]);
                } else {
                    textDesxifrat += arrayAlfabet[posCaracter];
                }
            } else {
                // Si no és una lletra del alfabet, la deixem igual
                textDesxifrat += c;
            }
            
        }

        return textDesxifrat;
    }

    // Métode que busca la posició d'un caràcter en un array donat
    // Retorna l'índex si el troba o -1 si no és
    public static int buscaPosicio(char[] array, char lletra ) {

        for (int j = 0; j < array.length; j++) {
            char c = array[j];

            if (lletra == c) {
                return j; 
            }
        }
        return -1;
    }

}
