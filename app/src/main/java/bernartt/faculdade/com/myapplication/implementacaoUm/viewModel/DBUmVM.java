package bernartt.faculdade.com.myapplication.implementacaoUm.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bernartt.faculdade.com.myapplication.implementacaoUm.ProdutoImplUm;

public class DBUmVM extends ViewModel {

    private MutableLiveData<ProdutoImplUm> currentData;

    public MutableLiveData<ProdutoImplUm> getCurrentData(){
        if (currentData == null){
            currentData = new MutableLiveData<ProdutoImplUm>();
        }
        return currentData;
    }
}



