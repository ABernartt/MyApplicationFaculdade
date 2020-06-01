package bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProdutoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setProduto(Produto produto);

    @Query("SELECT * from produto")
    LiveData<Produto> getProduto();

    @Query("UPDATE produto SET sku=:sku")
    void updateSku(int sku);

    @Query("UPDATE produto SET prodNome=:prodNome")
    void updateProdNome(String prodNome);

    @Query("UPDATE produto SET prodPreço=:prodPreco")
    void updateProdPreco(double prodPreco);

    @Query("SELECT sku FROM produto")
    int getSKU();

    @Query("SELECT prodNome FROM produto")
    String getProdNome();

    @Query("SELECT prodPreço FROM produto")
    double getProdPreco();

    @Query("UPDATE produto SET chamadasDB=:chamadasDB")
    void updateChamadas(int chamadasDB);

    @Query("SELECT chamadasDB FROM produto")
    int getChamadas();

}


