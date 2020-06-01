package bernartt.faculdade.com.myapplication.implementacaoDois.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.Repositorio;

public class ProdutoViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Repositorio mRepositorio;

    public ProdutoViewModelFactory(Repositorio repositorio){
        mRepositorio = repositorio;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProdutoVM(mRepositorio);
    }
}
