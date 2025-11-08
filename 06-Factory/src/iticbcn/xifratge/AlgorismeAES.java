package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory{
    
    @Override
    public Xifrador creaXifrador() {
        // retorna un XifradorAES
        return new XifradorAES();
    }
}
