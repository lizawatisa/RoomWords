package my.edu.utem.roomwords;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    //code sesntiasa sama
    WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

   //code sentiasa sama
    //nak tunjuk kt mana
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    //apa yg nak ditunjuk. code mungkin berubah ikut soalan
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords != null){
            holder.wordTextView.setText(mWords.get(position).getWord());
        }
        else{
            holder.wordTextView.setText("No word");
        }

    }

    //brp row nak
    @Override
    public int getItemCount() {
        if(mWords != null)
            return mWords.size();
        else
            return 0;
    }

    //utk masukkan data
    void setmWords(List <Word> words){
        this.mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        TextView wordTextView;
        public WordViewHolder(View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.textView);
        }
    }
}
