package my.edu.utem.roomwords;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDataBase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){ //LiveData automatic wat thread berbeza
        return mAllWords;
    }

    //implementation kalau xcomplex
    /*public void insert(Word word){
        mWordDao.insert(word);
    }*/

    public void insert(Word word){
        new insertAsycTask(mWordDao).execute(word);
    }

    //implementation kalau xcomplex
    /*public void deleteAll(){
        mWordDao.deleteAll();
    }*/

    public void deleteAll(){
       new deleteAllAsyncTask(mWordDao).execute();
    }

    private static class insertAsycTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;
        insertAsycTask(WordDao dao){
            mAsyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Word, Void, Void>{ //<hntr apa, apa yg dpt in progress, apa yg dpt in return>

        private WordDao mAsyncTaskDao;
        deleteAllAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
