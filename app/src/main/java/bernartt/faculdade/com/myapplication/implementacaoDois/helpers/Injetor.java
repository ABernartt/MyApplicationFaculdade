package bernartt.faculdade.com.myapplication.implementacaoDois.helpers;

import android.content.Context;

import bernartt.faculdade.com.myapplication.implementacaoDois.databaseLocal.ProdutoDatabase;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.AcessoDatabaseExterna;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.ExecutorsMT;
import bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna.Repositorio;
import bernartt.faculdade.com.myapplication.implementacaoDois.viewModel.ProdutoViewModelFactory;

public class Injetor {

    public static Repositorio forneceRepositorio(Context context){
        ProdutoDatabase produtoDatabase = ProdutoDatabase.getInstance(context);
        ExecutorsMT executorsMT = ExecutorsMT.getInstance();
        AcessoDatabaseExterna acessoDatabaseExterna = AcessoDatabaseExterna.getInstance();
        return Repositorio.getInstance(produtoDatabase.mProdutoDAO(),
                acessoDatabaseExterna, executorsMT);
    }
    public static ProdutoViewModelFactory forneceProdutoViewModelFactory(Context context){

        Repositorio repositorio = forneceRepositorio(context);
        return new ProdutoViewModelFactory(repositorio);

    }
}


