package iticbcn.xifratge;

/*
* Enunciat
Crea una classe Java anomenada RotX.java que contingui les següents funcions:
xifraRotX( cadena, desplaçament ): Ha de substituir cada lletra (no els espais ni els signes de
puntuació) per la lletra que està "desplaçament" posicions més a la dreta en l’abecedari (si
sobrepassa ha de tornar a començar).
desxifraRotX( cadena, desplaçament ): Ha de fer el procés invers de la funció anterior.
forcaBrutaRotX( cadenaXifrada ): Ha de provar tots els desplaçaments possibles i mostrar el
missatge resultant de desxifrar amb desplaçaments de 1,2,3,... fins la longitud de l’abecedari.
Crea també un main per fer algunes proves.
*/

public class XifradorRotX implements Xifrador{

    private static char[] arrayAbecedariMin = "aàáäbcdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    private static char[] arrayAbecedariMaj = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();


    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // Convertim la clau a int
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        // Xifrem i retornem TextXifrat
        return new TextXifrat(xifraRotX(msg, desplacament).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        // Desxifrem el TextXifrat
        return desxifraRotX(new String(xifrat.getBytes()), desplacament);
    }

    // Métode que xifra una cadena donada, segons el desplacamnt donat, utilitzant ROTX
    public String xifraRotX(String cadena, int desplacament) {
        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            // Busca la posició del caràcter dins l'abecedari en majúscules
            int posCaracter = buscaPosicio(arrayAbecedariMaj, caracter);

            if (posCaracter != -1) {
                int novaPos = (posCaracter + desplacament) % arrayAbecedariMaj.length;
                textXifrat += arrayAbecedariMaj[novaPos];
            } else {
                // Busca la posició del caràcter dins l'abecedari en minúscules
                posCaracter = buscaPosicio(arrayAbecedariMin, caracter);

                if (posCaracter != -1) {
                    int novaPos = (posCaracter + desplacament) % arrayAbecedariMin.length;
                    textXifrat += arrayAbecedariMin[novaPos];
                } else {
                    // Si no és cap lletra, l'afegim igual
                    textXifrat += caracter;
                }
            }
        }

        return textXifrat;
    }

    // Métode que desxifra una cadena donada, segons el desplaçamnt donat, utilitzant ROTX
    public String desxifraRotX(String cadena, int desplacament) {
        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            // Busca la posició del caràcter dins l'abecedari en majúscules
            int posCaracter = buscaPosicio(arrayAbecedariMaj, caracter);

            if (posCaracter != -1) {

                int novaPos = (posCaracter - desplacament + arrayAbecedariMaj.length) % arrayAbecedariMaj.length;
                textDesxifrat += arrayAbecedariMaj[novaPos];
            } else {
                // Busca la posició del caràcter dins l'abecedari en minúscules
                posCaracter = buscaPosicio(arrayAbecedariMin, caracter);

                if (posCaracter != -1) {
                    int novaPos = (posCaracter - desplacament + arrayAbecedariMin.length) % arrayAbecedariMin.length;
                    textDesxifrat += arrayAbecedariMin[novaPos];
                } else {
                    // Si no és cap lletra, l'afegim igual
                    textDesxifrat += caracter;
                }
            }
        }

        return textDesxifrat;
    }

    // Métode que desxifra una cadena provant tots els desplaçaments possibles i mostra els missatges resultants
    public void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("\n Missatge xifrat: " + cadenaXifrada + "\n");

        for (int i = 0; i < arrayAbecedariMaj.length; i++) {
            
            System.out.println("(" + i + ")" + desxifraRotX(cadenaXifrada, i));
        }
        
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
