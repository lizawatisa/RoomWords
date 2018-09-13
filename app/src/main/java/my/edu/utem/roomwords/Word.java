package my.edu.utem.roomwords;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    //@PrimaryKey(autoGenerate = true) //utk auto generate
    //private int id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="word")
    private String mWord;

    public Word (@NonNull String mWord){
        this.mWord = mWord;
    }

    public String getWord(){
        return mWord;
    }

    public void setWord (String mWord){
        this.mWord = mWord;
    }
}
