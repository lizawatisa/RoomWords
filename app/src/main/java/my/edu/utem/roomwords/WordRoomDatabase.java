package my.edu.utem.roomwords;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao(); //utk 1 Dao utk 1 entity
    private static WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDataBase(final Context context){
        if (INSTANCE == null){ //singleton
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sroomDatabaseCallBack)
                            .build(); //builder
                }
            }
        }
        return INSTANCE;
    }

    //
    private static RoomDatabase.Callback sroomDatabaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDatabaseAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void,Void,Void>{
        String[] words = {"dolphin", "crocodile", "cobra"};
        private final WordDao mDao;

        PopulateDatabaseAsync(WordRoomDatabase database){
            this.mDao = database.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //  mDao.deleteAll();
            if(mDao.getAnyWord().length == 0){ //0 bila kali pertama kita install app
                for(int i=0; i<words.length; i++){
                    mDao.insert(new Word(words[i]));
                }
            }



            return null;
        }
    }
}
