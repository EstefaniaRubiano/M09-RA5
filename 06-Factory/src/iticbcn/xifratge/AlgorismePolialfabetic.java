package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        // Retorna un XifradorPolialfabetic
        return new XifradorPolialfabetic();
    }
}
