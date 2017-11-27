package ru.dmitriy.translator.business.translator;

import io.reactivex.Observable;

/**
 * Created by Dmitriy on 27.11.2017.
 */

public interface ITranslatorInteractor {

    Observable<String> getTranslate(String neetToTransalte);

}
