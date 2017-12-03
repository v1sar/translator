package ru.dmitriy.translator.ui.presenters;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriy.translator.business.translator.ITranslatorInteractor;
import ru.dmitriy.translator.ui.views.ITranslatorView;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class TranslatorPresenter implements ITranslatorPresenter {

    private ITranslatorView mTranslatorView;
    private ITranslatorInteractor mTranslatorInteractor;

    public TranslatorPresenter(ITranslatorInteractor iTranslatorInteractor) {
        mTranslatorInteractor = iTranslatorInteractor;
    }

    @Override
    public void bindView(ITranslatorView translatorView) {
        this.mTranslatorView = translatorView;
    }

    @Override
    public void unbindView() {
        mTranslatorView = null;
    }

    public void doTranslate(String wordToTranslate) {
        mTranslatorView.showLoading();
        mTranslatorInteractor.getTranslate(wordToTranslate)
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessResult, this::handleBadResult);
    }

    private void handleSuccessResult(String translatedWord) {
        mTranslatorView.stopLoading();
        mTranslatorView.onTranslateDone(translatedWord);
    }

    private void handleBadResult(Throwable throwable) {
        mTranslatorView.stopLoading();
        mTranslatorView.onTranslateDone(throwable.getLocalizedMessage());
    }
}
