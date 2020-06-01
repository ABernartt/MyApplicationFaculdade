package bernartt.faculdade.com.myapplication.implementacaoControle;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntidadeControle {

    @PrimaryKey
    public int cod;

    @ColumnInfo(name = "chamadasImplUm")
    public int implementacaoUm;

    public EntidadeControle(int cod, int implementacaoUm){
        this.implementacaoUm=implementacaoUm;

    }
    public int getCod() {
        return cod;
    }

    public int getImplementacaoUm() {
        return implementacaoUm;
    }

}


