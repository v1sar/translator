package ru.dmitriy.translator.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import ru.dmitriy.translator.R;
import ru.dmitriy.translator.data.repositories.db.translate.TranslatedWord;

/**
 * Created by Dmitriy on 05.01.2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.WordViewHolder> {

    private List<TranslatedWord> wordsList;

    public HistoryAdapter(List<TranslatedWord> wordsList) {
        this.wordsList = wordsList;
    }

    @Override
    public HistoryAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.translated_row, parent, false);
        return new WordViewHolder(itemView);
    }


    public void onBindViewHolder(HistoryAdapter.WordViewHolder holder, int position) {
        final TranslatedWord word = wordsList.get(position);
        holder.toTranslate.setText(word.getWord());
        holder.translated.setText(word.getTranslated());
        holder.direction.setText(word.getDirection());
        holder.isFav.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView toTranslate, translated, direction;
        private CheckBox isFav;

        public WordViewHolder(View itemView) {
            super(itemView);
            toTranslate = itemView.findViewById(R.id.word_to_translate);
            translated = itemView.findViewById(R.id.translated_word);
            direction =  itemView.findViewById(R.id.translate_dir);
            isFav = itemView.findViewById(R.id.favourite_word);
        }
    }
}
