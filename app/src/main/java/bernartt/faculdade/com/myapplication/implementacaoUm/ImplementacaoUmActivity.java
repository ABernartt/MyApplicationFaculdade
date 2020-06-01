
package bernartt.faculdade.com.myapplication.implementacaoUm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import bernartt.faculdade.com.myapplication.IDataBase;
import bernartt.faculdade.com.myapplication.MainActivity;
import bernartt.faculdade.com.myapplication.R;
import bernartt.faculdade.com.myapplication.implementacaoControle.EntidadeControle;
import bernartt.faculdade.com.myapplication.implementacaoUm.viewModel.DBUmVM;

public class ImplementacaoUmActivity extends AppCompatActivity implements IDataBase {

    private Observer<ProdutoImplUm> mDataObserver;
    private DBUmVM mDBUmVM;
    private ConexaoDatabase mConexaoDatabase;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TextView mTituloTv, mDescricaoTv, mSKUTv, mNomeTv, mPrecoTv, mChamadasTv;
    private EditText mUpdateSKUEt, mUpdateNomeEt, mUpdatePrecoEt;
    private Button mSalvarDadosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        mTituloTv = (TextView) findViewById(R.id.titulo);
        mDescricaoTv = (TextView) findViewById(R.id.implementacaoDesc);
        mSKUTv = (TextView) findViewById(R.id.prodSKU);
        mNomeTv = (TextView) findViewById(R.id.prodNome);
        mPrecoTv = (TextView) findViewById(R.id.prodPreco);
        mChamadasTv= (TextView) findViewById(R.id.chamadas);
        mSalvarDadosBtn = (Button) findViewById(R.id.saveData);
        mUpdateSKUEt = (EditText) findViewById(R.id.editSKU);
        mUpdateNomeEt = (EditText) findViewById(R.id.editNome);
        mUpdatePrecoEt = (EditText) findViewById(R.id.editPreco);

        mTituloTv.setText(getResources().getString(R.string.tituloUm));
        mDescricaoTv.setText(getResources().getString(R.string.descUm));

        mConexaoDatabase = new ConexaoDatabase(this);
        LiveData<EntidadeControle> liveData = mConexaoDatabase.
                getEntidadeControleMutableLiveData();
        liveData.observe(this, new Observer<EntidadeControle>() {
            @Override
            public void onChanged(EntidadeControle entidadeControle) {

                mChamadasTv.setText(getResources().getString(R.string.chamadas,
                        String.valueOf(entidadeControle.getImplementacaoUm())));

            }
        });

        mDBUmVM = new ViewModelProvider(this).get(DBUmVM.class);
        mDataObserver = new Observer<ProdutoImplUm>() {
            @Override
            public void onChanged(ProdutoImplUm data) {

                if (data != null){
                    int sku = data.getSKU();
                    String prodName = data.getProdNome();
                    double prodPreco = data.getProdPreco();
                    mSKUTv.setText(getResources().getString(R.string.prodSKU, String.valueOf(sku)));
                    mNomeTv.setText(getResources().getString(R.string.prodNome, prodName));
                    mPrecoTv.setText(getResources().getString(R.string.prodPreco, String.valueOf(prodPreco)));
                    mUpdateSKUEt.setText(String.valueOf(sku));
                    mUpdateNomeEt.setText(prodName);
                    mUpdatePrecoEt.setText(String.valueOf(prodPreco));
                }
            }
        };

        mDBUmVM.getCurrentData().observe(this, mDataObserver);

        mConexaoDatabase.getDados(ImplementacaoUmActivity.this);

        mSalvarDadosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sku = Integer.parseInt(mUpdateSKUEt.getText().toString());
                String prodName = mUpdateNomeEt.getText().toString();
                double prodPreco = Double.parseDouble(mUpdatePrecoEt.getText().toString());
                ProdutoImplUm produtoImplUm = new ProdutoImplUm(sku, prodName, prodPreco);
                salvarDatabase(produtoImplUm);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ImplementacaoUmActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void requisitarDatabase(ProdutoImplUm data) {
        mDBUmVM.getCurrentData().setValue(data);
    }

    @Override
    public void salvarDatabase(ProdutoImplUm data) {
        mConexaoDatabase.salvarDados(data);
        mConexaoDatabase.getDados(ImplementacaoUmActivity.this);
    }
}


