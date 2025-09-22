/* Enunciat
Crea una classe Java anomenada Rot13.java que contingui les següents funcions:
xifraRot13( cadena ): Ha de substituir cada lletra (no els espais ni els signes de puntuació) per la
lletra que està 13 posicions més a la dreta en l’abecedari (si sobrepassa ha de tornar a començar).
desxifraRot13( cadena ): Ha de fer el procés invers de la funció anterior.
Crea també un main per fer algunes proves.
*/

public class Rot13 {

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
            String cadenaXifrada = xifraRot13(cadena);
            System.out.println(cadena + " => " + cadenaXifrada);
        }

        // Bloc de desxifrat
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < provesXifrar.length; i++) {
            String cadena = provesXifrar[i];
            String cadenaXifrada = xifraRot13(cadena);
            String cadenaDesxifrada = desxifraRot13(cadenaXifrada);
            System.out.println(cadenaXifrada + " => " + cadenaDesxifrada);
        }

    }

    // Métode que xifra una cadena donada utilitzant ROT13
    public static String xifraRot13(String cadena) {
        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            // Busca la posició del caràcter dins l'abecedari en majúscules
            int posCaracter = buscaPosicio(arrayAbecedariMaj, caracter);

            if (posCaracter != -1) {
                int novaPos = (posCaracter + 13) % arrayAbecedariMaj.length;
                textXifrat += arrayAbecedariMaj[novaPos];
            } else {
                // Busca la posició del caràcter dins l'abecedari en minúscules
                posCaracter = buscaPosicio(arrayAbecedariMin, caracter);

                if (posCaracter != -1) {
                    int novaPos = (posCaracter + 13) % arrayAbecedariMin.length;
                    textXifrat += arrayAbecedariMin[novaPos];
                } else {
                    // Si no és cap lletra, l'afegim igual
                    textXifrat += caracter;
                }
            }
        }

        return textXifrat;
    }

    // Métode que desxifra una cadena donada utilitzant ROT13
    public static String desxifraRot13(String cadena) {
        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            // Busca la posició del caràcter dins l'abecedari en majúscules
            int posCaracter = buscaPosicio(arrayAbecedariMaj, caracter);

            if (posCaracter != -1) {

                int novaPos = (posCaracter - 13 + arrayAbecedariMaj.length) % arrayAbecedariMaj.length;
                textDesxifrat += arrayAbecedariMaj[novaPos];
            } else {
                // Busca la posició del caràcter dins l'abecedari en minúscules
                posCaracter = buscaPosicio(arrayAbecedariMin, caracter);

                if (posCaracter != -1) {
                    int novaPos = (posCaracter - 13 + arrayAbecedariMin.length) % arrayAbecedariMin.length;
                    textDesxifrat += arrayAbecedariMin[novaPos];
                } else {
                    // Si no és cap lletra, l'afegim igual
                    textDesxifrat += caracter;
                }
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