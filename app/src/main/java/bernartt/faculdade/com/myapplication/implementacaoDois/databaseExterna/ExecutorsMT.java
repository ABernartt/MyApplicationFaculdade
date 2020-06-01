package bernartt.faculdade.com.myapplication.implementacaoDois.databaseExterna;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorsMT {

    private static final Object LOCK = new Object();
    private static ExecutorsMT sInstancia;
    private final Executor mExecutor;

    private ExecutorsMT(Executor executor){
        mExecutor = executor;
    }

    public static ExecutorsMT getInstance(){
        if(sInstancia == null){
            synchronized (LOCK){
                sInstancia = new ExecutorsMT(Executors.newSingleThreadExecutor());
            }
        }
        return sInstancia;
    }

    public Executor getExecutor(){
        return  mExecutor;
    }

}


