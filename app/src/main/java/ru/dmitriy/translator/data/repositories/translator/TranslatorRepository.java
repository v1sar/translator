package ru.dmitriy.translator.data.repositories.translator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import ru.dmitriy.translator.data.repositories.db.AppDatabase;
import ru.dmitriy.translator.data.repositories.db.translate.TranslatedWord;
import ru.dmitriy.translator.data.repositories.network.ITranslatorApi;

/**
 * Created by Dmitriy on 27.11.2017.
 */

public class TranslatorRepository implements ITranslatorRepository {

    private final static String API_KEY = "trnsl.1.1.20170319T210150Z.f957fb7c5aa69a04.aa08dbf2c63ea7557971c4902005270c45eeb94b";

    private Retrofit mRetrofit;
    private AppDatabase mAppDatabase;

    public TranslatorRepository(Retrofit retrofit, AppDatabase appDatabase) {
        mRetrofit = retrofit;
        mAppDatabase = appDatabase;
    }

    @Override
    public Observable<String> getDbTranslate(String wordToTranslate) {
        System.out.println("getDbTranslate");
            return mAppDatabase.getTranslatedWordDao().getWordTranslation(wordToTranslate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).toObservable()
                .map(translatedWord -> translatedWord.getTranslated() + " from DB");
    }

    @Override
    public Observable<String> getNetworkTranslate(String wordToTranslate) {
        System.out.println("getNetworkTranslate");
        return mRetrofit.create(ITranslatorApi.class)
                .getMyJSON(API_KEY, wordToTranslate, "en-ru")
                .subscribeOn(Schedulers.io())
                .map(val -> {
                    System.out.println("inside map getNetworkTranslate");
                    saveTranslation(wordToTranslate, "en-ru", val.getText()[0]);
                    return val.getText()[0];
                })
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.just("Some error occur"));

    }

    @Override
    public void saveTranslation(String wordToTranslate, String direction, String translation) {
        System.out.println("Saving: " + wordToTranslate + " translation: " + translation);
        mAppDatabase.getTranslatedWordDao()
                .insertWords(new TranslatedWord(wordToTranslate, direction, translation));
    }
}
