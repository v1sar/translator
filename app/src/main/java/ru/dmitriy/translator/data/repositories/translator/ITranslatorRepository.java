package ru.dmitriy.translator.data.repositories.translator;

import io.reactivex.Observable;

/**
 * Created by Dmitriy on 27.11.2017.
 */

public interface ITranslatorRepository {

    Observable<String> getNetworkTranslate(String wordToTranslate);

}
