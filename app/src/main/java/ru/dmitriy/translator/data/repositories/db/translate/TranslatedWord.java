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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslatedWord that = (TranslatedWord) o;

        if (!word.equals(that.word)) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null)
            return false;
        return translated != null ? translated.equals(that.translated) : that.translated == null;
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (translated != null ? translated.hashCode() : 0);
        return result;
    }
}
