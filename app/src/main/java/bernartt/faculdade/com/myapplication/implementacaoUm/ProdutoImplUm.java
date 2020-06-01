package bernartt.faculdade.com.myapplication.implementacaoUm;

public class ProdutoImplUm {

    private int mSKU;
    private String mProdNome;
    private double mProdPreco;

    public ProdutoImplUm(int SKU, String prodNome, double prodPreco) {
        mSKU = SKU;
        mProdNome = prodNome;
        mProdPreco = prodPreco;
    }

    public int getSKU() {
        return mSKU;
    }

    public void setSKU(int SKU) {
        mSKU = SKU;
    }

    public String getProdNome() {
        return mProdNome;
    }

    public void setProdNome(String prodNome) {
        mProdNome = prodNome;
    }

    public double getProdPreco() {
        return mProdPreco;
    }

    public void setProdPreco(double prodPreco) {
        mProdPreco = prodPreco;
    }
}
