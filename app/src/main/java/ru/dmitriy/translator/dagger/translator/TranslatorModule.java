package ru.dmitriy.translator.dagger.translator;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dmitriy.translator.business.translator.ITranslatorInteractor;
import ru.dmitriy.translator.business.translator.TranslatorInteractor;
import ru.dmitriy.translator.data.repositories.translator.ITranslatorRepository;
import ru.dmitriy.translator.data.repositories.translator.TranslatorRepository;
import ru.dmitriy.translator.ui.presenters.ITranslatorPresenter;
import ru.dmitriy.translator.ui.presenters.TranslatorPresenter;

/**
 * Created by Dmitriy on 03.12.2017.
 */
@Module
public class TranslatorModule {

    private static final String ROOT_URL = "https://translate.yandex.net";

    @Provides
    @TranslatorScope
    ITranslatorPresenter provideITranslatorPresenter(ITranslatorInteractor iTranslatorInteractor) {
        return new TranslatorPresenter(iTranslatorInteractor);
    }

    @Provides
    @TranslatorScope
    ITranslatorInteractor provideITranslatorInteractor(ITranslatorRepository iTranslatorRepository) {
        return new TranslatorInteractor(iTranslatorRepository);
    }

    @Provides
    @TranslatorScope
    ITranslatorRepository provideITranslatorRepository(Retrofit retrofit) {
        return new TranslatorRepository(retrofit);
    }

    @Provides
    @TranslatorScope
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ROOT_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
