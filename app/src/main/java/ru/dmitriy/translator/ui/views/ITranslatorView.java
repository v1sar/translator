package ru.dmitriy.translator.ui.views;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public interface ITranslatorView{
    void showLoading();
    void stopLoading();
    void onTranslateDone(String transalate);
}
