package bernartt.faculdade.com.myapplication.implementacaoDois;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bernartt.faculdade.com.myapplication.MainActivity;
import bernartt.faculdade.com.myapplication.R;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.AcessoDatabaseExterna;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.Produto;
import bernartt.faculdade.com.myapplication.implementacaoDois.helpers.Injetor;
import bernartt.faculdade.com.myapplication.implementacaoDois.viewModel.ProdutoVM;
import bernartt.faculdade.com.myapplication.implementacaoDois.viewModel.ProdutoViewModelFactory;

public class ImplementacaoDoisActivity extends AppCompatActivity {

    private TextView mTituloTv, mDescricaoTv,mSKUTv, mNomeTv, mPrecoTv,  mChamadasTv;
    private EditText mUpdateSKUEt, mUpdateNomeEt, mUpdatePrecoEt;
    private ProdutoVM mProdutoVM;
    private Button mSalvarDadosBtn;
    private int mChamadas;


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

        mTituloTv.setText(getResources().getString(R.string.tituloDois));
        mDescricaoTv.setText(getResources().getString(R.string.descDois));

        ProdutoViewModelFactory produtoViewModelFactory = Injetor
                .forneceProdutoViewModelFactory(this.getApplicationContext());
        mProdutoVM = new ViewModelProvider(this, produtoViewModelFactory)
                .get(ProdutoVM.class);

        mProdutoVM.getProdutoLiveData().observe(this, new Observer<Produto>() {
            @Override
            public void onChanged(Produto produto) {
                if (produto != null){
                    int sku = produto.getSku();
                    String prodName = produto.getProdNome();
                    double prodPreco = produto.getProdPreco();
                    mSKUTv.setText(getResources().getString(R.string.prodSKU, String.valueOf(sku)));
                    mNomeTv.setText(getResources().getString(R.string.prodNome, prodName));
                    mPrecoTv.setText(getResources().getString(R.string.prodPreco, String.valueOf(prodPreco)));
                    mUpdateSKUEt.setText(String.valueOf(sku));
                    mUpdateNomeEt.setText(prodName);
                    mUpdatePrecoEt.setText(String.valueOf(prodPreco));
                    mChamadas=produto.getChamadas();
                    mChamadasTv.setText(getResources().getString(R.string.chamadas, String.valueOf(mChamadas)));
                }
            }
        });

        mSalvarDadosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sku = Integer.parseInt(mUpdateSKUEt.getText().toString());
                String prodNome = mUpdateNomeEt.getText().toString();
                double prodPreco = Double.parseDouble(mUpdatePrecoEt.getText().toString());
                Produto produto = new Produto(sku, prodNome, prodPreco, mChamadas);
                AcessoDatabaseExterna.salvarDatabase(produto, ImplementacaoDoisActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ImplementacaoDoisActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
