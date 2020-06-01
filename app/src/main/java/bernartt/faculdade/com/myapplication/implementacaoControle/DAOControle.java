package bernartt.faculdade.com.myapplication.implementacaoControle;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DAOControle {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setEntidadeControle(EntidadeControle entidadeControle);

    @Query("SELECT * from entidadeControle")
    LiveData<EntidadeControle> getEntidadeControle();

    @Query("UPDATE entidadeControle SET chamadasImplUm=:chamadasImplUm")
    void updateChamadasImplUm(int chamadasImplUm);

    @Query("SELECT chamadasImplUm FROM entidadeControle")
    int getChamadasImplUm();

}
