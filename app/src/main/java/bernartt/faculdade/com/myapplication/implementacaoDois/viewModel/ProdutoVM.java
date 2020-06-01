package bernartt.faculdade.com.myapplication.implementacaoDois.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.Produto;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.Repositorio;

public class ProdutoVM extends ViewModel {

    private Repositorio mRepositorio;
    private LiveData<Produto> mProdutoLiveData;

    public ProdutoVM(Repositorio repositorio){
        mRepositorio = repositorio;
        mProdutoLiveData = mRepositorio.getProduto();
    }
    public LiveData<Produto> getProdutoLiveData() {
        return mProdutoLiveData;
    }
}


