package ru.dmitriy.translator.data.repositories.translator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import ru.dmitriy.translator.data.repositories.network.ITranslatorApi;

/**
 * Created by Dmitriy on 27.11.2017.
 */

public class TranslatorRepository implements ITranslatorRepository {

    private final static String API_KEY = "trnsl.1.1.20170319T210150Z.f957fb7c5aa69a04.aa08dbf2c63ea7557971c4902005270c45eeb94b";

    private Retrofit mRetrofit;

    public TranslatorRepository(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<String> getNetworkTranslate(String wordToTranslate) {
        return mRetrofit.create(ITranslatorApi.class)
                .getMyJSON(API_KEY, wordToTranslate, "en-ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(val -> val.getText()[0]);
    }
}
