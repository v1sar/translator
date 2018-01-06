package ru.dmitriy.translator.ui.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriy.translator.business.translator.ITranslatorInteractor;
import ru.dmitriy.translator.ui.views.ITranslatorView;

/**
 * Created by Dmitriy on 26.11.2017.
 */

@InjectViewState
public class TranslatorPresenter extends MvpPresenter<ITranslatorView> implements ITranslatorPresenter {

    private ITranslatorInteractor mTranslatorInteractor;

    public TranslatorPresenter(ITranslatorInteractor iTranslatorInteractor) {
        mTranslatorInteractor = iTranslatorInteractor;
    }

    public void doTranslate(String wordToTranslate) {
        getViewState().showLoading();
        mTranslatorInteractor.getTranslate(wordToTranslate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessResult, this::handleBadResult);
    }

    private void handleSuccessResult(String translatedWord) {
        getViewState().stopLoading();
        getViewState().onTranslateDone(translatedWord);
    }

    private void handleBadResult(Throwable throwable) {
        getViewState().stopLoading();
        getViewState().onTranslateDone(throwable.getLocalizedMessage());
    }
}
