/*
 * Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
 * permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus,
 * aguts, dièresi, «ç» i «ñ» i la retorni en un array de char.
 * Després crea els mètodes:
 * xifraMonoAlfa(cadena) que xifre la cadena passada com a paràmetre amb xifratge monoalfabètic
 * utilitzant la permutació generada inicialment
 * desxifraMonoAlfa(cadena) que desxifre la cadena del paràmetre i torni una cadena dexifrada amb
 * monoalfabètic.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    
    static char[] arrayAlfabet = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    static char[] alfabetPermutat = permutaAlfabet(arrayAlfabet);
    
    public static void main(String[]args) {

        // Arrays amb cadenes de prova
        String[] provesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};

        // Bloc de xifrat
        System.out.println("Xifrat");
        System.out.println("---------");
        for (int i = 0; i < provesXifrar.length; i++) {
            String cadena = provesXifrar[i];
            String cadenaXifrada = xifraMonoAlfa(cadena);
            System.out.println("(" + i + ") -" + cadena + " => " + cadenaXifrada);
        }

        // Bloc de desxifrat
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < provesXifrar.length; i++) {
            String cadena = provesXifrar[i];
            String cadenaXifrada = xifraMonoAlfa(cadena);
            String cadenaDesxifrada = desxifraMonoAlfa(cadenaXifrada);
            System.out.println("(" + i + ") -" + cadenaXifrada + " => " + cadenaDesxifrada);
        }

    }

    // Métode que genera una permutació de l'alfabet donat 
    public static char[] permutaAlfabet(char[] alfabet) {

        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        Collections.shuffle(llista);

        char[] alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
 
        return alfabetPermutat;
    }

    // Métode que xifre la cadena passada amb xifratge monoalfabètic utilitzant l'alfabet permutat generat
    public static String xifraMonoAlfa(String cadena) {
        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

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

    // Métode que desxifre la cadena passada amb xifratge monoalfabètic utilitzant l'alfabet permutat generat
    public static String desxifraMonoAlfa(String cadena) {
        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

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
