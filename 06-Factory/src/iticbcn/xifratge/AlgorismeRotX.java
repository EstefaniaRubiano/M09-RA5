package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        // Retorna un XifradorRotX
        return new XifradorRotX();
    }
}
