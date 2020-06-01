package bernartt.faculdade.com.myapplication.implementacaoControle;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EntidadeControle.class}, version =1, exportSchema = false)
public abstract class DatabaseControle extends RoomDatabase {

    private static final String DATABASE_NAME = "controleDatabase";
    private static final Object LOCK = new Object();
    private static DatabaseControle sInstancia;

    public static DatabaseControle getInstance(Context context){
        if(sInstancia == null){
            synchronized (LOCK){
                sInstancia = Room.databaseBuilder(context.getApplicationContext(),
                        DatabaseControle.class, DatabaseControle.DATABASE_NAME).build();
            }
        }
        return sInstancia;
    }

    public abstract DAOControle mDAOControle();


}
