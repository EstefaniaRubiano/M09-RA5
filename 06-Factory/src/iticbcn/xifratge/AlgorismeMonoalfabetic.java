package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador() {
        // Retorna un XifradorMonoalfabetic
        return new XifradorMonoalfabetic();
    }
}
