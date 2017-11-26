package ru.dmitriy.translator.ui.presenters;

import ru.dmitriy.translator.ui.views.ITranslatorView;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public interface ITranslatorPresenter {
    void bindView(ITranslatorView translatorView);
    void unbindView();
    void doTranslate();
}
