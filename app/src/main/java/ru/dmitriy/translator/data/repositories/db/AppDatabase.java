package ru.dmitriy.translator.data.repositories.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.dmitriy.translator.data.repositories.db.translate.TranslatedWord;
import ru.dmitriy.translator.data.repositories.db.translate.TranslatedWordDao;

/**
 * Created by Dmitriy on 04.01.2018.
 */

@Database(entities = {TranslatedWord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TranslatedWordDao getTranslatedWordDao();
}
