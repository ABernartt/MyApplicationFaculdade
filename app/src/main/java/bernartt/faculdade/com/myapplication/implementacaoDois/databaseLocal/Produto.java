package bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Produto {

    @PrimaryKey
    public int sku;

    @ColumnInfo(name = "prodNome")
    public String prodNome;

    @ColumnInfo(name = "prodPreço")
    public double prodPreco;

    @ColumnInfo(name = "chamadasDB")
    public int chamadas;

    public Produto(int sku, String prodNome, double prodPreco, int chamadas){
        this.sku = sku;
        this.prodNome = prodNome;
        this.prodPreco = prodPreco;
        this.chamadas=chamadas;
    }

    public int getChamadas() {
        return chamadas;
    }

    public int getSku() {
        return sku;
    }

    public String getProdNome() {
        return prodNome;
    }

    public double getProdPreco() {
        return prodPreco;
    }

}



