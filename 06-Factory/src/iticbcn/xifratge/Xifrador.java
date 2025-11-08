package iticbcn.xifratge;

public interface Xifrador {

    // Mètode per xifrar un text
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;

    // Mètode per desxifrar un text
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
