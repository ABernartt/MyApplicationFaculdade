package bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
public abstract class ProdutoDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "produtosDatabase";
    private static final Object LOCK = new Object();
    private static ProdutoDatabase sInstancia;

    public static ProdutoDatabase getInstance(Context context){
        if(sInstancia == null){
            synchronized (LOCK){
                sInstancia = Room.databaseBuilder(context.getApplicationContext(),
                        ProdutoDatabase.class, ProdutoDatabase.DATABASE_NAME).build();
            }
        }
        return sInstancia;
    }

    public abstract ProdutoDAO mProdutoDAO();

}


