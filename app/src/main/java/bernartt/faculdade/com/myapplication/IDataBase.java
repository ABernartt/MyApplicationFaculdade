package bernartt.faculdade.com.myapplication;

import bernartt.faculdade.com.myapplication.implementacaoUm.ProdutoImplUm;

public interface IDataBase {

    void requisitarDatabase(ProdutoImplUm data);
    void salvarDatabase(ProdutoImplUm ProdutoImplUm);
}



