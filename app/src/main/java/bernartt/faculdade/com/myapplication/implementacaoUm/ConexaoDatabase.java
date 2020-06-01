package bernartt.faculdade.com.myapplication.implementacaoUm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import bernartt.faculdade.com.myapplication.IDataBase;
import bernartt.faculdade.com.myapplication.implementacaoControle.DatabaseControle;
import bernartt.faculdade.com.myapplication.implementacaoControle.EntidadeControle;


public class ConexaoDatabase{

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private IDataBase mIDataBase;
    private MutableLiveData<EntidadeControle> mEntidadeControleMutableLiveData;

    public ConexaoDatabase(IDataBase iDataBase){
        mIDataBase = iDataBase;
        mEntidadeControleMutableLiveData = new MutableLiveData<>();
    }

    public void getDados(Context context){
        DatabaseReference ref = database.getReference("data");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sku = Integer.parseInt(dataSnapshot.child("sku").getValue().toString());
                String nome = dataSnapshot.child("nome").getValue().toString();
                double preco = Double.parseDouble(dataSnapshot.child("preco").getValue().toString());
                ProdutoImplUm produtoImplUm = new ProdutoImplUm(sku, nome, preco);
                mIDataBase.requisitarDatabase(produtoImplUm);
                setControleChamadas(context);
             }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void salvarDados(ProdutoImplUm data){
        DatabaseReference ref = database.getReference("data");
        ref.child("sku").setValue(data.getSKU());
        ref.child("nome").setValue(data.getProdNome());
        ref.child("preco").setValue(data.getProdPreco());
    }

    public void setControleChamadas(Context context){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(()->{
            DatabaseControle mDatabaseControle=  Room.
                    databaseBuilder(context, DatabaseControle.class, "controleDatabase")
                    .build();
            int chamada = mDatabaseControle.mDAOControle().getChamadasImplUm();
            int updateChamada = chamada + 1;
            EntidadeControle entidadeControle = new EntidadeControle('1', updateChamada);
            mDatabaseControle.mDAOControle().setEntidadeControle(entidadeControle);
            mEntidadeControleMutableLiveData.postValue(entidadeControle);
        });
    }

    public MutableLiveData<EntidadeControle> getEntidadeControleMutableLiveData(){
        return mEntidadeControleMutableLiveData;
    }
}


