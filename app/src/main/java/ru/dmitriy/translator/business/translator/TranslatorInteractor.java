package ru.dmitriy.translator.business.translator;

import io.reactivex.Observable;
import ru.dmitriy.translator.data.repositories.translator.ITranslatorRepository;

/**
 * Created by Dmitriy on 27.11.2017.
 */

public class TranslatorInteractor implements ITranslatorInteractor {

    private ITranslatorRepository mTranslatorRepository;

    public TranslatorInteractor(ITranslatorRepository translatorRepository) {
        mTranslatorRepository = translatorRepository;
    }

    @Override
    public Observable<String> getTranslate(String wordToTranslate) {
        return mTranslatorRepository.getDbTranslate(wordToTranslate)
                .onErrorResumeNext(mTranslatorRepository.getNetworkTranslate(wordToTranslate));
    }

}
