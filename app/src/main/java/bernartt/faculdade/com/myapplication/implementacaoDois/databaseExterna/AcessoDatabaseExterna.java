package bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.Produto;
import bernartt.faculdade.com.myapplication.implementacaoDois.helpers.Injetor;

public class AcessoDatabaseExterna {

    private static final Object LOCK = new Object();
    private static AcessoDatabaseExterna sInstancia;
    private final MutableLiveData<Produto> mProdutoMutableLiveDataD;
    private final FirebaseDatabase mFirebaseDatabase;

   private AcessoDatabaseExterna(){

        mProdutoMutableLiveDataD = new MutableLiveData<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    public static AcessoDatabaseExterna getInstance(){
        if(sInstancia == null){
            synchronized (LOCK){
                sInstancia = new AcessoDatabaseExterna();
            }
        }
        return sInstancia;
    }

    public MutableLiveData<Produto> getDadosDatabaseExternaLD(){
        return mProdutoMutableLiveDataD;
    }

    public void baixarDadosExternos(int chamadas){
        chamadas +=1;
        DatabaseReference ref = mFirebaseDatabase.getReference("data");
        int finalChamadas = chamadas;
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int sku = Integer.parseInt(dataSnapshot.child("sku").getValue().toString());
                String nome = dataSnapshot.child("nome").getValue().toString();
                double preco = Double.parseDouble(dataSnapshot.child("preco").getValue().toString());

                Produto produto = new Produto(sku, nome, preco, finalChamadas);

                mProdutoMutableLiveDataD.postValue(produto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void salvarDatabase(Produto produto, Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("data");
        ref.child("sku").setValue(produto.getSku());
        ref.child("nome").setValue(produto.getProdNome());
        ref.child("preco").setValue(produto.getProdPreco());

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(()->{
            Repositorio repositorio = Injetor.forneceRepositorio(context);
            repositorio.setProdSKU(produto.getSku());
            repositorio.setProdNome(produto.prodNome);
            repositorio.setProdPreco(produto.getProdPreco());

        });
    }
}
