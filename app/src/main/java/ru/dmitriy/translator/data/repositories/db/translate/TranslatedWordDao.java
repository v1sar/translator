package ru.dmitriy.translator.data.repositories.db.translate;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import io.reactivex.Single;


/**
 * Created by Dmitriy on 04.01.2018.
 */

@Dao
public interface TranslatedWordDao {
    @Insert
    void insertWords(TranslatedWord... translatedWords);

    @Delete
    void delete(TranslatedWord translatedWord);

    @Query("SELECT * FROM translatedword")
    Single<TranslatedWord> getAllTranslatedWords();

    @Query("SELECT * FROM translatedword WHERE word LIKE :w")
    Single<TranslatedWord> getWordTranslation(String w);
}
