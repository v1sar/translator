package ru.dmitriy.translator.dagger.translator;

import dagger.Module;
import dagger.Provides;
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
    ITranslatorRepository provideITranslatorRepository() {
        return new TranslatorRepository();
    }
}
