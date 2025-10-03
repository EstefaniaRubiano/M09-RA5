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

public class RotX {

    static char[] arrayAbecedariMin = "aàáäbcdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    static char[] arrayAbecedariMaj = "AÀÁÄBCDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();
    public static void main(String[]args) {

        // Arrays amb cadenes de prova
        String[] provesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};

        // Bloc de xifrat
        System.out.println("Xifrat");
        System.out.println("---------");
        for (int i = 0; i < provesXifrar.length; i++) {
            String cadena = provesXifrar[i];
            String cadenaXifrada = xifraRotX(cadena, i*2);
            System.out.println("(" + i*2 + ") -" + cadena + " => " + cadenaXifrada);
        }

        // Bloc de desxifrat
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < provesXifrar.length; i++) {
            String cadena = provesXifrar[i];
            String cadenaXifrada = xifraRotX(cadena, i*2);
            String cadenaDesxifrada = desxifraRotX(cadenaXifrada, i*2);
            System.out.println("(" + i*2 + ") -" + cadenaXifrada + " => " + cadenaDesxifrada);

            if (i == provesXifrar.length - 1) {
                forcaBrutaRotX(cadenaXifrada);
            }
        }

    }

    // Métode que xifra una cadena donada, segons el desplacamnt donat, utilitzant ROTX
    public static String xifraRotX(String cadena, int desplacament) {
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
    public static String desxifraRotX(String cadena, int desplacament) {
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
    public static void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("\n Missatge xifrat: " + cadenaXifrada + "\n");

        for (int i = 0; i < arrayAbecedariMaj.length; i++) {
            
            System.out.println("(" + i + ")" + desxifraRotX(cadenaXifrada, i));
        }
        
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
