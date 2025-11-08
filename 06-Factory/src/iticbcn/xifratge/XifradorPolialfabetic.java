package iticbcn.xifratge;

/*
 * Polialfabetic.java
 * ------------------
 * Programa que xifra i desxifra textos amb un mètode polialfabètic.
 * 
 * Funciona així:
 * 1. Genera una permutació aleatòria de l'alfabet per cada lletra.
 * 2. Substitueix cada lletra del text segons l'alfabet permutat.
 * 3. Manté els caràcters que no són lletres sense canviar.
 * 4. Utilitza una clau secreta per poder desxifrar el text correctament.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{

    private static char[] arrayAlfabet = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    private final char[] alfabetPermutat = new char[arrayAlfabet.length];
    private static final long CLAU_SECRETA = 1234;
    private Random rnd = null;

    @Override
    // Mètode per xifrar un text
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        // Inicialitzem random amb la clau
        initRandom(clauLong);

        // Xifrem i retornem TextXifrat
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    // Mètode per desxifrar un text
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
    
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauLong);

        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }

    public void initRandom(long clau){
        rnd = new Random(CLAU_SECRETA);
    }

    // Métode que genera una permutació de l'alfabet donat 
    public void permutaAlfabet(char[] alfabet) {

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
    public String xifraPoliAlfa(String msg) {

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
    public String desxifraPoliAlfa(String msgXifrat) {
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
    public int buscaPosicio(char[] array, char lletra ) {

        for (int j = 0; j < array.length; j++) {
            char c = array[j];
            if (lletra == c) {
                return j; 
            }
        }
        return -1;
    }
}
