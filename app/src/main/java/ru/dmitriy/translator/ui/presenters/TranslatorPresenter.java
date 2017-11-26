package ru.dmitriy.translator.ui.presenters;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriy.translator.ui.views.ITranslatorView;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class TranslatorPresenter implements ITranslatorPresenter {

    ITranslatorView translatorView;

    @Override
    public void bindView(ITranslatorView translatorView) {
        this.translatorView = translatorView;
    }

    @Override
    public void unbindView() {
        translatorView = null;
    }

    public void doTranslate() {
        translatorView.showLoading();
        Observable.just("one")
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    translatorView.onTranslateDone(s);
                    translatorView.stopLoading();
                });
    }
}
