package ru.dmitriy.translator.data.repositories.db.translate;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Dmitriy on 04.01.2018.
 */
@Entity
public class TranslatedWord {
    @PrimaryKey
    @NonNull
    String word;

    String direction;

    String translated;

    public TranslatedWord(@NonNull String word, String direction, String translated) {
        this.word = word;
        this.direction = direction;
        this.translated = translated;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public String getDirection() {
        return direction;
    }

    public String getTranslated() {
        return translated;
    }
}
