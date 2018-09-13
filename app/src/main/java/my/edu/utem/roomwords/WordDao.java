package my.edu.utem.roomwords;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

//fungsi Dao utk mapkn function ke query
@Dao
public interface WordDao {
    //define method yg perlu ada utk access db

    @Insert
    void insert(Word word);

    @Query("DELETE from word_table") //mapping queery ke function
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();
}
