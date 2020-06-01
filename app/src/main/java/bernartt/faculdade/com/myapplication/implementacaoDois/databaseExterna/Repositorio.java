package bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.Produto;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.ProdutoDAO;

public class Repositorio {

    private static final Object LOCK = new Object();
    private static Repositorio sInstancia;
    private final ProdutoDAO mDAO;
    private boolean mInicializado;
    private AcessoDatabaseExterna mAcessoDatabaseExterna;
    private final ExecutorsMT mExecutorsMT;

    private Repositorio( ProdutoDAO dao,
                        AcessoDatabaseExterna acessoDatabaseExterna, ExecutorsMT executorsMT){

        mDAO= dao;
        mAcessoDatabaseExterna=acessoDatabaseExterna;
        mExecutorsMT=executorsMT;

        LiveData<Produto> networkDados = mAcessoDatabaseExterna.getDadosDatabaseExternaLD();

        networkDados.observeForever(new Observer<Produto>() {
            @Override
            public void onChanged(Produto data) {

                mExecutorsMT.getExecutor().execute(()->{
                    mDAO.setProduto(data);
                });
            }
        });
    }

    public synchronized static Repositorio getInstance(ProdutoDAO dao, AcessoDatabaseExterna acessoDatabaseExterna, ExecutorsMT executorsMT){
        if(sInstancia == null){
            synchronized (LOCK){
                sInstancia = new Repositorio(dao, acessoDatabaseExterna, executorsMT);
            }
        }
        return sInstancia;
    }

    public LiveData<Produto> getProduto(){
        iniciarDados();
        return mDAO.getProduto();
    }


    public synchronized void iniciarDados(){

        if(mInicializado) return;

        mInicializado = true;
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(()->{
            mAcessoDatabaseExterna.baixarDadosExternos(mDAO.getChamadas());
        });

    }

    public int getProdSKU(){return mDAO.getSKU();}

    public String getProdNome(){return mDAO.getProdNome();}

    public double getProdPreco(){return mDAO.getProdPreco();}

    public void setProdSKU(int sku){
        mDAO.updateSku(sku);
    }

    public void setProdNome(String prodNome){
        mDAO.updateProdNome(prodNome);
    }

    public void setProdPreco(double prodPreco){
        mDAO.updateProdPreco(prodPreco);
    }


}
